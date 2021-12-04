package com.lpr.repairs.dto.param.search;

import com.lpr.repairs.model.Price;
import com.lpr.repairs.model.enums.PriorityEnum;
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
public class MaterialSearchParam {
  private Long id;

  @Pattern(regexp = ALPHABETIC)
  private String name;

  private Long tradeMarkId;

  @Pattern(regexp = ALPHANUMERIC)
  private String tradeMarkName;

  private Long materialCategoryId;

  @Pattern(regexp = ALPHANUMERIC)
  private String materialCategoryName;

  private Long jobCategoryId;

  @Pattern(regexp = ALPHANUMERIC)
  private String jobCategoryName;

  private Price price;

  private PriorityEnum priceLevel;
}
