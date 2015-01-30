package com.raw.khipu.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the profile database table.
 * 
 */
@Entity
@Table(name = "profile")
@NamedQuery(name = "Profile.findAll", query = "SELECT p FROM Profile p")
public class Profile implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private int idProfile;

  private int enabled;

  private String name;

  // bi-directional many-to-one association to Profiledetail
  @OneToMany(mappedBy = "profile")
  private List<ProfileDetail> profiledetails;

  // bi-directional many-to-one association to Userprofile
  @OneToMany(mappedBy = "profile")
  private List<UserProfile> userprofiles;

  public Profile() {
  }

  public int getIdProfile() {
    return this.idProfile;
  }

  public void setIdProfile(int idProfile) {
    this.idProfile = idProfile;
  }

  public int getEnabled() {
    return this.enabled;
  }

  public void setEnabled(int enabled) {
    this.enabled = enabled;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<ProfileDetail> getProfiledetails() {
    return this.profiledetails;
  }

  public void setProfiledetails(List<ProfileDetail> profiledetails) {
    this.profiledetails = profiledetails;
  }

  public ProfileDetail addProfiledetail(ProfileDetail profiledetail) {
    getProfiledetails().add(profiledetail);
    profiledetail.setProfile(this);

    return profiledetail;
  }

  public ProfileDetail removeProfiledetail(ProfileDetail profiledetail) {
    getProfiledetails().remove(profiledetail);
    profiledetail.setProfile(null);

    return profiledetail;
  }

  public List<UserProfile> getUserprofiles() {
    return this.userprofiles;
  }

  public void setUserprofiles(List<UserProfile> userprofiles) {
    this.userprofiles = userprofiles;
  }

  public UserProfile addUserprofile(UserProfile userprofile) {
    getUserprofiles().add(userprofile);
    userprofile.setProfile(this);

    return userprofile;
  }

  public UserProfile removeUserprofile(UserProfile userprofile) {
    getUserprofiles().remove(userprofile);
    userprofile.setProfile(null);

    return userprofile;
  }

  @Override
  public String toString() {
    return String.format("%d . %s ", idProfile, name);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Profile other = (Profile) obj;
    if (enabled != other.enabled)
      return false;
    if (idProfile != other.idProfile)
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }

}