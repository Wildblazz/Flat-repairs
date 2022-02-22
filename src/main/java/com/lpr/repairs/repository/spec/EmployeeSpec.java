package com.lpr.repairs.repository.spec;

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

import static com.lpr.repairs.common.Util.getNullSafeCollection;
import static com.lpr.repairs.repository.spec.SpecBuilder.SearchOperation.IN;
import static com.lpr.repairs.repository.spec.SpecBuilder.SearchType.AND;
import static com.lpr.repairs.repository.spec.SpecBuilder.addSpec;
import static com.lpr.repairs.repository.spec.SpecBuilder.equalCriteria;
import static com.lpr.repairs.repository.spec.SpecBuilder.getJoinedTable;
import static com.lpr.repairs.repository.spec.SpecBuilder.getNonNullSpecs;
import static com.lpr.repairs.repository.spec.SpecBuilder.likeCriteria;

@Component
public class EmployeeSpec {
  public Optional<Specification<Employee>> buildSearchSpec(EmployeeSearchParam searchParam) {
    var specList = new ArrayList<Specification<Employee>>();
    addSpec(equalCriteria(Employee_.ID, searchParam.getId()), specList);
    addSpec(equalCriteria(Employee_.LOGIN_ID, searchParam.getLoginId()), specList);
    addSpec(likeCriteria(Employee_.NAME, searchParam.getName()), specList);
    addSpec(likeCriteria(Employee_.SURNAME, searchParam.getSurname()), specList);
    addSpec(equalCriteria(Employee_.SKILL, searchParam.getSkill()), specList);
    addSpec(getJoinedTable(Employee.class, JobCategory.class, Employee_.JOB_CATEGORIES, JobCategory_.ID,
        getNullSafeCollection(searchParam.getJobCategoryIds()), IN), specList);
    addSpec(getJoinedTable(Employee.class, JobCategory.class, Employee_.JOB_CATEGORIES, JobCategory_.NAME,
        getNullSafeCollection(searchParam.getJobCategoryNames()), IN), specList);
    addSpec(getJoinedTable(Employee.class, Team.class, Employee_.TEAMS, Team_.ID,
        getNullSafeCollection(searchParam.getTeamIds()), IN), specList);
    addSpec(getJoinedTable(Employee.class, Team.class, Employee_.TEAMS, Team_.NAME,
        getNullSafeCollection(searchParam.getTeamNames()), IN), specList);

    return specList.isEmpty() ? Optional.empty() : Optional.of(getNonNullSpecs(specList, AND));
  }
}
