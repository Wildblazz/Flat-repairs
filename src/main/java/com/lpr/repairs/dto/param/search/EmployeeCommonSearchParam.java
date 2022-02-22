package com.lpr.repairs.dto.param.search;

import com.lpr.repairs.model.enums.PriorityEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCommonSearchParam {
  private Long id;
  private String loginId;
  private String name;
  private String surname;
  private PriorityEnum skill;
}
