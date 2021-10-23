package com.lpr.repairs.dto.param.create;

import com.lpr.repairs.model.Price;
import com.lpr.repairs.model.enums.PriorityEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.lpr.repairs.common.Constants.Validation.ALPHABETIC;
import static com.lpr.repairs.common.Constants.Validation.ALPHANUMERIC;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterialCreateParam {
  @Max(64)
  @NotBlank
  @Pattern(regexp = ALPHANUMERIC)
  private String name;

  @Max(512)
  @Pattern(regexp = ALPHABETIC)
  private String description;

  @Max(32)
  @NotBlank
  @Pattern(regexp = ALPHANUMERIC)
  private String tradeMark;

  @Max(32)
  @NotBlank
  @Pattern(regexp = ALPHANUMERIC)
  private String materialCategory;

  @NotNull
  private Price price;

  @NotNull
  private PriorityEnum priority;
}
