package com.lpr.repairs.model;

import com.lpr.repairs.model.enums.MeasureUnitEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Price {
  private int cost;
  private MeasureUnitEnum measureUnit;
}
