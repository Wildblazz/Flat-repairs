package com.lpr.repairs.dto;//package com.lpr.repairs.dto;

import com.lpr.repairs.model.Material;
import com.lpr.repairs.model.MaterialCategory;
import com.lpr.repairs.model.Price;
import com.lpr.repairs.model.PriorityEnum;
import com.lpr.repairs.model.TradeMark;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static com.lpr.repairs.common.Constants.Validation.ALPHABETIC;
import static com.lpr.repairs.common.Constants.Validation.ALPHANUMERIC;
import static com.lpr.repairs.common.Constants.Validation.NUMERIC;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterialsDto {

  @Max(64)
  @NotBlank
  @Pattern(regexp = NUMERIC)
  private long id;

  @Max(64)
  @NotBlank
  @Pattern(regexp = ALPHANUMERIC)
  private String name;

  @Max(512)
  @Pattern(regexp = ALPHABETIC)
  private String description;

  //todo add validation
  private MaterialCategory materialCategory;
  private TradeMark tradeMark;
  private Price price;
  private PriorityEnum priority;

  public MaterialsDto(Material material) {
    id = material.getId();
    name = material.getName();
    materialCategory = material.getMaterialCategory();
    tradeMark = material.getTradeMark();
    price = material.getPrice();
    priority = material.getPriceLevel();
  }
}
