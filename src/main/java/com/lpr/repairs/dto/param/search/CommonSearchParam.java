package com.lpr.repairs.dto.param.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

import static com.lpr.repairs.common.Constants.Validation.ALPHABETIC;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonSearchParam {
  private Long id;
  @Pattern(regexp = ALPHABETIC)
  private String name;
}
