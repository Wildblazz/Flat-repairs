package com.lpr.repairs.rest;

import com.lpr.repairs.dto.param.create.JobCategoryCreateParam;
import com.lpr.repairs.model.JobCategory;
import com.lpr.repairs.service.JobCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

import static com.lpr.repairs.common.Constants.Validation.ALPHANUMERIC;

@RestController
@RequestMapping("/jobCategory")
@RequiredArgsConstructor
public class JobCategoryController {

  private final JobCategoryService service;

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public Page<JobCategory> getAllJobCategories(@PageableDefault(sort = "name", size = 25) Pageable pageable) {
    return service.findAll(pageable);
  }

  @PostMapping(path = "/findByNameIn")
  @ResponseStatus(HttpStatus.OK)
  public List<JobCategory> findByNameIn(@RequestBody List<@Pattern(regexp = ALPHANUMERIC)String> names) {
    return service.findByNameIn(names);
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<JobCategory> createJobCategory(@Pattern(regexp = ALPHANUMERIC) @RequestBody @Valid JobCategoryCreateParam param) {
    return new ResponseEntity<>(service.create(param), HttpStatus.OK);
  }


  @DeleteMapping()
  @ResponseStatus(HttpStatus.OK)
  public void removeJobCategory(@NotBlank @RequestParam Long id) {
    service.remove(id);
  }
}
