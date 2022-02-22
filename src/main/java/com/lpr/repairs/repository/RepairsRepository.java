package com.lpr.repairs.repository;

import com.lpr.repairs.model.AvgJobPrice;
import com.lpr.repairs.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairsRepository extends JpaRepository<Job, Long> {

  @Query(value =
      "SELECT jobCategory, SUM(materials_cost) + (job_cost * :repairRatio * area) AS total " +
       "FROM  (" +
            "SELECT jc.name AS jobCategory, " +
            "j.cost AS job_cost, " +
            "e.area AS area, " +
            "AVG(m.cost * mfp.quantity_in_one_measure_unit  * mfp.ratio * e.area) AS materials_cost " +
              "FROM estate AS e, job AS j " +
                "INNER JOIN job_category AS jc ON jc.id = j.category_id " +
                "INNER JOIN job_category_required_materials_formula AS jc_mf ON jc_mf.job_category_id = jc.id " +
                "INNER JOIN materials_formula AS mf ON mf.id = jc_mf.materials_formula_id " +
                "INNER JOIN materials_formula_material_category_proportions AS mf_mcp ON mf_mcp.materials_formula_id = mf.id " +
                "INNER JOIN materials_formula_proportions AS mfp ON mfp.id = mf_mcp.material_category_proportions_id " +
                "INNER JOIN material_category AS mc ON mc.id = mfp.material_category_id " +
                "INNER JOIN material AS m ON m.material_category_id = mc.id " +
              "WHERE j.id = :jobId AND m.price_level = :materialPriceLevel AND e.id = :estateId " +
            "GROUP BY jc.name, mf.name, mfp.ratio, mfp.id, j.total_cost, e.area, j.cost, e.area) as foo " +
       "GROUP BY jobCategory, job_cost, area", nativeQuery = true)
  AvgJobPrice getAvgJobPrice(Long jobId, Long estateId, String materialPriceLevel, double repairRatio );
}
