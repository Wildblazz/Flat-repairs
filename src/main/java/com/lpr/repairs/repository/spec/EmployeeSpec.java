package com.lpr.repairs.repository.spec;

import com.lpr.repairs.dto.param.search.CommonSearchParam;
import com.lpr.repairs.dto.param.search.EmployeeSearchParam;
import com.lpr.repairs.model.Employee;
import com.lpr.repairs.model.Employee_;
import com.lpr.repairs.model.JobCategory;
import com.lpr.repairs.model.JobCategory_;
import com.lpr.repairs.model.Team;
import com.lpr.repairs.model.Team_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.lpr.repairs.repository.spec.SpecBuilder.SearchOperation.IN;
import static com.lpr.repairs.repository.spec.SpecBuilder.SearchType.AND;
import static com.lpr.repairs.repository.spec.SpecBuilder.addSpec;
import static com.lpr.repairs.repository.spec.SpecBuilder.equalCriteria;
import static com.lpr.repairs.repository.spec.SpecBuilder.getJoinedTable;
import static com.lpr.repairs.repository.spec.SpecBuilder.getNonNullSpecs;
import static com.lpr.repairs.repository.spec.SpecBuilder.likeCriteria;

@Component
public class EmployeeSpec {
  public Specification<Employee> buildSearchSpec(EmployeeSearchParam searchParam) {
    var specList = new ArrayList<Specification<Employee>>();
    var jobCategories = Optional.ofNullable(searchParam.getJobCategories()).orElse(Set.of());
    var teams = Optional.ofNullable(searchParam.getTeams()).orElse(Set.of());
    addSpec(equalCriteria(Employee_.ID, searchParam.getId()), specList);
    addSpec(equalCriteria(Employee_.LOGIN_ID, searchParam.getLoginId()), specList);
    addSpec(likeCriteria(Employee_.NAME, searchParam.getName()), specList);
    addSpec(likeCriteria(Employee_.SURNAME, searchParam.getSurname()), specList);
    addSpec(equalCriteria(Employee_.SKILL, searchParam.getSkill()), specList);
    addSpec(getJoinedTable(Employee.class, JobCategory.class, Employee_.JOB_CATEGORIES, JobCategory_.ID,
        jobCategories.stream().map(CommonSearchParam::getId).collect(Collectors.toList()), IN), specList);

    addSpec(getJoinedTable(Employee.class, JobCategory.class, Employee_.JOB_CATEGORIES, JobCategory_.NAME,
        jobCategories.stream().map(CommonSearchParam::getName).collect(Collectors.toList()), IN), specList);

    addSpec(getJoinedTable(Employee.class, Team.class, Employee_.TEAMS, Team_.ID,
        teams.stream().map(CommonSearchParam::getId).collect(Collectors.toList()), IN), specList);

    addSpec(getJoinedTable(Employee.class, Team.class, Employee_.TEAMS, Team_.NAME,
        teams.stream().map(CommonSearchParam::getName).collect(Collectors.toList()), IN), specList);

    return getNonNullSpecs(specList, AND);
  }
}
