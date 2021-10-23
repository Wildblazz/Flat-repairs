package com.lpr.repairs.service;

import com.lpr.repairs.configuration.RepairCoefficientsProperties;
import com.lpr.repairs.dto.param.common.DesiredRepairsParam;
import com.lpr.repairs.model.Estate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RepairsService {
  private final UserService userService;
  private final EstateService estateService;
  private final RepairCoefficientsProperties coefficientsProps;

  public double calculate(DesiredRepairsParam param) {
    var user = userService.findUserByUserName(param.getUsername()).orElseThrow(() -> new RuntimeException("User does not exist!"));
    var estate = estateService.findById(param.getEstateId()).orElseThrow(() -> new RuntimeException("Estate does not exist!"));
    var repairsCoefficient = calculateApproximateRepairsRatioCoefficient(estate);

    return 0;
  }

  //this method will calculate the coefficient, multiplying by which we can get the approximate cost of repairing one square meter of the apartment.
  // It is needed because repairing a room usually costs less than repairing a bathroom or kitchen.
  public double calculateApproximateRepairsRatioCoefficient(Estate estate) {
  //  simple formula... there should be more parameters to be considered
    var bathroomAndToiletsCalculatedArea = estate.isCommonBathroomWithToilet() ?
            (estate.getToiletsArea() * coefficientsProps.getToiletCoefficient()) + (estate.getBathroomsArea() * coefficientsProps.getBathroomCoefficient()) :
            (estate.getToiletsArea() * coefficientsProps.getToiletCoefficient() * (estate.getToilets() * coefficientsProps.getMultipleToiletCoefficient())) +
            (estate.getBathroomsArea() * coefficientsProps.getBathroomCoefficient() * (estate.getBathrooms() * coefficientsProps.getMultipleBathroomCoefficient()));

   return (bathroomAndToiletsCalculatedArea + (estate.getKitchenArea() * coefficientsProps.getKitchenCoefficient()) +
          (estate.getArea() - estate.getKitchenArea() -  estate.getBathroomsArea() - estate.getToiletsArea())) /
          estate.getArea();
  }
}
