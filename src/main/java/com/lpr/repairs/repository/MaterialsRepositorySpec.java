package com.lpr.repairs.repository;

import com.lpr.repairs.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialsRepositorySpec extends JpaRepository<Material, Long>, JpaSpecificationExecutor<Material> {
}
