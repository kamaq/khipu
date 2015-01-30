package com.raw.khipu.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.raw.khipu.dto.Component;
import com.raw.khipu.dto.Function;
import com.raw.khipu.dto.Module;
import com.raw.khipu.dto.Profile;
import com.raw.khipu.dto.ProfileDetail;
import com.raw.khipu.dto.Property;
import com.raw.khipu.dto.User;
import com.raw.khipu.util.DAOFactory;

public class ProfileDao {

  // Get granted components (component, function) per user
  @SuppressWarnings("unchecked")
  public static List<Object[]> getUserGrantedComponent(User user, String filter) throws Exception {

    EntityManager em = DAOFactory.getEntityManagerFactory().createEntityManager();
    String stmtJPQL = "SELECT distinct pd.function.component.name, pd.function.component.className, "
        + "pd.function.component.imageFileName "
        + "FROM UserProfile up, ProfileDetail pd WHERE up.user.idUser=:p_idUser "
        + "AND pd.profile.idProfile = up.profile.idProfile AND pd.function.component.name LIKE '%"
        + filter + "%' ";

    Query q = em.createQuery(stmtJPQL);
    q.setParameter("p_idUser", user.getIdUser());

    // List<Object[]> for non-entity results
    List<Object[]> resultList = q.getResultList();
    em.clear();
    em.close();
    return resultList;
  }

  // Get granted function-components (component, function) per user
  public static List<Object[]> getUserGrantedComponentFunction(User user, Component component)
      throws Exception {

    EntityManager em = DAOFactory.getEntityManagerFactory().createEntityManager();
    String stmtJPQL = "SELECT pd.function.component.name, pd.function.component.className, "
        + "pd.function.component.imageFileName, pd.function.id.idFunction "
        + "FROM UserProfile up, ProfileDetail pd WHERE up.user.idUser=:p_idUser "
        + "AND pd.profile.idProfile = up.profile.idProfile "
        + "AND pd.function.component.idComponent=:p_idComponent ";

    Query q = em.createQuery(stmtJPQL);
    q.setParameter("p_idUser", user.getIdUser());
    q.setParameter("p_idComponent", component.getIdComponent());
    List<Object[]> resultList = q.getResultList();
    em.clear();
    em.close();
    return resultList;
  }

  // Get Component from name class
  public static Component getComponent(String classNameComponent) throws Exception {
    Component component;
    EntityManager em = DAOFactory.getEntityManagerFactory().createEntityManager();
    String stmtJPQL = "SELECT c FROM Component c WHERE c.className=:p_className ";
    Query q = em.createQuery(stmtJPQL);
    q.setParameter("p_className", classNameComponent);
    component = (com.raw.khipu.dto.Component) q.getSingleResult();
    em.clear();
    em.close();
    return component;
  }

  // Get Module Property List
  public static List<Property> getModulePropertyList() throws Exception {
    EntityManager em = DAOFactory.getEntityManagerFactory().createEntityManager();
    String stmtJPQL = "SELECT m FROM Module m";
    Query q = em.createQuery(stmtJPQL);
    List<Object> resultList = q.getResultList();
    em.clear();
    em.close();

    List<Property> listProperties = new ArrayList<Property>();
    for (Object module : resultList) {
      listProperties.add(new Property(Integer.toString(((Module) module).getIdModule()),
          ((Module) module).getName()));
    }
    return listProperties;
  }

  // Get Component Property List
  public static List<Property> getComponentPropertyList(int idModule) throws Exception {
    EntityManager em = DAOFactory.getEntityManagerFactory().createEntityManager();
    String stmtJPQL = "SELECT c FROM Component c WHERE c.module.idModule=:p_IdModule";
    Query q = em.createQuery(stmtJPQL);
    q.setParameter("p_IdModule", idModule);
    List<Object> resultList = q.getResultList();
    em.clear();
    em.close();

    List<Property> listProperties = new ArrayList<Property>();
    for (Object component : resultList) {
      listProperties.add(new Property(Integer.toString(((Component) component).getIdComponent()),
          ((Component) component).getName()));
    }
    return listProperties;
  }

  // Get Module List
  public static List<Module> getModuleList() throws Exception {
    EntityManager em = DAOFactory.getEntityManagerFactory().createEntityManager();
    String stmtJPQL = "SELECT m FROM Module m";
    Query q = em.createQuery(stmtJPQL);
    List<Module> resultList = q.getResultList();
    em.clear();
    em.close();
    return resultList;
  }

  public static List<Component> getComponentList(Module module) throws Exception {
    EntityManager em = DAOFactory.getEntityManagerFactory().createEntityManager();
    String stmtJPQL;
    Query query;
    if (module != null) {
      stmtJPQL = "SELECT c FROM Component c WHERE c.module.idModule=:p_IdModule";
      query = em.createQuery(stmtJPQL);
      query.setParameter("p_IdModule", module.getIdModule());
    } else {
      stmtJPQL = "SELECT c FROM Component c";
      query = em.createQuery(stmtJPQL);
    }
    List<Component> resultList = query.getResultList();
    em.clear();
    em.close();
    return resultList;
  }

  // get Function List
  public static List<Function> getFunctionList(Component component) throws Exception {
    EntityManager em = DAOFactory.getEntityManagerFactory().createEntityManager();
    String stmtJPQL;
    Query query;
    if (component != null) {
      stmtJPQL = "SELECT f FROM Function f WHERE f.id.idComponent=:p_IdComponent";
      query = em.createQuery(stmtJPQL);
      query.setParameter("p_IdComponent", component.getIdComponent());
    } else {
      stmtJPQL = "SELECT f FROM Function f";
      query = em.createQuery(stmtJPQL);
    }
    List<Function> resultList = query.getResultList();
    em.clear();
    em.close();
    return resultList;
  }

  // Get Profile List
  public static List<Profile> getProfileList() throws Exception {
    EntityManager em = DAOFactory.getEntityManagerFactory().createEntityManager();
    String stmtJPQL = "SELECT p FROM Profile p";
    Query q = em.createQuery(stmtJPQL);
    List<Profile> resultList = q.getResultList();
    em.clear();
    em.close();
    return resultList;
  }

  // Get Profile Detail List
  public static List<ProfileDetail> getProfileDetailList(Profile profile) throws Exception {
    EntityManager em = DAOFactory.getEntityManagerFactory().createEntityManager();
    String stmtJPQL;
    Query query;
    if (profile != null) {
      stmtJPQL = "SELECT c FROM ProfileDetail c WHERE c.profile.idProfile=:p_IdProfile";
      query = em.createQuery(stmtJPQL);
      query.setParameter("p_IdProfile", profile.getIdProfile());
    } else {
      stmtJPQL = "SELECT c FROM ProfileDetail c";
      query = em.createQuery(stmtJPQL);
    }
    List<ProfileDetail> resultList = query.getResultList();
    em.clear();
    em.close();
    return resultList;
  }

}
