package com.lpr.repairs.repository;

import com.lpr.repairs.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepositorySpec extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

  Optional<Employee> findByLoginId(String loginId);
}
