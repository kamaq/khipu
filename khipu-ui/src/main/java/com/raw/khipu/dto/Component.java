package com.raw.khipu.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the component database table.
 * 
 */
@Entity
@Table(name = "component")
@NamedQuery(name = "Component.findAll", query = "SELECT c FROM Component c")
public class Component implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1964917475617406189L;

  @Id
  private int idComponent;

  private String className;

  private String description;

  @Column(name = "ImageFileName")
  private String imageFileName;

  private String name;

  // bi-directional many-to-one association to Module
  @ManyToOne
  @JoinColumn(name = "idModule")
  private Module module;

  // bi-directional many-to-one association to Function
  @OneToMany(mappedBy = "component")
  private List<Function> functions;

  public Component() {
  }

  public int getIdComponent() {
    return this.idComponent;
  }

  public void setIdComponent(int idComponent) {
    this.idComponent = idComponent;
  }

  public String getClassName() {
    return this.className;
  }

  public void setClassName(String className) {
    this.className = className;
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

  public Module getModule() {
    return this.module;
  }

  public void setModule(Module module) {
    this.module = module;
  }

  public List<Function> getFunctions() {
    return this.functions;
  }

  public void setFunctions(List<Function> functions) {
    this.functions = functions;
  }

  public Function addFunction(Function function) {
    getFunctions().add(function);
    function.setComponent(this);

    return function;
  }

  public Function removeFunction(Function function) {
    getFunctions().remove(function);
    function.setComponent(null);

    return function;
  }

  @Override
  public String toString() {
    return String.format("%d . %s ", idComponent, description);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Component other = (Component) obj;
    if (className == null) {
      if (other.className != null)
        return false;
    } else if (!className.equals(other.className))
      return false;
    if (description == null) {
      if (other.description != null)
        return false;
    } else if (!description.equals(other.description))
      return false;
    if (idComponent != other.idComponent)
      return false;
    if (imageFileName == null) {
      if (other.imageFileName != null)
        return false;
    } else if (!imageFileName.equals(other.imageFileName))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }

}