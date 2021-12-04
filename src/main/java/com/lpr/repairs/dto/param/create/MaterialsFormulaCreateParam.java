package com.lpr.repairs.dto.param.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Set;

import static com.lpr.repairs.common.Constants.Validation.ALPHANUMERIC;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterialsFormulaCreateParam {
  @Max(32)
  @NotBlank
  @Pattern(regexp = ALPHANUMERIC)
  private String name;
  private Set<MaterialCategoryProportionsCreateParam> materialCategoryProportions;

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class MaterialCategoryProportionsCreateParam {
    @Max(10)
    @NotBlank
    private double quantityInOneMeasureUnit;

    @Max(10)
    @NotBlank
    private double ratio;

    @NotBlank
    @Pattern(regexp = ALPHANUMERIC)
    private String materialCategoryName;
  }
}
