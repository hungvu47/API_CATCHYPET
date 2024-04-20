package com.hungvu.webgym.service.impl;

import com.hungvu.webgym.model.Contact;
import com.hungvu.webgym.repository.ContactRepository;
import com.hungvu.webgym.request.ContactRequest;
import com.hungvu.webgym.service.IContactService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements IContactService {

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public void CustomerContact(ContactRequest request) {
        Contact newContact = new Contact();
        newContact.setCustomerName(request.getCustomerName());
        newContact.setPhoneCustomer(request.getPhoneCustomer());
        newContact.setEmailCustomer(request.getEmailCustomer());
        newContact.setNotes(request.getNotes());

        contactRepository.save(newContact);

    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }
}
