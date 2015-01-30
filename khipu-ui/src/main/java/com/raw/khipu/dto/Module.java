package com.raw.khipu.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the module database table.
 * 
 */
@Entity
@Table(name = "module")
@NamedQuery(name = "Module.findAll", query = "SELECT m FROM Module m")
public class Module implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private int idModule;

  private String description;

  @Column(name = "ImageFileName")
  private String imageFileName;

  private String name;

  // bi-directional many-to-one association to Component
  @OneToMany(mappedBy = "module")
  private List<Component> components;

  public Module() {
  }

  public int getIdModule() {
    return this.idModule;
  }

  public void setIdModule(int idModule) {
    this.idModule = idModule;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImageFileName() {
    return this.imageFileName;
  }

  public void setImageFileName(String imageFileName) {
    this.imageFileName = imageFileName;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Component> getComponents() {
    return this.components;
  }

  public void setComponents(List<Component> components) {
    this.components = components;
  }

  public Component addComponent(Component component) {
    getComponents().add(component);
    component.setModule(this);

    return component;
  }

  public Component removeComponent(Component component) {
    getComponents().remove(component);
    component.setModule(null);

    return component;
  }

  @Override
  public String toString() {
    return String.format("%d . %s ", idModule, name);
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Module other = (Module) obj;
    if (description == null) {
      if (other.description != null) {
        return false;
      }
    } else if (!description.equals(other.description)) {
      return false;
    }
    if (idModule != other.idModule) {
      return false;
    }
    if (imageFileName == null) {
      if (other.imageFileName != null) {
        return false;
      }
    } else if (!imageFileName.equals(other.imageFileName)) {
      return false;
    }
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }
    return true;
  }

}