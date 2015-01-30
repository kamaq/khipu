package com.raw.khipu.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the userprofile database table.
 * 
 */
@Entity
@Table(name = "userprofile")
@NamedQuery(name = "UserProfile.findAll", query = "SELECT u FROM UserProfile u")
public class UserProfile implements Serializable {
  private static final long serialVersionUID = 1L;

  @EmbeddedId
  private UserprofilePK id;

  @Temporal(TemporalType.TIMESTAMP)
  private Date dateInsert;

  @Temporal(TemporalType.TIMESTAMP)
  private Date dateUpdate;

  private int userInsert;

  private int userUpdate;

  // bi-directional many-to-one association to Profile
  @ManyToOne
  @JoinColumn(name = "idProfile")
  private Profile profile;

  // bi-directional many-to-one association to User
  @ManyToOne
  @JoinColumn(name = "idUser")
  private User user;

  public UserProfile() {
  }

  public UserprofilePK getId() {
    return this.id;
  }

  public void setId(UserprofilePK id) {
    this.id = id;
  }

  public Date getDateInsert() {
    return this.dateInsert;
  }

  public void setDateInsert(Date dateInsert) {
    this.dateInsert = dateInsert;
  }

  public Date getDateUpdate() {
    return this.dateUpdate;
  }

  public void setDateUpdate(Date dateUpdate) {
    this.dateUpdate = dateUpdate;
  }

  public int getUserInsert() {
    return this.userInsert;
  }

  public void setUserInsert(int userInsert) {
    this.userInsert = userInsert;
  }

  public int getUserUpdate() {
    return this.userUpdate;
  }

  public void setUserUpdate(int userUpdate) {
    this.userUpdate = userUpdate;
  }

  public Profile getProfile() {
    return this.profile;
  }

  public void setProfile(Profile profile) {
    this.profile = profile;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return String.format("%d . %s ", user.getIdUser(), user.getName());
  }

}