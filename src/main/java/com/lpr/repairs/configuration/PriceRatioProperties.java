package com.lpr.repairs.configuration;

import com.lpr.repairs.model.enums.PriorityEnum;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "price.ratio")
public class PriceRatioProperties {
  private Map<PriorityEnum, Double> time;
  private Map<PriorityEnum, Double> skill;
}
