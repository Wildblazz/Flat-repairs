package com.lpr.repairs.dto;

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
public class TradeMarkDto {
  @Max(64)
  @NotBlank
  @Pattern(regexp = ALPHANUMERIC)
  private String name;
}
