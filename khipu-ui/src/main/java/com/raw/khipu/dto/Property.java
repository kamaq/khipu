package com.raw.khipu.dto;

import java.io.Serializable;

public class Property implements Serializable {
  private static final long serialVersionUID = -4919057861574279551L;
  private String id;
  private String name;

  public Property(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return String.format("%s . %s", id, name);

  }
}
