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
  private double area;

  @Max(4)
  @NotBlank
  private double kitchenArea;

  @Max(4)
  @NotBlank
  private double toiletArea;


  @Max(4)
  @NotBlank
  private double bathroomArea;

  @Max(4)
  @NotBlank
  private double floorsArea;

  @Max(2)
  @NotBlank
  private int rooms;

  @Max(2)
  @NotBlank
  private int toilets;

  @Max(2)
  @NotBlank
  @Pattern(regexp = NUMERIC)
  private int bathrooms;

  private boolean isCommonBathroomWithToilet;

  @Max(16)
  @NotBlank
  @Pattern(regexp = NUMERIC)
  private long userId;
}
