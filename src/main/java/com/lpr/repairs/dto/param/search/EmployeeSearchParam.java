package com.lpr.repairs.dto.param.search;

import com.lpr.repairs.model.enums.PriorityEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EmployeeSearchParam extends EmployeeCommonSearchParam {
  private Set<Long> jobCategoryIds;
  private Set<String> jobCategoryNames;
  private Set<Long> teamIds;
  private Set<String> teamNames;

  public EmployeeSearchParam(Long id, String loginId, String name, String surname, PriorityEnum skill, Set<Long> jobCategoryIds, Set<String> jobCategoryNames, Set<Long> teamIds, Set<String> teamNames) {
    super(id, loginId, name, surname, skill);
    this.jobCategoryIds = jobCategoryIds;
    this.jobCategoryNames = jobCategoryNames;
    this.teamIds = teamIds;
    this.teamNames = teamNames;
  }
}


