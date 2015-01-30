package com.raw.khipu.dto;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the profiledetail database table.
 * 
 */
@Entity
@Table(name = "profiledetail")
@NamedQuery(name = "ProfileDetail.findAll", query = "SELECT p FROM ProfileDetail p")
public class ProfileDetail implements Serializable {
  private static final long serialVersionUID = 1L;

  @EmbeddedId
  private ProfiledetailPK id;

  private int enabled;

  // bi-directional many-to-one association to Function
  @ManyToOne
  @JoinColumns({ @JoinColumn(name = "idComponent", referencedColumnName = "idComponent"),
      @JoinColumn(name = "idFunction", referencedColumnName = "idFunction") })
  private Function function;

  // bi-directional many-to-one association to Profile
  @ManyToOne
  @JoinColumn(name = "idProfile")
  private Profile profile;

  public ProfileDetail() {
  }

  public ProfiledetailPK getId() {
    return this.id;
  }

  public void setId(ProfiledetailPK id) {
    this.id = id;
  }

  public int getEnabled() {
    return this.enabled;
  }

  public void setEnabled(int enabled) {
    this.enabled = enabled;
  }

  public Function getFunction() {
    return this.function;
  }

  public void setFunction(Function function) {
    this.function = function;
  }

  public Profile getProfile() {
    return this.profile;
  }

  public void setProfile(Profile profile) {
    this.profile = profile;
  }

  @Override
  public String toString() {
    return String.format("%d . %s ", id.getIdComponent(), function.getName());
  }

}