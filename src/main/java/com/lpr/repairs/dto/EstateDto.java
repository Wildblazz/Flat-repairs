package com.lpr.repairs.dto;

import com.lpr.repairs.model.Estate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static com.lpr.repairs.common.Constants.Validation.NUMERIC;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class EstateDto {
  @Pattern(regexp = NUMERIC)
  private long id;

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

  @Max(2)
  @NotBlank
  private int rooms;

  @Max(2)
  @NotBlank
  private int toilets;

  @Max(2)
  @NotBlank
  private int bathrooms;

  @Max(5)
  @NotBlank
  private double floorsArea;

  private boolean isCommonBathroomWithToilet;

  private long userId;


  public EstateDto(Estate estate) {
    id = estate.getId();
    area = estate.getArea();
    kitchenArea = estate.getKitchenArea();
    bathroomArea = estate.getBathroomsArea();
    floorsArea = estate.getFloorArea();
    toiletArea = estate.getToiletsArea();
    bathrooms = estate.getBathrooms();
    toilets = estate.getToilets();
    rooms = estate.getRooms();
    isCommonBathroomWithToilet = estate.isCommonBathroomWithToilet();
    userId = estate.getUser().getId();
  }
}
