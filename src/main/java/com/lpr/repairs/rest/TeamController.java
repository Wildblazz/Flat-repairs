package com.lpr.repairs.rest;

import com.lpr.repairs.dto.param.search.TeamSearchParam;
import com.lpr.repairs.model.Team;
import com.lpr.repairs.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

import static com.lpr.repairs.common.Constants.Validation.ALPHANUMERIC;

@Validated
@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {

  private final TeamService service;

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<Team>> getAllTeams() {
    return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
  }

  @GetMapping(path = "/{teamId}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Team> findById(@PathVariable("teamId") Long teamId) {
    return new ResponseEntity<>(service.findById(teamId), HttpStatus.OK);
  }

  @PostMapping(path = "/search")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<Team>> search(@Valid @RequestBody TeamSearchParam searchParam) {
    return new ResponseEntity<>(service.search(searchParam), HttpStatus.OK);
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Team> createTeam(@RequestBody @Max(64) @NotBlank @Pattern(regexp = ALPHANUMERIC) String name) {
    return new ResponseEntity<>(service.create(name), HttpStatus.CREATED);
  }

  @DeleteMapping(path = "/{teamId}")
  @ResponseStatus(HttpStatus.OK)
  public void removeTeam(@NotBlank @PathVariable("teamId") Long teamId) {
    service.remove(teamId);
  }
}
