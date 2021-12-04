package com.lpr.repairs.dto.param.common;

import com.lpr.repairs.model.enums.PriorityEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

  @NotNull
  private Long estateId;

  private Set<Long> jobIds;

  private PriorityEnum materialsPriceLevel;

  private PriorityEnum employeeQualityLevel;

  private PriorityEnum repairTime;
}
