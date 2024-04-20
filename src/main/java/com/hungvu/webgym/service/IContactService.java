package com.hungvu.webgym.service;

import com.hungvu.webgym.model.Contact;
import com.hungvu.webgym.request.ContactRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IContactService {
    void CustomerContact(ContactRequest request);

    List<Contact> getAllContacts();
}
