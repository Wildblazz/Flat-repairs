package com.lpr.repairs.dto.param.search;

import com.lpr.repairs.model.JobCategory;
import com.lpr.repairs.model.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EmployeeSearchParam extends EmployeeCommonSearchParam {
  private Set<JobCategory> jobCategories;
  private Set<Team> teams;
}


