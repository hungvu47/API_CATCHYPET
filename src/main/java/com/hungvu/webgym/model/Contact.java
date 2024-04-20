package com.hungvu.webgym.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "contact")
@Data
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Long contactId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "phone_customer")
    private String phoneCustomer;

    private String emailCustomer;

    private String notes;
}
