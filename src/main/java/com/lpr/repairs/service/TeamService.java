package com.lpr.repairs.service;

import com.lpr.repairs.dto.param.search.TeamSearchParam;
import com.lpr.repairs.model.Team;
import com.lpr.repairs.repository.TeamRepository;
import com.lpr.repairs.repository.spec.TeamSpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.lpr.repairs.common.Util.throwExceptionIfRequired;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamService {
  private final TeamRepository teamRepository;
  private final TeamSpec teamSpec;

  public Team findById(Long teamId) {
    return teamRepository.findById(teamId).orElseThrow(RuntimeException::new);
  }

  public List<Team> search(TeamSearchParam searchParam) {
    var specs = teamSpec.buildSearchSpec(searchParam);
    return specs.map(teamRepository::findAll).orElseGet(teamRepository::findAll);
  }

  public Team create(String name) throws RuntimeException {
    throwExceptionIfRequired(teamRepository.findByName(name.toLowerCase()).isPresent(), "Team already exists!");

    return teamRepository.save(Team.builder().name(name.toLowerCase()).employees(Set.of()).build());
  }

  public void remove(Long id) {
    var existing = findById(id);
    teamRepository.delete(existing);
  }
}
