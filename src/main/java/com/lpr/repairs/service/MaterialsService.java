package com.lpr.repairs.service;

import com.lpr.repairs.dto.param.create.MaterialCreateParam;
import com.lpr.repairs.dto.param.search.MaterialSearchParam;
import com.lpr.repairs.model.JobCategory;
import com.lpr.repairs.model.Material;
import com.lpr.repairs.model.MaterialCategory;
import com.lpr.repairs.model.TradeMark;
import com.lpr.repairs.repository.JobCategoryRepository;
import com.lpr.repairs.repository.MaterialCategoryRepository;
import com.lpr.repairs.repository.MaterialsRepository;
import com.lpr.repairs.repository.TradeMarkRepository;
import com.lpr.repairs.repository.spec.MaterialsSpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MaterialsService {

  private final MaterialsRepository materialsRepository;
  private final MaterialCategoryRepository materialCategoryRepository;
  private final JobCategoryRepository jobCategoryRepository;
  private final TradeMarkRepository tradeMarkRepository;
  private final MaterialsSpec materialsSpec;

  public List<Material> findAll() {
    return materialsRepository.findAll();
  }

  public Material create(MaterialCreateParam createParam) throws Exception {
    // TODO: add custom exception
    materialsRepository.findByName(createParam.getName()).ifPresent(s -> {
      throw new RuntimeException();
    });
    var tradeMark = saveTradeMark(createParam.getTradeMark());
    var materialCategory = saveMaterialCategory(createParam.getMaterialCategory());
    var jobCategory = saveJobCategory(createParam.getJobCategory());

    return materialsRepository.save(createMaterial(createParam, materialCategory, jobCategory, tradeMark));
  }

  public void remove(Long id) {
    materialsRepository.deleteById(id);
  }

  private TradeMark saveTradeMark(String tradeMarkName) {
    return tradeMarkRepository.findByName(tradeMarkName).orElse(tradeMarkRepository.save(TradeMark.builder().name(tradeMarkName).build()));
  }

  private MaterialCategory saveMaterialCategory(String materialCategoryName) {
    return materialCategoryRepository.findByName(materialCategoryName)
        .orElse(materialCategoryRepository.save(MaterialCategory.builder().name(materialCategoryName).build()));
  }

  private JobCategory saveJobCategory(String jobCategoryName) {
    return jobCategoryRepository.findByName(jobCategoryName)
        .orElse(jobCategoryRepository.save(JobCategory.builder().name(jobCategoryName).build()));
  }

  private Material createMaterial(MaterialCreateParam createParam, MaterialCategory materialCategory, JobCategory jobCategory, TradeMark tradeMark) {
    return Material.builder()
        .name(createParam.getName())
        .description(createParam.getDescription())
        .price(createParam.getPrice())
        .priceLevel(createParam.getPriority())
        .materialCategory(materialCategory)
        .jobCategory(jobCategory)
        .tradeMark(tradeMark)
        .build();
  }

  public List<Material> search(MaterialSearchParam searchParam) {
    return materialsRepository.findAll(materialsSpec.buildSearchSpec(searchParam));
  }
}
