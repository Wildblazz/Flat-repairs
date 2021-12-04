package com.lpr.repairs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Builder
@Table
@AllArgsConstructor
@NoArgsConstructor
public class JobCategory {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(nullable = false, unique = true)
  private String name;

  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(
      name = "job_category_required_materials_formula",
      joinColumns = @JoinColumn(name = "job_category_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "materials_formula_id", referencedColumnName = "id"))
  private List<MaterialsFormula> requiredMaterialsFormula;

//  @ManyToMany(mappedBy = "jobCategories")
//  private Set<Employee> employee;
}
