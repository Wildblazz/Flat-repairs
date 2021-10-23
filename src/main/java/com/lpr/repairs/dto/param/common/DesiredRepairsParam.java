package com.lpr.repairs.dto.param.common;

import com.lpr.repairs.model.enums.PriorityEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.lpr.repairs.common.Constants.Validation.ALPHANUMERIC;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DesiredRepairsParam {
  @Max(64)
  @NotBlank
  @Pattern(regexp = ALPHANUMERIC)
  private String username;

  @NotNull
  private Long estateId;

  private PriorityEnum materialsPriceLevel;

  private PriorityEnum employeePriceLevel;

  private PriorityEnum repairTime;
}
