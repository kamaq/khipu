package com.raw.khipu.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the userprofile database table.
 * 
 */
@Embeddable
public class UserprofilePK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  @Column(insertable = false, updatable = false)
  private int idProfile;

  @Column(insertable = false, updatable = false)
  private int idUser;

  public UserprofilePK() {
  }

  public int getIdProfile() {
    return this.idProfile;
  }

  public void setIdProfile(int idProfile) {
    this.idProfile = idProfile;
  }

  public int getIdUser() {
    return this.idUser;
  }

  public void setIdUser(int idUser) {
    this.idUser = idUser;
  }

  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof UserprofilePK)) {
      return false;
    }
    UserprofilePK castOther = (UserprofilePK) other;
    return (this.idProfile == castOther.idProfile) && (this.idUser == castOther.idUser);
  }

  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.idProfile;
    hash = hash * prime + this.idUser;

    return hash;
  }

  @Override
  public String toString() {
    return String.format("%d . %d ", idUser, idProfile);
  }
}