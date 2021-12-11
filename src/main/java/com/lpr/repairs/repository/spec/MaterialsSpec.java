package com.lpr.repairs.repository.spec;

import com.lpr.repairs.dto.param.search.MaterialSearchParam;
import com.lpr.repairs.model.Material;
import com.lpr.repairs.model.MaterialCategory;
import com.lpr.repairs.model.MaterialCategory_;
import com.lpr.repairs.model.Material_;
import com.lpr.repairs.model.Price;
import com.lpr.repairs.model.Price_;
import com.lpr.repairs.model.TradeMark;
import com.lpr.repairs.model.TradeMark_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

import static com.lpr.repairs.repository.spec.SpecBuilder.SearchOperation.EQUALITY;
import static com.lpr.repairs.repository.spec.SpecBuilder.SearchType.AND;
import static com.lpr.repairs.repository.spec.SpecBuilder.addSpec;
import static com.lpr.repairs.repository.spec.SpecBuilder.equalCriteria;
import static com.lpr.repairs.repository.spec.SpecBuilder.getJoinedTable;
import static com.lpr.repairs.repository.spec.SpecBuilder.getNonNullSpecs;
import static com.lpr.repairs.repository.spec.SpecBuilder.likeCriteria;

@Component
public class MaterialsSpec {
  public Specification<Material> buildSearchSpec(MaterialSearchParam searchParam) {
    var specList = new ArrayList<Specification<Material>>();
    addSpec(equalCriteria(Material_.ID, searchParam.getId()), specList);
    addSpec(likeCriteria(Material_.NAME, searchParam.getName()), specList);
    addSpec(equalCriteria(Material_.PRICE_LEVEL, searchParam.getPriceLevel()), specList);
    addSpec(getJoinedTable(Material.class, Price.class, Material_.PRICE, Price_.COST, Optional.ofNullable(searchParam.getPrice()).map(Price::getCost).orElse(null), EQUALITY), specList);
    addSpec(getJoinedTable(Material.class, Price.class, Material_.PRICE, Price_.MEASURE_UNIT, Optional.ofNullable(searchParam.getPrice()).map(Price::getMeasureUnit).orElse(null), EQUALITY), specList);
    addSpec(getJoinedTable(Material.class, TradeMark.class, Material_.TRADE_MARK, TradeMark_.ID, searchParam.getTradeMarkId(), EQUALITY), specList);
    addSpec(getJoinedTable(Material.class, TradeMark.class, Material_.TRADE_MARK, TradeMark_.NAME, searchParam.getTradeMarkName(), EQUALITY), specList);
    addSpec(getJoinedTable(Material.class, MaterialCategory.class, Material_.MATERIAL_CATEGORY, MaterialCategory_.ID, searchParam.getMaterialCategoryId(), EQUALITY), specList);
    addSpec(getJoinedTable(Material.class, MaterialCategory.class, Material_.MATERIAL_CATEGORY, MaterialCategory_.NAME, searchParam.getMaterialCategoryName(), EQUALITY), specList);

    return getNonNullSpecs(specList, AND);
  }
}
