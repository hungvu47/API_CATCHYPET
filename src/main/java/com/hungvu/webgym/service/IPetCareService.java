package com.hungvu.webgym.service;

import com.hungvu.webgym.model.ClassificationPet;
import com.hungvu.webgym.model.PetCare;
import com.hungvu.webgym.request.PetCareRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPetCareService {

    List<PetCare> getAllPetServices();

    PetCare addPetCareService(PetCareRequest request);

    PetCare updatePetService(Long petCareId, PetCareRequest request);

    void deletePetService(Long petCareId);
}
