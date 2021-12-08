package com.lpr.repairs.dto.param.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EmployeeSearchParam extends EmployeeCommonSearchParam {
  private Set<CommonSearchParam> jobCategories;
  private Set<CommonSearchParam> teams;
}


