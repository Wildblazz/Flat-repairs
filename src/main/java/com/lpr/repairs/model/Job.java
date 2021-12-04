package com.lpr.repairs.model;

import com.lpr.repairs.dto.param.create.JobCreateParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Table
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Job {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(nullable = false, unique = true, length = 64)
  private String name;

  @Column(length = 512)
  private String description;

  @ManyToOne
  @JoinColumn(name = "category_id", referencedColumnName = "id")
  private JobCategory jobCategory;

  public Job(JobCreateParam createParam, JobCategory category) {
    this.name = createParam.getName();
    this.description = createParam.getDescription();
    this.jobCategory =  category;
  }
}
