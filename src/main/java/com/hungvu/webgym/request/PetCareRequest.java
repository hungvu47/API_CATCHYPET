package com.hungvu.webgym.request;

import com.hungvu.webgym.model.ClassificationPet;
import lombok.Data;

import java.util.List;

@Data
public class PetCareRequest {

    private Long petCareId;
    private String serviceName;
    private double priceService;
    private List<ClassificationPetRequest> classifications;
}
