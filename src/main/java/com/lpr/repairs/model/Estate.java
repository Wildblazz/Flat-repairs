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
  private double area;

  @Column(nullable = false)
  private double kitchenArea;

  @Column(nullable = false)
  private double toiletsArea;

  @Column(nullable = false)
  private double bathroomsArea;

  @Column(nullable = false)
  private double floorArea;

  @Column(nullable = false)
  private int rooms;

  @Column(nullable = false)
  private int toilets;

  @Column(nullable = false)
  private int bathrooms;

  @Column(nullable = false)
  private boolean isCommonBathroomWithToilet;

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
    this.kitchenArea = dto.getKitchenArea();
    this.floorArea = dto.getFloorsArea();
    this.toiletsArea = dto.getToiletArea();
    this.bathroomsArea = dto.getBathroomArea();
    this.toilets = dto.getToilets();
    this.bathrooms = dto.getBathrooms();
    this.isCommonBathroomWithToilet = dto.isCommonBathroomWithToilet();
    this.rooms = dto.getRooms();
//    user = new User(dto.getUser());
  }

  public Estate(EstateCreateParam param, User user) {
    this.area = param.getArea();
    this.kitchenArea = param.getKitchenArea();
    this.toiletsArea = param.getToiletArea();
    this.bathroomsArea = param.getBathroomArea();
    this.floorArea = param.getFloorsArea();
    this.toilets = param.getToilets();
    this.bathrooms = param.getBathrooms();
    this.isCommonBathroomWithToilet = param.isCommonBathroomWithToilet();
    this.rooms = param.getRooms();
    this.user =  user;

  }
}
