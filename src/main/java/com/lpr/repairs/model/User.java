package com.lpr.repairs.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lpr.repairs.dto.UserDto;
import com.lpr.repairs.dto.param.create.UserCreateParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"estates"})
@EqualsAndHashCode(exclude="estates")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(unique = true, nullable = false, length = 64)
  private String userName;

  @Column(nullable = false, length = 1024)
  private String passwordHash;

  @Column(length = 64)
  private String name;

  @Column(length = 64)
  private String surname;

  @JsonManagedReference
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Estate> estates = new HashSet<>();

  @CreationTimestamp
  @Column(updatable = false)
  Timestamp dateCreated;

  @UpdateTimestamp
  Timestamp lastModified;

  public User(UserDto userDto) {
    userName = userDto.getUsername();
    name = userDto.getName();
    surname = userDto.getSurname();
//    estates = userDto.getEstates().stream().map(Estate::new).collect(Collectors.toSet());
  }

  public User(UserCreateParam createParam) {
    userName = createParam.getUsername();
    name = createParam.getName();
    surname = createParam.getSurname();
//    todo encrypt the password
    passwordHash = createParam.getPassword();
  }
}
