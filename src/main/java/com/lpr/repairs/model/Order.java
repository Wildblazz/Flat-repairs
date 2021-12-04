package com.lpr.repairs.model;

import com.lpr.repairs.model.enums.PriorityEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

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

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "estate_id", referencedColumnName = "id")
  private Estate estate;

  @Enumerated(EnumType.STRING)
  private PriorityEnum timePriority;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Job> jobs;

  @Enumerated(EnumType.STRING)
  private PriorityEnum materialsPriceLevel;

  @Enumerated(EnumType.STRING)
  private PriorityEnum employeeQualityLevel;

  @Enumerated(EnumType.STRING)
  private PriorityEnum repairTime;

  @Column
  private Double total;
}
