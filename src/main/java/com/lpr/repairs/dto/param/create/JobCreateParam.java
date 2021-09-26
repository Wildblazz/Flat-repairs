package com.lpr.repairs.dto.param.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static com.lpr.repairs.common.Constants.Validation.ALPHANUMERIC;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobCreateParam {
  @Max(64)
  @NotBlank
  @Pattern(regexp = ALPHANUMERIC)
  private String name;

  @Max(512)
  @NotBlank
  @Pattern(regexp = ALPHANUMERIC)
  private String description;

  @Max(64)
  @NotBlank
  @Pattern(regexp = ALPHANUMERIC)
  private String jobCategory;
}
