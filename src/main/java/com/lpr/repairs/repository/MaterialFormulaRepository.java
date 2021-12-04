package com.lpr.repairs.repository;

import com.lpr.repairs.model.MaterialsFormula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MaterialFormulaRepository extends JpaRepository<MaterialsFormula, Long>, JpaSpecificationExecutor<MaterialsFormula> {
  List<MaterialsFormula> findByNameIn(Set<String> names);
}
