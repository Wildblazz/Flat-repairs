package com.lpr.repairs.rest;

import com.lpr.repairs.dto.param.create.EmployeeCreateParam;
import com.lpr.repairs.dto.param.search.EmployeeSearchParam;
import com.lpr.repairs.model.Employee;
import com.lpr.repairs.model.enums.PriorityEnum;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Set;

import static com.lpr.repairs.common.Constants.Validation.ALPHANUMERIC;

@Validated
@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

  private final EmployeeService service;

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<Employee>> getAllEmployee(
      @RequestParam(required = false) Long id,
      @RequestParam(required = false) @Pattern(regexp = ALPHANUMERIC) String loginId,
      @RequestParam(required = false) @Pattern(regexp = ALPHANUMERIC) String name,
      @RequestParam(required = false) @Pattern(regexp = ALPHANUMERIC) String surname,
      @RequestParam(required = false) PriorityEnum skill,
      @RequestParam(required = false) Set<Long> jobCategoryIds,
      @RequestParam(required = false) Set<@Pattern(regexp = ALPHANUMERIC) String> jobCategoryNames,
      @RequestParam(required = false) Set<Long> teamIds,
      @RequestParam(required = false) Set<@Pattern(regexp = ALPHANUMERIC) String> teamNames
  ) {
    return new ResponseEntity<>(service.search(new EmployeeSearchParam(id, loginId, name, surname, skill, jobCategoryIds, jobCategoryNames, teamIds, teamNames)), HttpStatus.OK);
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
