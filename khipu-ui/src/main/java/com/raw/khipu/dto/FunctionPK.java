package com.raw.khipu.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the function database table.
 * 
 */
@Embeddable
public class FunctionPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  @Column(insertable = false, updatable = false)
  private int idComponent;

  private String idFunction;

  public FunctionPK() {
  }

  public int getIdComponent() {
    return this.idComponent;
  }

  public void setIdComponent(int idComponent) {
    this.idComponent = idComponent;
  }

  public String getIdFunction() {
    return this.idFunction;
  }

  public void setIdFunction(String idFunction) {
    this.idFunction = idFunction;
  }

  @Override
  public String toString() {
    return this.idFunction;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + idComponent;
    result = prime * result + ((idFunction == null) ? 0 : idFunction.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    FunctionPK other = (FunctionPK) obj;
    if (idComponent != other.idComponent)
      return false;
    if (idFunction == null) {
      if (other.idFunction != null)
        return false;
    } else if (!idFunction.equals(other.idFunction))
      return false;
    return true;
  }

}