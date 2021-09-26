package com.lpr.repairs.dto.param.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static com.lpr.repairs.common.Constants.Validation.NUMERIC;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstateCreateParam {
  @Max(4)
  @NotBlank
  @Pattern(regexp = NUMERIC)
  private int area;

  @Max(3)
  @NotBlank
  @Pattern(regexp = NUMERIC)
  private int rooms;

  @Max(16)
  @NotBlank
  @Pattern(regexp = NUMERIC)
  private long userId;
}
