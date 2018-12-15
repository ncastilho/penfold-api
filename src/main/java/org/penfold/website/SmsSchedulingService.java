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
        int hour = LocalDateTime.now().getHour();
        int minutes = LocalDateTime.now().getMinute();

        Iterable<MessageEntity> messageEntities = messageEntityRepository.findAllByScheduledTime(String.format("%s:%s", hour, minutes));

        messageEntities.forEach((message) -> sendSms(message));
    }

    private void sendSms(MessageEntity message) {
        PreferencesEntity preferencesEntity = preferencesEntityRepository.findByContactId(message.getContactId());

        if (!preferencesEntity.isSmsEnabled() || !preferencesEntity.isMobileVerified()) {
            log.warn("Cannot send message: [id={}]. Sms is disabled or mobile is not verified: [smsEnabled={}, mobileVerified={}]", message.getId(), preferencesEntity.isSmsEnabled(), preferencesEntity.isMobileVerified());
            return;
        }

        Optional<ContactEntity> contactEntity = contactEntityRepository.findById(message.getContactId());

        if (!contactEntity.isPresent()) {
            throw new IllegalStateException(String.format("Found an orphan message: [%s]", message.getId()));
        }

        HistoryEntity historyEntity = HistoryEntity.builder()
                .contactId(message.getContactId())
                .mobile(contactEntity.get().getMobile())
                .content(message.getContent())
                .scheduledTime(message.getScheduledTime())
                .events(new ArrayList<>())
                .build();

        try {
            String callbackToken = smsGateway.sendMessage(contactEntity.get().getMobile(), message.getContent());
            historyEntity.setCallbackToken(callbackToken);
            historyEntity.setSentStatus();
        } catch (Exception ex) {
            historyEntity.setFailedStatus();
        } finally {
            historyEntityRepository.save(historyEntity);
        }
    }

}
