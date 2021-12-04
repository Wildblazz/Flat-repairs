package com.lpr.repairs.repository;

import com.lpr.repairs.model.JobCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface JobCategoryRepository extends JpaRepository<JobCategory, Long> {
  Optional<JobCategory> findByName(String key);

  List<JobCategory> findByNameIn(Collection<String> names);

  List<JobCategory> findByIdIn(Set<Long> ids);
}
