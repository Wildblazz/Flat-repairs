package com.lpr.repairs.repository;

import com.lpr.repairs.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepositorySpec extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {
  Optional<Job> findByName(String name);

  List<Job> findByJobCategoryId(long id);
}
