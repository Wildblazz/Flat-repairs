package com.lpr.repairs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Table
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "estate_id")
  private Estate estate;

  @Enumerated(EnumType.STRING)
  private PriorityEnum qualityLevel;

  @Enumerated(EnumType.STRING)
  private PriorityEnum timePriority;

  @OneToMany(fetch = FetchType.LAZY,
      cascade = CascadeType.ALL)
  private List<Job> jobs;

  @Embedded
  private Price price;

  @Enumerated(EnumType.STRING)
  private PriorityEnum priceLevel;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private MaterialCategory materialCategory;
}
