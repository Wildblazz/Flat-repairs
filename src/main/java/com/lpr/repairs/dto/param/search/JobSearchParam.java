package com.lpr.repairs.dto.param.search;

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
public class JobSearchParam {
  private Long id;

  @Pattern(regexp = ALPHABETIC)
  private String name;

  @Pattern(regexp = ALPHANUMERIC)
  private String description;

  private Long jobCategoryId;

  @Pattern(regexp = ALPHANUMERIC)
  private String jobCategoryName;
}
