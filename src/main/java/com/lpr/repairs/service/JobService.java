package com.lpr.repairs.service;

import com.lpr.repairs.dto.param.create.JobCreateParam;
import com.lpr.repairs.dto.param.search.JobSearchParam;
import com.lpr.repairs.model.Job;
import com.lpr.repairs.repository.JobCategoryRepository;
import com.lpr.repairs.repository.JobRepository;
import com.lpr.repairs.repository.spec.JobSpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobService {
  private final JobRepository jobRepository;
  private final JobCategoryRepository categoryRepository;
  private final JobSpec jobSpec;

  public Job create(@Valid JobCreateParam createParam) {
    jobRepository.findByName(createParam.getName()).ifPresent(obj -> {
      throw new RuntimeException("Job with given name exists");
    });
    var category = categoryRepository.findByName(createParam.getJobCategory()).orElseThrow(() -> {
      throw new RuntimeException("Job category with given id not exists");
    });

    return jobRepository.save(new Job(createParam, category));
  }

  public Page<Job> findAll(Pageable pageable) {
    return jobRepository.findAll(pageable);
  }

  public void remove(Long id) {
    jobRepository.deleteById(id);
  }

  public List<Job> search(JobSearchParam searchParam) {
    return jobRepository.findAll(jobSpec.buildSearchSpec(searchParam));
  }
}
