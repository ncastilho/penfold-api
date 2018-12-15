package org.penfold.website;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApiService {

    private final ContactEntityRepository contactEntityRepository;
    private final MessageEntityRepository messageEntityRepository;

    public ApiService(ContactEntityRepository contactEntityRepository, MessageEntityRepository messageEntityRepository) {
        this.contactEntityRepository = contactEntityRepository;
        this.messageEntityRepository = messageEntityRepository;
    }

    public ContactEntity createContact(Contact contact) {
        ContactEntity contactEntity = convertToContactEntity(contact);

        ContactEntity result = contactEntityRepository.save(contactEntity);

        List<MessageEntity> messageEntities = contact.getMessages().stream()
                .map((message) -> convertToMessageEntity(contactEntity, message))
                .collect(Collectors.toList());

        messageEntityRepository.saveAll(messageEntities);

        return result;
    }

    public ContactEntity getContact(String contactId) {
        return contactEntityRepository.findById(contactId).orElseThrow(() -> new ContactNotFoundException(contactId));
    }

    public List<ContactEntity> getAllContacts() {
        return Lists.newArrayList(contactEntityRepository.findAll());
    }

    public void deleteContact(String contactId) {
        contactEntityRepository.deleteById(contactId);
    }

    public ContactEntity updateContact(String contactId, Contact contact) {
        ContactEntity contactEntity = getContact(contactId);

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
