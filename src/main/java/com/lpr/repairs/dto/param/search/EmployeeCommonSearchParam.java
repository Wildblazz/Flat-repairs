package com.lpr.repairs.dto.param.search;

import com.lpr.repairs.model.PriorityEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

import static com.lpr.repairs.common.Constants.Validation.ALPHABETIC;
import static com.lpr.repairs.common.Constants.Validation.ALPHANUMERIC;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCommonSearchParam {
  private Long id;
  @Pattern(regexp = ALPHANUMERIC)
  private String loginId;
  @Pattern(regexp = ALPHABETIC)
  private String name;
  @Pattern(regexp = ALPHABETIC)
  private String surname;

  private PriorityEnum skill;
}
