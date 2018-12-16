package org.penfold.website;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ApiController {

    private final ContactService contactService;

    public ApiController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/contacts")
    public ContactEntity addContact(@Valid @RequestBody Contact contact) {
        return contactService.createContact(contact);
    }

    @GetMapping("/contacts")
    public List<ContactEntity> getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/contacts/{id}")
    public ContactEntity getContact(@PathVariable String id) {
        return contactService.getContact(id);
    }

    @PutMapping("/contacts/{id}")
    public ContactEntity updateContact(@PathVariable String id, @Valid @RequestBody Contact contact) {
        return contactService.updateContact(id, contact);
    }

    @DeleteMapping("/contacts/{id}")
    public void deleteContact(@PathVariable String id) {
        contactService.deleteContact(id);
    }

    @PostMapping("/contacts/{id}/messages")
    public MessageEntity addMessage(@PathVariable String id, @Valid @RequestBody Message message) {
        return contactService.createMessage(id, message);
    }

    @GetMapping("/contacts/{id}/messages")
    public List<MessageEntity> getAllMessages(@PathVariable String id) {
        return contactService.getAllMessages(id);
    }

    @GetMapping("/contacts/{id}/history")
    public List<HistoryEntity> getHistory(@PathVariable String id) {
        return contactService.getHistory(id);
    }

    @GetMapping("/contacts/{id}/preferences")
    public PreferencesEntity getPreferences(@PathVariable String id) {
        return contactService.getPreferences(id);
    }

    @GetMapping("/messages/{id}")
    public MessageEntity getMessage(@PathVariable String id) {
        return contactService.getMessage(id);
    }

    @PutMapping("/messages/{id}")
    public MessageEntity updateMessage(@PathVariable String id, @Valid @RequestBody Message message) {
        return contactService.updateMessage(id, message);
    }

    @DeleteMapping("/messages/{id}")
    public void deleteMessage(@PathVariable String id) {
        contactService.deleteMessage(id);
    }

    @PostMapping(value = "/callback/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void callback(@PathVariable String id, @Valid @ModelAttribute CallbackEvent callbackEvent) {
        //TODO id, sid, token, from, to, error msg / code verification / validation
        contactService.updateMessageHistory(id, callbackEvent);
    }

}