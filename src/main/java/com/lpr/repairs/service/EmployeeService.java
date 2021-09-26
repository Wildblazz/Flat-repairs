package com.lpr.repairs.service;

import com.lpr.repairs.dto.EstateDto;
import com.lpr.repairs.dto.param.create.EmployeeCreateParam;
import com.lpr.repairs.dto.param.search.EmployeeSearchParam;
import com.lpr.repairs.model.Employee;
import com.lpr.repairs.model.JobCategory;
import com.lpr.repairs.model.Team;
import com.lpr.repairs.repository.EmployeeRepositorySpec;
import com.lpr.repairs.repository.JobCategoryRepository;
import com.lpr.repairs.repository.TeamRepository;
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

  private final EmployeeRepositorySpec employeeRepository;
  private final JobCategoryRepository jobCategoryRepository;
  private final TeamRepository teamRepository;

  public List<Employee> findAll() {
    return employeeRepository.findAll();
  }

  public Employee findById(Long estateId) {
    return employeeRepository.findById(estateId).orElseThrow(RuntimeException::new);
  }

  public List<Employee> search(EmployeeSearchParam searchParam) {
//    return employeeRepository.findAll(jobSpec.buildSearchSpec(searchParam));
    return null;
  }

  public Employee create(EmployeeCreateParam createParam) throws RuntimeException {
    throwExceptionIfRequired(employeeRepository.findByLoginId(createParam.getLoginId()).isPresent(), "Employee already exists!");
    var teams = new HashSet<>(teamRepository.findByNameIn(createParam.getTeams()));
    throwExceptionIfRequired(!isCollectionsSizeEqual(createParam.getTeams(), teams), "Cannot find given team!");
    var jobs = new HashSet<>(jobCategoryRepository.findByNameIn(createParam.getJobCategories()));
    throwExceptionIfRequired(!isCollectionsSizeEqual(createParam.getJobCategories(), jobs), "Cannot find Job Category!");

    return employeeRepository.save(new Employee(createParam, jobs, teams));
  }

  public void remove(Long id) {
    var existing = findById(id);
    employeeRepository.delete(existing);
  }

//  private boolean isTeamExists(String name) {
//    return teamRepository.findByName(name).isPresent();
//  }
//
//  private boolean isJobCategoryExists(String name) {
//    return jobCategoryRepository.findByName(name).isPresent();
//  }
}
