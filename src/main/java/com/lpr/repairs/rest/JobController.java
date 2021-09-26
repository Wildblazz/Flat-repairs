package com.lpr.repairs.rest;

import com.lpr.repairs.dto.param.create.JobCreateParam;
import com.lpr.repairs.dto.param.search.JobSearchParam;
import com.lpr.repairs.model.Job;
import com.lpr.repairs.service.JobService;
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
import java.util.List;

@RestController
@RequestMapping("/job")
@RequiredArgsConstructor
public class JobController {

  private final JobService service;

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public Page<Job> getAllJobs(@PageableDefault(sort = "name", size = 25) Pageable pageable) {
    return service.findAll(pageable);
  }

  @PostMapping(path = "/search")
  @ResponseStatus(HttpStatus.OK)
  public List<Job> search(@Valid @RequestBody JobSearchParam searchParam) {
    return service.search(searchParam);
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
