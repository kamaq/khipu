package com.raw.khipu.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the profiledetail database table.
 * 
 */
@Embeddable
public class ProfiledetailPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  @Column(insertable = false, updatable = false)
  private int idProfile;

  @Column(insertable = false, updatable = false)
  private int idComponent;

  @Column(insertable = false, updatable = false)
  private String idFunction;

  public ProfiledetailPK() {
  }

  public int getIdProfile() {
    return this.idProfile;
  }

  public void setIdProfile(int idProfile) {
    this.idProfile = idProfile;
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

  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof ProfiledetailPK)) {
      return false;
    }
    ProfiledetailPK castOther = (ProfiledetailPK) other;
    return (this.idProfile == castOther.idProfile) && (this.idComponent == castOther.idComponent)
        && this.idFunction.equals(castOther.idFunction);
  }

  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.idProfile;
    hash = hash * prime + this.idComponent;
    hash = hash * prime + this.idFunction.hashCode();

    return hash;
  }

  @Override
  public String toString() {
    return String.format("%d . %s ", idComponent, idFunction);
  }

}
