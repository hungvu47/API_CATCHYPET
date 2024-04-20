package com.hungvu.webgym.repository;

import com.hungvu.webgym.model.PetCare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetCareRepository extends JpaRepository<PetCare, Long> {
}
