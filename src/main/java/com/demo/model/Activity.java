package com.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Entity
@Table(name = "activities")
public class Activity {
  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "category")
  private String category;

  public boolean hasNameWith(String name){
    return this.name.toLowerCase().contains(name.toLowerCase());
  }
}
