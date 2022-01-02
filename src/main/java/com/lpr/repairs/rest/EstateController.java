package com.lpr.repairs.rest;

import com.lpr.repairs.dto.EstateDto;
import com.lpr.repairs.dto.param.create.EstateCreateParam;
import com.lpr.repairs.service.EstateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("/estate")
@RequiredArgsConstructor
public class EstateController {

  private final EstateService estateService;

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public List<EstateDto> getAllEstates() {
    return estateService.findAll();
  }

  @GetMapping(path = "/{estateId}")
  @ResponseStatus(HttpStatus.OK)
  public EstateDto getByEstateId(@PathVariable("estateId") Long estateId) {
    return estateService.findEstateById(estateId);
  }

  @GetMapping(path = "/byUser")
  @ResponseStatus(HttpStatus.OK)
  public EstateDto getByUserId(
      @RequestParam(value = "userId", required = false) final Long userId,
      @RequestParam(value = "name", required = false) final String name) {
    return estateService.findByUser(userId, name);
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<EstateDto> createEstate(@Valid @RequestBody EstateCreateParam createParam) {
    return new ResponseEntity<>(estateService.create(createParam), HttpStatus.OK);
  }

  @PutMapping()
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<EstateDto> updateEstate(@NotNull @Valid @RequestBody EstateDto dto) {
    return new ResponseEntity<>(estateService.update(dto), HttpStatus.OK);
  }

  @DeleteMapping(path = "/{estateId}")
  @ResponseStatus(HttpStatus.OK)
  public void removeEstate(@NotBlank @PathVariable("estateId") Long estateId) {
    estateService.remove(estateId);
  }
}
