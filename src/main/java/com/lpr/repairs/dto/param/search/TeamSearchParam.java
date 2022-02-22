package com.lpr.repairs.dto.param.search;

import com.lpr.repairs.model.enums.PriorityEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TeamSearchParam extends CommonSearchParam {
  private Set<Long> employeeIds;
  private Set<String> employeeLoginIds;
  private Set<String> employeeNames;
  private Set<String> employeeSurnames;
  private Set<PriorityEnum> employeeSkills;

  public TeamSearchParam(Long id, String name, Set<Long> employeeIds, Set<String> employeeLoginIds, Set<String> employeeNames, Set<String> employeeSurnames, Set<PriorityEnum> employeeSkills) {
    super(id, name);
    this.employeeIds = employeeIds;
    this.employeeLoginIds = employeeLoginIds;
    this.employeeNames = employeeNames;
    this.employeeSurnames = employeeSurnames;
    this.employeeSkills = employeeSkills;
  }
}
