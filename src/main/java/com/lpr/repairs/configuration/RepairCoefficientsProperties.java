package com.lpr.repairs.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.URI;

@Data
@Component
@ConfigurationProperties(prefix = "repair.coefficients")
public class RepairCoefficientsProperties {
  private double kitchenCoefficient;
  private double toiletCoefficient;
  private double multipleToiletCoefficient;
  private double bathroomCoefficient;
  private double multipleBathroomCoefficient;
}
