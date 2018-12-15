package org.penfold.website;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContactService {

    private final ContactEntityRepository contactEntityRepository;
    private final MessageEntityRepository messageEntityRepository;
    private final HistoryEntityRepository historyEntityRepository;
    private final PreferencesEntityRepository preferencesEntityRepository;

    public ContactService(ContactEntityRepository contactEntityRepository, MessageEntityRepository messageEntityRepository, HistoryEntityRepository historyEntityRepository, PreferencesEntityRepository preferencesEntityRepository) {
        this.contactEntityRepository = contactEntityRepository;
        this.messageEntityRepository = messageEntityRepository;
        this.historyEntityRepository = historyEntityRepository;
        this.preferencesEntityRepository = preferencesEntityRepository;
    }

    public ContactEntity createContact(Contact contact) {
        ContactEntity contactEntity = contactEntityRepository.save(convertToContactEntity(contact));

        List<MessageEntity> messageEntities = contact.getMessages().stream()
                .map((message) -> convertToMessageEntity(contactEntity, message))
                .collect(Collectors.toList());

        messageEntityRepository.saveAll(messageEntities);

        PreferencesEntity preferencesEntity = PreferencesEntity.builder()
                .contactId(contactEntity.getId())
                .mobileVerified(false)
                .smsEnabled(true)
                .build();

        preferencesEntityRepository.save(preferencesEntity);

        return contactEntity;
    }

    public ContactEntity getContact(String contactId) {
        return contactEntityRepository.findById(contactId).orElseThrow(() -> new ContactNotFoundException(contactId));
    }

    public List<ContactEntity> getAllContacts() {
        return Lists.newArrayList(contactEntityRepository.findAll());
    }

    public void deleteContact(String contactId) {
        messageEntityRepository.deleteAllByContactId(contactId);
        preferencesEntityRepository.deleteAllByContactId(contactId);
        contactEntityRepository.deleteById(contactId);
    }

    public ContactEntity updateContact(String contactId, Contact contact) {
        ContactEntity contactEntity = getContact(contactId);

        if(!contact.getMobile().equals(contactEntity.getMobile())) {
            PreferencesEntity preferencesEntity = preferencesEntityRepository.findByContactId(contactId);
            preferencesEntity.setMobileVerified(false);
            preferencesEntityRepository.save(preferencesEntity);
        }

        BeanUtils.copyProperties(contact, contactEntity);

        return contactEntityRepository.save(contactEntity);
    }

    public MessageEntity createMessage(String contactId, Message message) {
        ContactEntity contactEntity = getContact(contactId);

        return messageEntityRepository.save(convertToMessageEntity(contactEntity, message));
    }

    public MessageEntity getMessage(String messageId) {
        return messageEntityRepository.findById(messageId).orElseThrow(() -> new ContactNotFoundException(messageId));
    }

    public List<MessageEntity> getAllMessages(String contactId) {
        ContactEntity contactEntity = getContact(contactId);

        return Lists.newArrayList(messageEntityRepository.findAllByContactId(contactEntity.getId()));
    }

    public void deleteMessage(String messageId) {
        messageEntityRepository.deleteById(messageId);
    }

    public MessageEntity updateMessage(String messageId, Message message) {
        MessageEntity messageEntity = getMessage(messageId);

        BeanUtils.copyProperties(message, messageEntity);

        return messageEntityRepository.save(messageEntity);
    }

    public List<HistoryEntity> getHistory(String contactId) {
        ContactEntity contactEntity = getContact(contactId);

        return Lists.newArrayList(historyEntityRepository.findAllByContactId(contactEntity.getId()));
    }

    public PreferencesEntity getPreferences(String contactId) {
        ContactEntity contactEntity = getContact(contactId);

        return preferencesEntityRepository.findByContactId(contactEntity.getId());
    }

    public PreferencesEntity toggleSmsEnabled(String contactId) {
        PreferencesEntity preferencesEntity = getPreferences(contactId);

        preferencesEntity.setSmsEnabled(!preferencesEntity.isSmsEnabled());

        return preferencesEntityRepository.save(preferencesEntity);
    }

    public PreferencesEntity verifyMobile(String contactId) {
        PreferencesEntity preferencesEntity = getPreferences(contactId);

        preferencesEntity.setMobileVerified(true);

        return preferencesEntityRepository.save(preferencesEntity);
    }

    private ContactEntity convertToContactEntity(Contact contact) {
        return ContactEntity.builder()
                .name(contact.getName())
                .email(contact.getEmail())
                .mobile(contact.getMobile())
                .build();
    }

    private MessageEntity convertToMessageEntity(ContactEntity contactEntity, Message message) {
        return MessageEntity.builder()
                .contactId(contactEntity.getId())
                .content(message.getContent())
                .scheduledTime(message.getScheduledTime())
                .build();
    }
}
