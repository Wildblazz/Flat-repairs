package com.lpr.repairs.repository;

import com.lpr.repairs.model.Estate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstateRepository extends JpaRepository<Estate, Long> {
  Optional<Estate> findDistinctByUserId(Long userId);

  Optional<Estate> findDistinctByUserName(String userName);
}
