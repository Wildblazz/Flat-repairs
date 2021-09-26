package com.lpr.repairs.dto.param.search;

import com.lpr.repairs.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.util.Set;

import static com.lpr.repairs.common.Constants.Validation.ALPHABETIC;
import static com.lpr.repairs.common.Constants.Validation.NUMERIC;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamSearchParam {
  @Pattern(regexp = NUMERIC)
  private String id;
  @Pattern(regexp = ALPHABETIC)
  private String name;

  private Set<EmployeeCommonSearchParam> employees;
}
