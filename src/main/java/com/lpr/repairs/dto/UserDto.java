package com.lpr.repairs.dto;

import com.lpr.repairs.model.Estate;
import com.lpr.repairs.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.lpr.repairs.common.Constants.Validation.ALPHABETIC;
import static com.lpr.repairs.common.Constants.Validation.ALPHANUMERIC;
import static com.lpr.repairs.common.Constants.Validation.NUMERIC;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
  @Pattern(regexp = NUMERIC)
  private long id;

  @Max(64)
  @NotBlank
  @Pattern(regexp = ALPHANUMERIC)
  private String username;

  @Max(64)
  @NotBlank
  @Pattern(regexp = ALPHABETIC)
  private String name;

  @Max(64)
  @Pattern(regexp = ALPHABETIC)
  @NotBlank
  private String surname;

  private List<Long> estates = new ArrayList<>();


  public UserDto(User user) {
    id = user.getId();
    username = user.getUserName();
    name = user.getName();
    surname = user.getSurname();
    estates = user.getEstates().stream().map(Estate::getId).collect(Collectors.toList());
  }
}
