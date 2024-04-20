package com.hungvu.webgym.repository;

import com.hungvu.webgym.model.ClassificationPet;
import com.hungvu.webgym.model.ClassificationValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassificationPetRepository extends JpaRepository<ClassificationPet, Long> {
    @Query("SELECT cv FROM ClassificationValue cv WHERE cv.classification = :classificationPet AND cv.value IN :values")
    Optional<ClassificationValue> findByClassificationPetAndValue(ClassificationPet classificationPet, List<String> value);
}
