package com.lpr.repairs.repository;

import com.lpr.repairs.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterialsRepository extends JpaRepository<Material, Long> {
  Optional<Material> findByName(String name);
}
