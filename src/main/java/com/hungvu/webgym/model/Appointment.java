package com.hungvu.webgym.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointmentId")
    private Long appointmentId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "appointment_date")
    private Date appointmentDate;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "notes", columnDefinition = "LONGTEXT")
    private String notes;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private PetCare petCare;

    @ManyToMany
    @JoinTable(name = "appointment_classification",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "classification_value_id"))
    private List<ClassificationValue> classifications = new ArrayList<>();

}
