package com.lpr.repairs.service;

import com.lpr.repairs.model.JobCategory;
import com.lpr.repairs.repository.JobCategoryRepository;
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
public class JobCategoryService {
  private final JobCategoryRepository categoryRepository;
  private final JobSpec jobSpec;

  public JobCategory create(@Valid String name) {
    categoryRepository.findByName(name).ifPresent(obj -> {
      throw new RuntimeException("Job category with given name exists");
    });

    return categoryRepository.save(JobCategory.builder().name(name).build());
  }

  public Page<JobCategory> findAll(Pageable pageable) {
    return categoryRepository.findAll(pageable);
  }

  public List<JobCategory> findByNameIn(List<String> name) {
    return categoryRepository.findByNameIn(name);
  }

  public void remove(Long id) {
    categoryRepository.findById(id).ifPresentOrElse(
        categoryRepository::delete,
        () -> {
          throw new RuntimeException("Job category with given id not exists");
        });
  }
}
