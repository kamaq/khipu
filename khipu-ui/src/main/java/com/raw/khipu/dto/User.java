package com.raw.khipu.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name = "user")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {

  @Id
  private int idUser;

  private String email;

  private int enabled;

  private String name;

  private String password;

  // bi-directional many-to-one association to Userfunction
  @OneToMany(mappedBy = "user")
  private List<Userfunction> userfunctions;

  // bi-directional many-to-one association to Userprofile
  @OneToMany(mappedBy = "user")
  private List<UserProfile> userprofiles;

  public User() {
  }

  public int getIdUser() {
    return this.idUser;
  }

  public void setIdUser(int idUser) {
    this.idUser = idUser;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
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

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Userfunction> getUserfunctions() {
    return this.userfunctions;
  }

  public void setUserfunctions(List<Userfunction> userfunctions) {
    this.userfunctions = userfunctions;
  }

  public Userfunction addUserfunction(Userfunction userfunction) {
    getUserfunctions().add(userfunction);
    userfunction.setUser(this);

    return userfunction;
  }

  public Userfunction removeUserfunction(Userfunction userfunction) {
    getUserfunctions().remove(userfunction);
    userfunction.setUser(null);

    return userfunction;
  }

  public List<UserProfile> getUserprofiles() {
    return this.userprofiles;
  }

  public void setUserprofiles(List<UserProfile> userprofiles) {
    this.userprofiles = userprofiles;
  }

  public UserProfile addUserprofile(UserProfile userprofile) {
    getUserprofiles().add(userprofile);
    userprofile.setUser(this);

    return userprofile;
  }

  public UserProfile removeUserprofile(UserProfile userprofile) {
    getUserprofiles().remove(userprofile);
    userprofile.setUser(null);

    return userprofile;
  }

  @Override
  public String toString() {
    return String.format("%d . %s ", idUser, name);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    User other = (User) obj;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    if (enabled != other.enabled)
      return false;
    if (idUser != other.idUser)
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (password == null) {
      if (other.password != null)
        return false;
    } else if (!password.equals(other.password))
      return false;
    return true;
  }

}