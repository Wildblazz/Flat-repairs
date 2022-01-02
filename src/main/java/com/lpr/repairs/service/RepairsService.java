package com.lpr.repairs.service;

import com.lpr.repairs.configuration.PriceRatioProperties;
import com.lpr.repairs.dto.param.common.OrderDto;
import com.lpr.repairs.model.Order;
import com.lpr.repairs.model.enums.PriorityEnum;
import com.lpr.repairs.repository.OrderRepository;
import com.lpr.repairs.repository.RepairsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RepairsService {
  private final RepairsRepository repairsRepository;
  private final OrderRepository orderRepository;
  private final PriceRatioProperties priceRatioProperties;

  public double calculate(OrderDto order) {
    var avgJobPrice = Optional.ofNullable(repairsRepository.getAvgJobPrice(
        order.getJobId(),
        order.getEstateId(),
        order.getMaterialsPriceLevel().name(),
        getWorkTimeRatio(order.getRepairTime(), order.getEmployeeQualityLevel())
    ));

    if (avgJobPrice.isPresent()) {
      orderRepository.save(Order.builder()
          .estateId(order.getEstateId())
          .jobId(order.getJobId())
          .repairTimePriority(order.getRepairTime())
          .employeeQualityLevel(order.getEmployeeQualityLevel())
          .materialsPriceLevel(order.getMaterialsPriceLevel())
          .total(avgJobPrice.get().getTotal()).build());

      return avgJobPrice.get().getTotal();
    }

    return -1;
  }

  private double getWorkTimeRatio(PriorityEnum repairTime, PriorityEnum skill) {
    return priceRatioProperties.getTime().get(repairTime) * priceRatioProperties.getTime().get(skill);
  }
}
