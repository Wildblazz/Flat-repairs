package com.lpr.repairs.rest;

import com.lpr.repairs.dto.param.create.MaterialCreateParam;
import com.lpr.repairs.dto.param.search.MaterialSearchParam;
import com.lpr.repairs.model.Material;
import com.lpr.repairs.model.Price;
import com.lpr.repairs.model.enums.PriorityEnum;
import com.lpr.repairs.service.MaterialsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

import static com.lpr.repairs.common.Constants.Validation.ALPHABETIC;
import static com.lpr.repairs.common.Constants.Validation.ALPHANUMERIC;

@RestController
@RequestMapping("/materials")
@RequiredArgsConstructor
public class MaterialController {

  private final MaterialsService materialsService;

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public List<Material> getAllMaterials(
     @RequestParam(required = false) Long id,
     @RequestParam(required = false) @Pattern(regexp = ALPHABETIC) String name,
      @RequestParam(required = false) Long tradeMarkId,
      @RequestParam(required = false) @Pattern(regexp = ALPHANUMERIC) String tradeMarkName,
      @RequestParam(required = false) Long materialCategoryId,
      @RequestParam(required = false) @Pattern(regexp = ALPHANUMERIC) String materialCategoryName,
      @RequestParam(required = false) Integer cost,
      @RequestParam(required = false) PriorityEnum priceLevel
      ) {
    return materialsService.search(
        new MaterialSearchParam(id, name, tradeMarkId, tradeMarkName, materialCategoryId, materialCategoryName,
            Price.builder().cost(cost).build(), priceLevel));
  }

  @GetMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Material> findById(@PathVariable("id") Long id) {
    return new ResponseEntity<>(materialsService.findById(id), HttpStatus.OK);
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
