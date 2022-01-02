package com.lpr.repairs.repository;

import com.lpr.repairs.model.AvgJobPrice;
import com.lpr.repairs.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairsRepository extends JpaRepository<Job, Long> {

  @Query(value =
      "SELECT jobCategory, SUM((avg_cost * mfp)) + (job_price * :repairRatio * e.area) AS total " +
       "FROM estate AS e, (" +
            "SELECT jc.name       AS jobCategory, " +
            "mfp.ratio            AS mfp, " +
            "AVG(m.cost)          AS avg_cost, " +
            "j.total_cost         AS job_price " +
              "FROM job AS j " +
                "INNER JOIN job_category AS jc ON jc.id = j.category_id " +
                "INNER JOIN job_category_required_materials_formula AS jc_mf ON jc_mf.job_category_id = jc.id " +
                "INNER JOIN materials_formula AS mf ON mf.id = jc_mf.materials_formula_id " +
                "INNER JOIN materials_formula_material_category_proportions AS mf_mcp ON mf_mcp.materials_formula_id = mf.id " +
                "INNER JOIN materials_formula_proportions AS mfp ON mfp.id = mf_mcp.material_category_proportions_id " +
                "INNER JOIN material_category AS mc ON mc.id = mfp.material_category_id " +
                "INNER JOIN material AS m ON m.material_category_id = mc.id " +
              "WHERE j.id = :jobId AND m.price_level = :materialPriceLevel " +
            "GROUP BY jc.name, mf.name, mfp.ratio, j.total_cost) as foo " +
       "WHERE e.id = :estateId " +
       "GROUP BY jobCategory, e.area, job_price", nativeQuery = true)
  AvgJobPrice getAvgJobPrice(Long jobId, Long estateId, String materialPriceLevel, double repairRatio );
}
