package com.lpr.repairs.repository.spec;

import com.lpr.repairs.dto.param.search.TeamSearchParam;
import com.lpr.repairs.model.Employee;
import com.lpr.repairs.model.Employee_;
import com.lpr.repairs.model.Team;
import com.lpr.repairs.model.Team_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

import static com.lpr.repairs.common.Util.getNullSafeCollection;
import static com.lpr.repairs.repository.spec.SpecBuilder.SearchOperation.IN;
import static com.lpr.repairs.repository.spec.SpecBuilder.SearchType.AND;
import static com.lpr.repairs.repository.spec.SpecBuilder.addSpec;
import static com.lpr.repairs.repository.spec.SpecBuilder.equalCriteria;
import static com.lpr.repairs.repository.spec.SpecBuilder.getJoinedTable;
import static com.lpr.repairs.repository.spec.SpecBuilder.getNonNullSpecs;
import static com.lpr.repairs.repository.spec.SpecBuilder.likeCriteria;

@Component
public class TeamSpec {
  public Optional<Specification<Team>> buildSearchSpec(TeamSearchParam searchParam) {
    var specList = new ArrayList<Specification<Team>>();
    addSpec(equalCriteria(Team_.ID, searchParam.getId()), specList);
    addSpec(likeCriteria(Team_.NAME, searchParam.getName()), specList);
      addSpec(getJoinedTable(Team.class, Employee.class, Team_.EMPLOYEES, Employee_.ID,
          getNullSafeCollection(searchParam.getEmployeeIds()), IN), specList);
      addSpec(getJoinedTable(Team.class, Employee.class, Team_.EMPLOYEES, Employee_.LOGIN_ID,
          getNullSafeCollection(searchParam.getEmployeeLoginIds()), IN), specList);
      addSpec(getJoinedTable(Team.class, Employee.class, Team_.EMPLOYEES, Employee_.NAME,
          getNullSafeCollection(searchParam.getEmployeeNames()), IN), specList);
      addSpec(getJoinedTable(Team.class, Employee.class, Team_.EMPLOYEES, Employee_.SURNAME,
          getNullSafeCollection(searchParam.getEmployeeSurnames()), IN), specList);
      addSpec(getJoinedTable(Team.class, Employee.class, Team_.EMPLOYEES, Employee_.SKILL,
          getNullSafeCollection(searchParam.getEmployeeSkills()), IN), specList);
    return specList.isEmpty() ? Optional.empty() : Optional.of(getNonNullSpecs(specList, AND));
  }
}
