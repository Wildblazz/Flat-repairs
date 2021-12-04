package com.lpr.repairs.service;

import com.lpr.repairs.dto.param.common.Order;
import com.lpr.repairs.model.enums.PriorityEnum;
import com.lpr.repairs.repository.JobRepository;
import com.lpr.repairs.repository.MaterialsRepository;
import com.lpr.repairs.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RepairsService {
  private final UserService userService;
  private final EstateService estateService;
  private final JobRepository jobRepository;
  private final MaterialsRepository materialsRepository;
  private final OrderRepository orderRepository;
  private final EmployeeService employeeService;

  public double calculate(Order param) {
//    var total = 0.0;
//    var estate = estateService.findById(param.getEstateId())
//        .orElseThrow(() -> new RuntimeException("Estate does not exist!"));
////    lazy init, user can be null, check !!
//    var user = estate.getUser();
//    var jobs = jobRepository.findByIdIn(param.getJobIds());
//    if (jobs.size() != param.getJobIds().size()) {
//      throw new RuntimeException("Jobs dont exist!");
//    }
//    var materials = new ArrayList<Material>();
//    for (Job j : jobs) {
//      var formula = j.getJobCategory().getRequiredMaterialsFormula();
//      for (MaterialsFormula f : formula) {
//        for (Map.Entry<MaterialCategory, MaterialsFormulaProportions> entry : f.getMaterialCategoryProportions()
//            .entrySet()) {
//         var material = materialsRepository.findByMaterialCategoryAndPriceLevel(entry.getKey(),
//                  param.getEmployeeQualityLevel())
//              .stream().findAny().orElseThrow(() -> new RuntimeException("Material with given params does not exist!"));
//          materials.add(material);
//          total += material.getPrice().getCost() * entry.getValue().getQuantityInOneMeasureUnit() * estate.getArea() *
//              entry.getValue().getRatio();
//        }
//      }
//    }
//
//    var employee = employeeService.getEmployeesByJobAndPriceLevel(
//            jobs.stream().map(Job::getJobCategory).collect(Collectors.toSet()), param.getEmployeeQualityLevel()).stream()
//            .findAny().orElseThrow(() -> new RuntimeException("Employee does not exist!"));
//
//    total += employee.getPrice().getCost() * estate.getArea() * workTimeRatio(param.getRepairTime());
//
//    orderRepository.save(com.lpr.repairs.model.Order.builder()
//        .user(user)
//        .jobsIds(jobs.stream().map(Job::getId).collect(Collectors.toSet()))
//        .estate(estate)
//        .timePriority(param.getRepairTime())
//        .employeeQualityLevel(param.getEmployeeQualityLevel())
//        .materialsPriceLevel(param.getMaterialsPriceLevel())
//        .repairTime(param.getRepairTime())
//        .total(total).build());
//
//    return total;
    return 0;
  }

  private double workTimeRatio(PriorityEnum repairTime){
    return PriorityEnum.HIGH.equals(repairTime) ? 2 : PriorityEnum.MIDDLE.equals(repairTime) ? 1.5 : 1;
  }
}
