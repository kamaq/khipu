package com.raw.khipu.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the function database table.
 * 
 */
@Entity
@Table(name = "function")
@NamedQuery(name = "Function.findAll", query = "SELECT f FROM Function f")
public class Function implements Serializable {
  private static final long serialVersionUID = 1L;

  @EmbeddedId
  private FunctionPK id;

  private String name;

  // bi-directional many-to-one association to Component
  @ManyToOne
  @JoinColumn(name = "idComponent")
  private Component component;

  // bi-directional many-to-one association to Profiledetail
  @OneToMany(mappedBy = "function")
  private List<ProfileDetail> profiledetails;

  // bi-directional many-to-one association to Userfunction
  @OneToMany(mappedBy = "function")
  private List<Userfunction> userfunctions;

  public Function() {
  }

  public FunctionPK getId() {
    return this.id;
  }

  public void setId(FunctionPK id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Component getComponent() {
    return this.component;
  }

  public void setComponent(Component component) {
    this.component = component;
  }

  public List<ProfileDetail> getProfiledetails() {
    return this.profiledetails;
  }

  public void setProfiledetails(List<ProfileDetail> profiledetails) {
    this.profiledetails = profiledetails;
  }

  public ProfileDetail addProfiledetail(ProfileDetail profiledetail) {
    getProfiledetails().add(profiledetail);
    profiledetail.setFunction(this);

    return profiledetail;
  }

  public ProfileDetail removeProfiledetail(ProfileDetail profiledetail) {
    getProfiledetails().remove(profiledetail);
    profiledetail.setFunction(null);

    return profiledetail;
  }

  public List<Userfunction> getUserfunctions() {
    return this.userfunctions;
  }

  public void setUserfunctions(List<Userfunction> userfunctions) {
    this.userfunctions = userfunctions;
  }

  public Userfunction addUserfunction(Userfunction userfunction) {
    getUserfunctions().add(userfunction);
    userfunction.setFunction(this);

    return userfunction;
  }

  public Userfunction removeUserfunction(Userfunction userfunction) {
    getUserfunctions().remove(userfunction);
    userfunction.setFunction(null);

    return userfunction;
  }

  @Override
  public String toString() {
    return String
        .format("%s / %s / %s", component.getModule().getName(), component.getName(), name);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Function other = (Function) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }

}