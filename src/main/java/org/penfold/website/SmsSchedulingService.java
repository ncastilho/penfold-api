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
    private final SmsGateway smsGateway;

    public SmsSchedulingService(ContactEntityRepository contactEntityRepository, MessageEntityRepository messageEntityRepository, PreferencesEntityRepository preferencesEntityRepository, HistoryEntityRepository historyEntityRepository, SmsGateway smsGateway) {
        this.contactEntityRepository = contactEntityRepository;
        this.messageEntityRepository = messageEntityRepository;
        this.preferencesEntityRepository = preferencesEntityRepository;
        this.historyEntityRepository = historyEntityRepository;
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

    private void sendSms(MessageEntity message) {
        PreferencesEntity preferences = getPreferencesEntity(message);

        boolean isSmsEnabled = preferences.isSmsEnabled();
        boolean isMobileVerified = preferences.isMobileVerified();

        if (!isSmsEnabled || !isMobileVerified) {
            log.warn("Cannot send message. Sms is disabled or mobile is not verified: [id={}, isSmsEnabled={}, mobileVerified={}]", message.getId(), isSmsEnabled, isMobileVerified);
            return;
        }

        ContactEntity contact = getContactEntity(message);
        HistoryEntity history = createHistoryEntity(message, contact);

        try {
            String messageSid = smsGateway.sendMessage(
                    message.getId(),
                    contact.getMobile(),
                    message.getContent());

            history.setMessageSid(messageSid);
            history.transitionTo(State.FORWARDED, String.format("Message has been forwarded: [sid=%s]", messageSid));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            history.transitionTo(State.FAILED, ex.getMessage());
        } finally {
            historyEntityRepository.save(history);
        }
    }

    private PreferencesEntity getPreferencesEntity(MessageEntity message) {
        PreferencesEntity preferencesEntity = preferencesEntityRepository.findByContactId(message.getContactId());

        if (preferencesEntity == null) {
            throw new IllegalStateException(String.format("Couldn't find contact preferences for message: [id=%s]", message.getId()));
        }

        return preferencesEntity;
    }

    private ContactEntity getContactEntity(MessageEntity message) {
        Optional<ContactEntity> contact = contactEntityRepository.findById(message.getContactId());

        if (!contact.isPresent()) {
            throw new IllegalStateException(String.format("Found an orphan message: [id=%s]", message.getId()));
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
                .events(new ArrayList<>())
                .build();
    }

}
