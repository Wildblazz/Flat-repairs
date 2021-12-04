package com.lpr.repairs.model;

import com.lpr.repairs.dto.param.create.MaterialsFormulaCreateParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Builder
@Table
@AllArgsConstructor
@NoArgsConstructor
public class MaterialsFormulaProportions {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column
  private double quantityInOneMeasureUnit;

  @Column
  private double ratio;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "material_category_id", referencedColumnName = "id")
  private MaterialCategory materialCategory;

  public MaterialsFormulaProportions(MaterialsFormulaCreateParam.MaterialCategoryProportionsCreateParam proportions, MaterialCategory materialCategory) {
    this.quantityInOneMeasureUnit = proportions.getQuantityInOneMeasureUnit();
    this.ratio = proportions.getRatio();
    this.materialCategory =materialCategory;
  }
}
