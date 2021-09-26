package com.lpr.repairs.repository;

import com.lpr.repairs.model.MaterialCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaterialCategoryRepository extends JpaRepository<MaterialCategory, Long> {
  Optional<MaterialCategory> findByName(String name);
}
