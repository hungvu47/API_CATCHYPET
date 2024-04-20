package com.hungvu.webgym.service.impl;

import com.hungvu.webgym.model.ClassificationPet;
import com.hungvu.webgym.model.ClassificationValue;
import com.hungvu.webgym.model.PetCare;
import com.hungvu.webgym.repository.ClassificationPetRepository;
import com.hungvu.webgym.repository.PetCareRepository;
import com.hungvu.webgym.request.ClassificationPetRequest;
import com.hungvu.webgym.request.PetCareRequest;
import com.hungvu.webgym.service.IPetCareService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PetCareServiceImpl implements IPetCareService {

    @Autowired
    private PetCareRepository petCareRepository;

    @Autowired
    private ClassificationPetRepository classificationPetRepository;

    @Override
    public List<PetCare> getAllPetServices() {
        return petCareRepository.findAll();
    }

    @Transactional
    @Override
    public PetCare addPetCareService(PetCareRequest request) {
        PetCare petCare = new PetCare();
        petCare.setServiceName(request.getServiceName());
        petCare.setPriceService(request.getPriceService());

        List<ClassificationPetRequest> classificationsDTO = request.getClassifications();
        for (ClassificationPetRequest classificationDTO : classificationsDTO) {
            ClassificationPet classification = new ClassificationPet();
            classification.setClassificationName(classificationDTO.getClassificationName());
            classification.setPetCare(petCare);

            List<String> valuesDTO = classificationDTO.getValues();
            for (String valueDTO : valuesDTO) {
                ClassificationValue classificationValue = new ClassificationValue();
                classificationValue.setValue(valueDTO);
                classificationValue.setClassification(classification);
                classification.getValues().add(classificationValue);
            }

            petCare.getClassifications().add(classification);
        }

        return petCareRepository.save(petCare);
    }

    @Override
    public PetCare updatePetService(Long petCareId, PetCareRequest request) {
        Optional<PetCare> optionalPetCare = petCareRepository.findById(petCareId);
        if (optionalPetCare.isEmpty()) {
            throw new IllegalStateException("Pet Care Service Not Found");
        }
        PetCare updatePetCare = optionalPetCare.get();
        updatePetCare.setServiceName(request.getServiceName());
        updatePetCare.setPriceService(request.getPriceService());

        petCareRepository.save(updatePetCare);
        return updatePetCare;
    }

    @Override
    public void deletePetService(Long petCareId) {
        petCareRepository.deleteById(petCareId);
    }
}
