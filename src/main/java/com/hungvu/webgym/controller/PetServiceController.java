package com.hungvu.webgym.controller;

import com.hungvu.webgym.model.PetCare;
import com.hungvu.webgym.request.PetCareRequest;
import com.hungvu.webgym.service.IPetCareService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pet-service")
@RequiredArgsConstructor
public class PetServiceController {

    private final IPetCareService petCareService;

    @PostMapping("/create")
    public ResponseEntity<?> addPetService(@RequestBody PetCareRequest request) {
        PetCare addedPetCare = petCareService.addPetCareService(request);
        return ResponseEntity.ok(addedPetCare);
    }

    @PutMapping("/update/{petCareId}")
    public ResponseEntity<String> updatePetService(@RequestBody PetCareRequest request,
                                                   @PathVariable Long petCareId) {
        petCareService.updatePetService(petCareId, request);
        return ResponseEntity.ok("Cập nhật thành công");
    }

    @DeleteMapping("/delete/{petCareId}")
    public ResponseEntity<String> deletePetService(@PathVariable Long petCareId) {
        petCareService.deletePetService(petCareId);
        return ResponseEntity.ok("Xóa thành công");
    }

    @GetMapping("/all-services")
    public ResponseEntity<List<PetCare>> getALlPetServices() {
        return ResponseEntity.ok(petCareService.getAllPetServices());
    }
}
