package com.lpr.repairs.model;

import com.lpr.repairs.model.enums.PriorityEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "orders")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column
  private long estateId;

  @Column
  private long jobId;

  @Enumerated(EnumType.STRING)
  private PriorityEnum materialsPriceLevel;

  @Enumerated(EnumType.STRING)
  private PriorityEnum employeeQualityLevel;

  @Enumerated(EnumType.STRING)
  private PriorityEnum repairTimePriority;

  @Column
  private Double total;
}
