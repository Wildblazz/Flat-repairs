package com.lpr.repairs.model;

import com.lpr.repairs.model.enums.MeasureUnitEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Data
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Price {
  @NotNull
  private Integer cost;
  @NotNull
  private Integer totalCost;
  @NotNull
  private MeasureUnitEnum measureUnit;
  @NotNull
  private Integer quantityInOneUnit;

  public Price(int cost, MeasureUnitEnum measureUnit, int quantityInOneUnit) {
    this.cost = cost;
    this.totalCost = cost * quantityInOneUnit;
    this.measureUnit = measureUnit;
    this.quantityInOneUnit = quantityInOneUnit;
  }
}
