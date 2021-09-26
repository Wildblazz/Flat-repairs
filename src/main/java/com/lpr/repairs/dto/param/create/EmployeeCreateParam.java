package com.lpr.repairs.dto.param.create;

import com.lpr.repairs.model.PriorityEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

import static com.lpr.repairs.common.Constants.Validation.ALPHANUMERIC;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCreateParam {
  @Max(64)
  @NotBlank
  @Pattern(regexp = ALPHANUMERIC)
  private String name;

  @Max(64)
  @NotBlank
  @Pattern(regexp = ALPHANUMERIC)
  private String loginId;

  @NotBlank
  private String surname;

  private PriorityEnum skill;

  private Set<String> jobCategories = new HashSet<>();

  private Set<String> teams = new HashSet<>();
}
