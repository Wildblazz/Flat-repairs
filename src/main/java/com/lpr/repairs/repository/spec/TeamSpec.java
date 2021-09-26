package com.lpr.repairs.repository.spec;

import com.lpr.repairs.dto.param.search.EmployeeCommonSearchParam;
import com.lpr.repairs.dto.param.search.TeamSearchParam;
import com.lpr.repairs.model.Employee;
import com.lpr.repairs.model.Employee_;
import com.lpr.repairs.model.Team;
import com.lpr.repairs.model.Team_;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.lpr.repairs.repository.spec.SpecBuilder.SearchOperation.EQUALITY;
import static com.lpr.repairs.repository.spec.SpecBuilder.SearchOperation.IN;
import static com.lpr.repairs.repository.spec.SpecBuilder.SearchType.AND;
import static com.lpr.repairs.repository.spec.SpecBuilder.addSpec;
import static com.lpr.repairs.repository.spec.SpecBuilder.equalCriteria;
import static com.lpr.repairs.repository.spec.SpecBuilder.getJoinedTable;
import static com.lpr.repairs.repository.spec.SpecBuilder.getNonNullSpecs;
import static com.lpr.repairs.repository.spec.SpecBuilder.likeCriteria;

@Component
public class TeamSpec {
  public Specification<Team> buildSearchSpec(TeamSearchParam searchParam) {
    var specList = new ArrayList<Specification<Team>>();
    addSpec(equalCriteria(Team_.ID, searchParam.getId()), specList);
    addSpec(likeCriteria(Team_.NAME, searchParam.getName()), specList);
    if (CollectionUtils.isNotEmpty(searchParam.getEmployees())) {
      addSpec(getJoinedTable(Team.class, Employee.class, Team_.EMPLOYEES, Employee_.ID,
          searchParam.getEmployees().stream().map(EmployeeCommonSearchParam::getId).collect(Collectors.toList()), IN), specList);
      addSpec(getJoinedTable(Team.class, Employee.class, Team_.EMPLOYEES, Employee_.LOGIN_ID,
          searchParam.getEmployees().stream().map(EmployeeCommonSearchParam::getLoginId).collect(Collectors.toList()), IN), specList);
      addSpec(getJoinedTable(Team.class, Employee.class, Team_.EMPLOYEES, Employee_.NAME,
          searchParam.getEmployees().stream().map(EmployeeCommonSearchParam::getName).collect(Collectors.toList()), IN), specList);
      addSpec(getJoinedTable(Team.class, Employee.class, Team_.EMPLOYEES, Employee_.SURNAME,
          searchParam.getEmployees().stream().map(EmployeeCommonSearchParam::getSurname).collect(Collectors.toList()), IN), specList);
      addSpec(getJoinedTable(Team.class, Employee.class, Team_.EMPLOYEES, Employee_.SKILL,
          searchParam.getEmployees().stream().map(EmployeeCommonSearchParam::getSkill).collect(Collectors.toList()), IN), specList);
    }
    return getNonNullSpecs(specList, AND);
  }
}
