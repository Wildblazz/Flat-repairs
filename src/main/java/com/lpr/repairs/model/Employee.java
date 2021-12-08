package com.lpr.repairs.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.lpr.repairs.dto.param.create.EmployeeCreateParam;
import com.lpr.repairs.model.enums.PriorityEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.util.Set;

import static com.lpr.repairs.common.Constants.Validation.ALPHABETIC;
import static com.lpr.repairs.common.Constants.Validation.ALPHANUMERIC;

@Data
@Entity
@Builder
@Table
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "teams")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id")
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(length = 64)
  @Pattern(regexp = ALPHANUMERIC)
  private String loginId;

  @Column(length = 64)
  @Pattern(regexp = ALPHABETIC)
  private String name;

  @Column(length = 64)
  @Pattern(regexp = ALPHABETIC)
  private String surname;

  @Enumerated(EnumType.STRING)
  private PriorityEnum skill;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "employee_job_categories",
      joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "job_category_id", referencedColumnName = "id"))
  private Set<JobCategory> jobCategories;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JoinTable(
      name = "team_employee",
      joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id"))
  private Set<Team> teams;

  public Employee(EmployeeCreateParam createParam, Set<JobCategory> jobCategories, Set<Team> teams) {
    this.loginId = createParam.getLoginId();
    this.name = createParam.getName();
    this.surname = createParam.getSurname();
    this.skill = createParam.getSkill();
    this.jobCategories = jobCategories;
    this.teams = teams;
  }
}
