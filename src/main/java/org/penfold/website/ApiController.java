package org.penfold.website;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @PostMapping("/contacts")
    public ContactEntity addContact(@Valid @RequestBody Contact contact) {
        return apiService.createContact(contact);
    }

    @GetMapping("/contacts")
    public List<ContactEntity> getAllContacts() {
        return apiService.getAllContacts();
    }

    @GetMapping("/contacts/{id}")
    public ContactEntity getContact(@PathVariable String id) {
        return apiService.getContact(id);
    }

    @PutMapping("/contacts/{id}")
    public ContactEntity updateContact(@PathVariable String id, @Valid @RequestBody Contact contact) {
        return apiService.updateContact(id, contact);
    }

    @DeleteMapping("/contacts/{id}")
    public void deleteContact(@PathVariable String id) {
        apiService.deleteContact(id);
    }

    @PostMapping("/contacts/{id}/messages")
    public MessageEntity addMessage(@PathVariable String id, @Valid @RequestBody Message message) {
        return apiService.createMessage(id, message);
    }

    @GetMapping("/contacts/{id}/messages")
    public List<MessageEntity> getAllMessages(@PathVariable String id) {
        return apiService.getAllMessages(id);
    }

    @GetMapping("/messages/{id}")
    public MessageEntity getMessage(@PathVariable String id) {
        return apiService.getMessage(id);
    }

    @PutMapping("/messages/{id}")
    public MessageEntity updateMessage(@PathVariable String id, @Valid @RequestBody Message message) {
        return apiService.updateMessage(id, message);
    }

    @DeleteMapping("/messages/{id}")
    public void deleteMessage(@PathVariable String id) {
        apiService.deleteMessage(id);
    }
}