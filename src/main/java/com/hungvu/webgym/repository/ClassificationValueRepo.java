package com.hungvu.webgym.repository;

import com.hungvu.webgym.model.ClassificationValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificationValueRepo extends JpaRepository<ClassificationValue, Long> {
}
