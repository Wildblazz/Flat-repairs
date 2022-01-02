package com.lpr.repairs.rest;

import com.lpr.repairs.dto.param.create.EmployeeCreateParam;
import com.lpr.repairs.dto.param.search.EmployeeSearchParam;
import com.lpr.repairs.model.Employee;
import com.lpr.repairs.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

  private final EmployeeService service;

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<Employee>> getAllEmployee() {
    return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
  }

  @PostMapping(path = "/search")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<Employee>> search(@Valid @RequestBody EmployeeSearchParam searchParam) {
    return new ResponseEntity<>(service.search(searchParam), HttpStatus.OK);
  }

  @GetMapping(path = "/{employeeId}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Employee> findById(@PathVariable("employeeId") Long employeeId) {
    return new ResponseEntity<>(service.findById(employeeId), HttpStatus.OK);
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Employee> createEmployee(@Valid @RequestBody EmployeeCreateParam createParam) {
    return new ResponseEntity<>(service.create(createParam), HttpStatus.CREATED);
  }

  @DeleteMapping(path = "/{employeeId}")
  @ResponseStatus(HttpStatus.OK)
  public void removeEmployee(@NotBlank @PathVariable("employeeId") Long employeeId) {
    service.remove(employeeId);
  }
}
