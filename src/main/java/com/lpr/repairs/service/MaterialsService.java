package com.lpr.repairs.service;

import com.lpr.repairs.dto.param.create.MaterialCreateParam;
import com.lpr.repairs.dto.param.search.MaterialSearchParam;
import com.lpr.repairs.model.Material;
import com.lpr.repairs.model.MaterialCategory;
import com.lpr.repairs.model.Price;
import com.lpr.repairs.model.TradeMark;
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
  private final TradeMarkRepository tradeMarkRepository;
  private final MaterialsSpec materialsSpec;

  public List<Material> search(MaterialSearchParam searchParam) {
    var specs = materialsSpec.buildSearchSpec(searchParam);
    return specs.map(materialsRepository::findAll).orElseGet(materialsRepository::findAll);
  }

  public Material findById(Long id) {
    return materialsRepository.findById(id).orElseThrow(() -> {
      throw new RuntimeException("Material with given id not exists");
    });
  }

  public Material create(MaterialCreateParam createParam) throws Exception {
    // TODO: add custom exception
    materialsRepository.findByName(createParam.getName()).ifPresent(s -> {
      throw new RuntimeException();
    });
    var tradeMark = getTradeMark(createParam.getTradeMark());
    var materialCategory = getMaterialCategory(createParam.getMaterialCategory());

    return materialsRepository.save(createMaterial(createParam, materialCategory, tradeMark));
  }

  public void remove(Long id) {
    materialsRepository.deleteById(id);
  }

  private TradeMark getTradeMark(String tradeMarkName) {
    return tradeMarkRepository.findByName(tradeMarkName).orElse(TradeMark.builder().name(tradeMarkName).build());
  }

  private MaterialCategory getMaterialCategory(String materialCategoryName) {
    return materialCategoryRepository.findByName(materialCategoryName)
        .orElse(MaterialCategory.builder().name(materialCategoryName).build());
  }

  private Material createMaterial(MaterialCreateParam createParam, MaterialCategory materialCategory, TradeMark tradeMark) {
    return Material.builder()
        .name(createParam.getName())
        .description(createParam.getDescription())
        .price(new Price(createParam.getCost(), createParam.getMeasureUnit(), createParam.getQuantityInOneUnit()))
        .priceLevel(createParam.getPriceLevel())
        .materialCategory(materialCategory)
        .tradeMark(tradeMark)
        .build();
  }

}
