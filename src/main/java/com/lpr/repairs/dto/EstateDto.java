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
  @Pattern(regexp = NUMERIC)
  private int area;

  @Max(3)
  @NotBlank
  @Pattern(regexp = NUMERIC)
  private int rooms;

  private long userId;


  public EstateDto(Estate estate) {
    id = estate.getId();
    area = estate.getArea();
    rooms = estate.getRooms();
    userId = estate.getUser().getId();
  }
}
