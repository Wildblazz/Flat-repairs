package com.lpr.repairs.service;

import com.lpr.repairs.dto.param.create.JobCategoryCreateParam;
import com.lpr.repairs.dto.param.create.MaterialsFormulaCreateParam;
import com.lpr.repairs.model.JobCategory;
import com.lpr.repairs.model.MaterialCategory;
import com.lpr.repairs.model.MaterialsFormula;
import com.lpr.repairs.model.MaterialsFormulaProportions;
import com.lpr.repairs.repository.JobCategoryRepository;
import com.lpr.repairs.repository.MaterialCategoryRepository;
import com.lpr.repairs.repository.MaterialFormulaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobCategoryService {
  private final JobCategoryRepository jobCategoryRepository;
  private final MaterialCategoryRepository materialCategoryRepository;
  private final MaterialFormulaRepository materialFormulaRepository;

  public JobCategory create(JobCategoryCreateParam param) {
    jobCategoryRepository.findByName(param.getName()).ifPresent(obj -> {
      throw new RuntimeException("Job category with given name exists");
    });
    var formulas = getOrCreateMaterialFormulas(param);

    return jobCategoryRepository.save(JobCategory.builder()
        .name(param.getName())
        .requiredMaterialsFormula(getMaterialFormula(param.getFormulaCreateParams(), formulas))
        .build());
  }

  private List<MaterialsFormula> getOrCreateMaterialFormulas(JobCategoryCreateParam param) {
    var formulas = materialFormulaRepository.findByNameIn(
        param.getFormulaCreateParams().stream().map(MaterialsFormulaCreateParam::getName).collect(Collectors.toSet()));
    var existingFormulaNames = formulas.stream().map(MaterialsFormula::getName).collect(Collectors.toList());

    var notExistingFormulaNames = param.getFormulaCreateParams().stream()
        .map(MaterialsFormulaCreateParam::getName)
        .filter(el -> !existingFormulaNames.contains(el))
        .collect(Collectors.toSet());

    var materialCategoriesFromParam = param.getFormulaCreateParams().stream()
        .flatMap(el -> el.getMaterialCategoryProportions().stream())
        .map(MaterialsFormulaCreateParam.MaterialCategoryProportionsCreateParam::getMaterialCategoryName)
        .collect(Collectors.toList());

    var materialCategories = materialCategoryRepository.findByNameIn(materialCategoriesFromParam);
//    create not existing material categories
    materialCategories.addAll(materialCategoriesFromParam.stream()
        .filter(el -> !materialCategories.stream().map(MaterialCategory::getName).collect(Collectors.toList()).contains(el))
        .map(MaterialCategory::new)
        .collect(Collectors.toSet()));

//    create not existing material formulas
    formulas.addAll(notExistingFormulaNames.stream()
        .map(name -> mapToMaterialFormula(name, param.getFormulaCreateParams(), materialCategories)).collect(Collectors.toList()));

    return formulas;
  }

  private List<MaterialsFormula> getMaterialFormula(Set<MaterialsFormulaCreateParam> params, Collection<MaterialsFormula> materialsFormulas) {
    var desiredFormulaNames = params.stream()
        .map(MaterialsFormulaCreateParam::getName)
        .collect(Collectors.toList());
    return materialsFormulas.stream().filter(formula -> desiredFormulaNames.contains(formula.getName())).collect(Collectors.toList());
  }

  private MaterialsFormula mapToMaterialFormula(String name, Collection<MaterialsFormulaCreateParam> formulaCreateParams, Collection<MaterialCategory> materialCategories) {

    var materialCategoryProportions = new HashSet<MaterialsFormulaProportions>();

    formulaCreateParams.stream()
        .filter(el -> el.getName().equals(name))
        .findFirst()
        .map(MaterialsFormulaCreateParam::getMaterialCategoryProportions)
        .orElse(Set.of())
        .forEach(proportions -> {
          materialCategoryProportions.add(new MaterialsFormulaProportions(proportions, materialCategories.stream()
              .filter(materialCategory -> materialCategory.getName().equals(proportions.getMaterialCategoryName())).findAny().get()));
        });

    return MaterialsFormula.builder()
        .name(name)
        .materialCategoryProportions(materialCategoryProportions)
        .build();
  }

  public List<JobCategory> search(List<String> names) {
    if (CollectionUtils.isNotEmpty(names)) {
      return jobCategoryRepository.findByNameIn(names);
    }
    return jobCategoryRepository.findAll();
  }

  public JobCategory findById(Long id) {
    return jobCategoryRepository.findById(id).orElseThrow(() -> {
      throw new RuntimeException("Job category with given id not exists");
    });
  }

  public void remove(Long id) {
    jobCategoryRepository.findById(id).ifPresentOrElse(
        jobCategoryRepository::delete,
        () -> {
          throw new RuntimeException("Job category with given id not exists");
        });
  }
}
