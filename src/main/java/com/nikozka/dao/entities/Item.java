package com.nikozka.dao.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "olio_items")
public class Item {
  @Id UUID id;
  String title;
  String description;
  String city;
  Boolean isListed;
  Boolean isExpired;
  Boolean isArranged;

  public Item() {}

  public Item(
      UUID id,
      String title,
      String description,
      String city,
      Boolean isListed,
      Boolean isExpired,
      Boolean isArranged) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.city = city;
    this.isListed = isListed;
    this.isExpired = isExpired;
    this.isArranged = isArranged;
  }

  public UUID getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public String getCity() {
    return city;
  }

  public Boolean getListed() {
    return isListed;
  }

  public Boolean getExpired() {
    return isExpired;
  }

  public Boolean getArranged() {
    return isArranged;
  }
}
