package com.hungvu.webgym.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Entity
@Table(name = "pet_services")
public class PetCare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_care_id")
    private Long petCareId;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "service_price")
    private double priceService;

    @OneToMany(mappedBy = "petCare", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ClassificationPet> classifications = new ArrayList<>();
}
