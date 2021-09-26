package com.lpr.repairs.rest;

import com.lpr.repairs.dto.param.create.MaterialCreateParam;
import com.lpr.repairs.dto.param.search.MaterialSearchParam;
import com.lpr.repairs.model.Material;
import com.lpr.repairs.service.MaterialsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/materials")
@RequiredArgsConstructor
public class MaterialController {

  private final MaterialsService materialsService;

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public List<Material> getAllMaterials() {
    return materialsService.findAll();
  }

  @PostMapping(path = "/search")
  @ResponseStatus(HttpStatus.OK)
  public List<Material> search(@Valid @RequestBody MaterialSearchParam searchParam) {
    return materialsService.search(searchParam);
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Material> createMaterial(@Valid @RequestBody MaterialCreateParam createParam) throws Exception {
    return new ResponseEntity<>(materialsService.create(createParam), HttpStatus.OK);
  }

  @DeleteMapping()
  @ResponseStatus(HttpStatus.OK)
  public void removeMaterial(@NotBlank @RequestParam Long id) {
    materialsService.remove(id);
  }
}
