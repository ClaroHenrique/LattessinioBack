package com.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "users")
public class User {
  @Id @GeneratedValue private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "address")
  private String address;

  @Column(name = "email")
  private String email;

  @Column(name = "tel")
  private String tel;

  @Column(name = "description")
  private String description;

  @OneToMany(targetEntity = Activity.class, cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private List<Activity> activities;

  public boolean hasNameWith(String name) {
    return this.name.toLowerCase().contains(name.toLowerCase());
  }

  public boolean hasActivityWithName(String activityName) {
    return this.activities.stream().filter((Activity a) -> a.hasNameWith(activityName)).count() > 0;
  }
}
