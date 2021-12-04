package com.lpr.repairs.repository;

import com.lpr.repairs.model.Material;
import com.lpr.repairs.model.MaterialCategory;
import com.lpr.repairs.model.enums.PriorityEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterialsRepository extends JpaRepository<Material, Long>, JpaSpecificationExecutor<Material> {
  Optional<Material> findByName(String name);
  List<Material> findByMaterialCategoryAndPriceLevel(MaterialCategory materialCategory, PriorityEnum priceLevel);
}
