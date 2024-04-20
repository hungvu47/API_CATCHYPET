package com.hungvu.webgym.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "classification_pet")
@Data
public class ClassificationPet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classification_pet_id")
    private Long classificationPetId;

    @Column(name = "classification_name")
    private String classificationName;

    @ManyToOne
    @JoinColumn(name = "pet_care_id")
    @JsonBackReference
    private PetCare petCare;


    @OneToMany(mappedBy = "classification", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ClassificationValue> values = new ArrayList<>();
}
