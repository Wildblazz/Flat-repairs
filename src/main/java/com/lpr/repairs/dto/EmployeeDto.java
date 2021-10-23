//package com.lpr.repairs.dto;
//
//import com.lpr.repairs.model.Employee;
//import com.lpr.repairs.model.enums.PriorityEnum;
//import com.lpr.repairs.model.ServiceCategory;
//import com.lpr.repairs.model.Team;
//import com.lpr.repairs.model.User;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.Column;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//import javax.persistence.OneToMany;
//import javax.validation.constraints.Max;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Pattern;
//
//import java.util.Set;
//
//import static com.lpr.repairs.common.Constants.ALPHABETIC;
//import static com.lpr.repairs.common.Constants.ALPHANUMERIC;
//import static com.lpr.repairs.common.Constants.NUMERIC;
//
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//public class EmployeeDto {
//
//  @Pattern(regexp = NUMERIC)
//  private long id;
//
//  @Max(64)
//  @NotBlank
//  @Pattern(regexp = ALPHABETIC)
//  private String name;
//
//  private PriorityEnum skill;
//
//  @Max(64)
//  @Pattern(regexp = ALPHABETIC)
//  @NotBlank
//  private String surname;
//
//  public EmployeeDto(Employee employee) {
//    id = employee.getId();
//    name = employee.getName();
//    surname = employee.getSurname();
//  }
//
////  @Id
////  @GeneratedValue(strategy = GenerationType.AUTO)
////  private long id;
////
////  @Column(length = 64)
////  private String name;
////
////  @Column(length = 64)
////  private String surname;
////
////  @Enumerated(EnumType.STRING)
////  private PriorityEnum skill;
////
////  @OneToMany
////  private Set<ServiceCategory> serviceCategories;
////
////  @ManyToMany
////  @JoinTable(
////      name = "team_employee",
////      joinColumns = @JoinColumn(name = "employee_id"),
////      inverseJoinColumns = @JoinColumn(name = "team_id"))
////  private Set<Team> teams;
//}
