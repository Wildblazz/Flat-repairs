package com.lpr.repairs.repository;

import com.lpr.repairs.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long>, JpaSpecificationExecutor<Team> {

  Optional<Team> findByName(String name);

  List<Team> findByNameIn(Collection<String> teams);
}
