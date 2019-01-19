package org.penfold.website;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
public class SmsSchedulingService {

    private final ContactEntityRepository contactEntityRepository;
    private final MessageEntityRepository messageEntityRepository;
    private final PreferencesEntityRepository preferencesEntityRepository;
    private final HistoryEntityRepository historyEntityRepository;
    private final HistoryEventEntityRepository historyEventEntityRepository;
    private final SmsGateway smsGateway;

    public SmsSchedulingService(ContactEntityRepository contactEntityRepository, MessageEntityRepository messageEntityRepository, PreferencesEntityRepository preferencesEntityRepository, HistoryEntityRepository historyEntityRepository, HistoryEventEntityRepository historyEventEntityRepository, SmsGateway smsGateway) {
        this.contactEntityRepository = contactEntityRepository;
        this.messageEntityRepository = messageEntityRepository;
        this.preferencesEntityRepository = preferencesEntityRepository;
        this.historyEntityRepository = historyEntityRepository;
        this.historyEventEntityRepository = historyEventEntityRepository;
        this.smsGateway = smsGateway;
    }

    @Scheduled(cron = "0 ${schedule.minutes} * * * ?")
    private void sendScheduledMessages() {
        final LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        int minutes = now.getMinute();

        String scheduledTime = String.format("%02d:%02d", hour, minutes);
        Iterable<MessageEntity> messageEntities = messageEntityRepository.findAllByScheduledTime(scheduledTime);

        messageEntities.forEach(this::sendSms);
    }

    private void sendSms(MessageEntity messageEntity) {
        PreferencesEntity preferences = getPreferencesEntity(messageEntity);

        boolean isSmsEnabled = preferences.isSmsEnabled();
        boolean isMobileVerified = preferences.isMobileVerified();

        if (!isSmsEnabled || !isMobileVerified) {
            log.warn("Cannot send message. Sms is disabled or mobile is not verified: [messageId={}, isSmsEnabled={}, mobileVerified={}]", messageEntity.getId(), isSmsEnabled, isMobileVerified);
            return;
        }

        ContactEntity contactEntity = getContactEntity(messageEntity);

        RunAs.runAsAdmin(() -> {
            HistoryEntity historyEntity = historyEntityRepository.save(createHistoryEntity(messageEntity, contactEntity));

            try {
                String messageSid = smsGateway.sendMessage(
                        historyEntity.getId(),
                        contactEntity.getMobile(),
                        messageEntity.getContent());

                saveForwardedEvent(historyEntity.getId(), messageSid);

                historyEntity.setMessageSid(messageSid);
            } catch (Exception ex) {
                log.error(ex.getMessage(), ex);
                saveFailedEvent(historyEntity.getId(), ex.getMessage());
            } finally {
                historyEntityRepository.save(historyEntity);
            }
        });
    }

    private void saveFailedEvent(String historyEntityId, String message) {
        historyEventEntityRepository.save(HistoryEventEntity.builder()
                .historyId(historyEntityId)
                .messageStatus("FAILED")
                .message(message)
                .build());
    }

    private void saveForwardedEvent(String historyEntityId, String messageSid) {
        historyEventEntityRepository.save(HistoryEventEntity.builder()
                .historyId(historyEntityId)
                .messageSid(messageSid)
                .messageStatus("FORWARDED")
                .message(String.format("Message has been forwarded: [sid=%s]", messageSid))
                .build());
    }

    private PreferencesEntity getPreferencesEntity(MessageEntity message) {
        PreferencesEntity preferencesEntity = preferencesEntityRepository.findByContactId(message.getContactId());

        if (preferencesEntity == null) {
            throw new IllegalStateException(String.format("Couldn't find contact preferences for message: [messageId=%s]", message.getId()));
        }

        return preferencesEntity;
    }

    private ContactEntity getContactEntity(MessageEntity message) {
        Optional<ContactEntity> contact = contactEntityRepository.findById(message.getContactId());

        if (!contact.isPresent()) {
            throw new IllegalStateException(String.format("Found an orphan message: [messageId=%s]", message.getId()));
        }

        return contact.get();
    }

    private HistoryEntity createHistoryEntity(MessageEntity message, ContactEntity contact) {
        return HistoryEntity.builder()
                .messageId(message.getId())
                .contactId(message.getContactId())
                .mobile(contact.getMobile())
                .content(message.getContent())
                .scheduledTime(message.getScheduledTime())
                .build();
    }

}
