package com.hungvu.webgym.controller;

import com.hungvu.webgym.model.Contact;
import com.hungvu.webgym.request.ContactRequest;
import com.hungvu.webgym.service.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactController {

    private final IContactService contactService;

    @GetMapping("/contactList")
    public ResponseEntity<List<Contact>> getContactList(){
        List<Contact> contactList = contactService.getAllContacts();
        return ResponseEntity.ok(contactList);
    }

    @PostMapping("/customer")
    public ResponseEntity<?> customerContact(@RequestBody ContactRequest request) {
        contactService.CustomerContact(request);
        return ResponseEntity.ok("success");
    }
}
