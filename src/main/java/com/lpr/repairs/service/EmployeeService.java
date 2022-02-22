package com.lpr.repairs.service;

import com.lpr.repairs.dto.param.create.EmployeeCreateParam;
import com.lpr.repairs.dto.param.search.EmployeeSearchParam;
import com.lpr.repairs.model.Employee;
import com.lpr.repairs.model.Team;
import com.lpr.repairs.repository.EmployeeRepository;
import com.lpr.repairs.repository.JobCategoryRepository;
import com.lpr.repairs.repository.spec.EmployeeSpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static com.lpr.repairs.common.Util.isCollectionsSizeEqual;
import static com.lpr.repairs.common.Util.throwExceptionIfRequired;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final JobCategoryRepository jobCategoryRepository;
  private final EmployeeSpec employeeSpec;

  public Employee findById(Long estateId) {
    return employeeRepository.findById(estateId).orElseThrow(() -> {
      throw new RuntimeException("Employee with given id not exists");
    });
  }

  public List<Employee> search(EmployeeSearchParam searchParam) {
    var specs = employeeSpec.buildSearchSpec(searchParam);
    return specs.map(employeeRepository::findAll).orElseGet(employeeRepository::findAll);
  }

  public Employee create(EmployeeCreateParam createParam) throws RuntimeException {
    throwExceptionIfRequired(employeeRepository.findByLoginId(createParam.getLoginId()).isPresent(), "Employee already exists!");
    var teams = createParam.getTeams().stream().map(Team::new).collect(Collectors.toSet());
    var jobs = new HashSet<>(jobCategoryRepository.findByNameIn(createParam.getJobCategories()));
    throwExceptionIfRequired(!isCollectionsSizeEqual(createParam.getJobCategories(), jobs), "Cannot find Job Category!");

    return employeeRepository.save(new Employee(createParam, jobs, teams));
  }

  public void remove(Long id) {
    var existing = findById(id);
    employeeRepository.delete(existing);
  }
}
