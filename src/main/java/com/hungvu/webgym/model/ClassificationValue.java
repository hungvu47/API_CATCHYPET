package com.hungvu.webgym.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "classification_values")
@Data
public class ClassificationValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    @ManyToOne
    @JoinColumn(name = "classification_id")
    @JsonBackReference
    private ClassificationPet classification;

    @ManyToMany(mappedBy = "classifications")
    private List<Appointment> appointments = new ArrayList<>();
}
