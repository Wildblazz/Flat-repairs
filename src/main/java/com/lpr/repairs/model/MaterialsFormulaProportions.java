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

//  Пример. формула цемента М400 на один куб: песок 1365 кг(74.33%), цемент 400 кг(22.66%) = 1765кг
//  Толщина стяжки 5см = 0,05 * 100кв метров = 5 куб метров материала.
//  Чтобы получить колличество материала на 1 кв метр, нужно 1765кг * 5кубов / 100 кв метров = 88.25 кг смеси на 1 кв метр
//  todo избавиться от ручных подсчетов
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
