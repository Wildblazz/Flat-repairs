package com.lpr.repairs.rest;

import com.lpr.repairs.dto.param.create.JobCreateParam;
import com.lpr.repairs.dto.param.search.JobSearchParam;
import com.lpr.repairs.model.Job;
import com.lpr.repairs.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import static com.lpr.repairs.common.Constants.Validation.ALPHANUMERIC;

@RestController
@RequestMapping("/job")
@RequiredArgsConstructor
public class JobController {

  private final JobService service;

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public List<Job> getAllJobs(
      @RequestParam(required = false) Long id,
      @RequestParam(required = false) @Pattern(regexp = ALPHANUMERIC) String name,
      @RequestParam(required = false) @Pattern(regexp = ALPHANUMERIC) String description,
      @RequestParam(required = false) Long jobCategoryId,
      @RequestParam(required = false) @Pattern(regexp = ALPHANUMERIC) String jobCategoryName) {
    return service.search(new JobSearchParam(id, name, description, jobCategoryId, jobCategoryName));
  }

  @GetMapping(path = "/{jobId}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Job> findById(@PathVariable("jobId") Long jobId) {
    return new ResponseEntity<>(service.findById(jobId), HttpStatus.OK);
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Job> createJob(@Valid @RequestBody JobCreateParam createParam) {
    return new ResponseEntity<>(service.create(createParam), HttpStatus.OK);
  }

  @DeleteMapping()
  @ResponseStatus(HttpStatus.OK)
  public void removeJob(@NotBlank @RequestParam Long id) {
    service.remove(id);
  }
}
