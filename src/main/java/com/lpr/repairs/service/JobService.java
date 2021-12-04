package com.lpr.repairs.service;

import com.lpr.repairs.dto.param.create.JobCreateParam;
import com.lpr.repairs.dto.param.search.JobSearchParam;
import com.lpr.repairs.model.Job;
import com.lpr.repairs.model.JobCategory;
import com.lpr.repairs.repository.JobCategoryRepository;
import com.lpr.repairs.repository.JobRepository;
import com.lpr.repairs.repository.spec.JobSpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobService {
  private final JobRepository jobRepository;
  private final JobCategoryRepository categoryRepository;
  private final JobSpec jobSpec;

  @Transactional
  public Job create(@Valid JobCreateParam createParam) {
    jobRepository.findByName(createParam.getName()).ifPresent(obj -> {
      throw new RuntimeException("Job with given name exists");
    });
    var category = categoryRepository.findByName(createParam.getJobCategory()).orElse(
        categoryRepository.save(
            JobCategory.builder().name(createParam.getJobCategory()).build()));

    return jobRepository.save(new Job(createParam, category));
  }

  public Page<Job> findAll(Pageable pageable) {
    return jobRepository.findAll(pageable);
  }

  @Transactional
  public void remove(Long id) {
    var existing = jobRepository.findById(id);

    jobRepository.deleteById(id);

    existing.map(Job::getJobCategory).ifPresent(category -> {
          var jobs = jobRepository.findByJobCategoryId(category.getId());
          if (CollectionUtils.isEmpty(jobs)) {
            categoryRepository.deleteById(category.getId());
          }
        }
    );
  }

  public List<Job> search(JobSearchParam searchParam) {
   return jobRepository.findAll(jobSpec.buildSearchSpec(searchParam));
  }
}
