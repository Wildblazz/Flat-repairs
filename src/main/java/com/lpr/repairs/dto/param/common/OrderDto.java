package com.lpr.repairs.dto.param.common;

import com.lpr.repairs.model.enums.PriorityEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

  @NotNull
  private Long estateId;

  private Long jobId;

  private PriorityEnum materialsPriceLevel;

  private PriorityEnum employeeQualityLevel;

  private PriorityEnum repairTime;

  private double totalPrice;
}
