package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import exercise.model.Contact;
import exercise.repository.ContactRepository;
import exercise.dto.ContactDTO;
import exercise.dto.ContactCreateDTO;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

    // BEGIN
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO create(@RequestBody ContactCreateDTO contactCreateDTO) {
        var contact = toEntity(contactCreateDTO);
        contactRepository.save(contact);
        return toDTO(contact);
    }

    private Contact toEntity(ContactCreateDTO data) {
        Contact contact = new Contact();
        contact.setFirstName(data.getFirstName());
        contact.setLastName(data.getLastName());
        contact.setPhone(data.getPhone());
        return contact;
    }

    private ContactDTO toDTO(Contact data) {
        ContactDTO contact = new ContactDTO();
        contact.setId(data.getId());
        contact.setFirstName(data.getFirstName());
        contact.setLastName(data.getLastName());
        contact.setPhone(data.getPhone());
        contact.setCreatedAt(data.getCreatedAt());
        contact.setUpdatedAt(data.getUpdatedAt());
        return contact;
    }
    // END
}
