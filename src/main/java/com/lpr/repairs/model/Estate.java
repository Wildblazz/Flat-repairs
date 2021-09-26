package com.lpr.repairs.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lpr.repairs.dto.EstateDto;
import com.lpr.repairs.dto.param.create.EstateCreateParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Table
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"user"})
public class Estate {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(nullable = false)
  private int area;

  @Column(nullable = false)
  private int rooms;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  @CreationTimestamp
  @Column(updatable = false)
  Timestamp dateCreated;

  @UpdateTimestamp
  Timestamp lastModified;

  public Estate(EstateDto dto) {
    this.id = dto.getId();
    this.area = dto.getArea();
    this.rooms = dto.getRooms();
//    user = new User(dto.getUser());
  }

  public Estate(EstateCreateParam createParam, User user) {
    this.area = createParam.getArea();
    this.rooms = createParam.getRooms();
    this.user =  user;

  }
}
