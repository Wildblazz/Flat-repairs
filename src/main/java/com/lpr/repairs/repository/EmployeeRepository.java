package com.lpr.repairs.repository;

import com.lpr.repairs.model.Employee;
import com.lpr.repairs.model.JobCategory;
import com.lpr.repairs.model.enums.PriorityEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

  Optional<Employee> findByLoginId(String loginId);

  List<Employee> findByJobCategoriesInAndSkill(Set<JobCategory> jobCategory, PriorityEnum skill);
}
