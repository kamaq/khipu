package com.raw.khipu.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.raw.khipu.dao.GeneralDao;
import com.raw.khipu.dto.User;

public class DAOFactory {
  private static final EntityManagerFactory emf;

  static {
    try {
      emf = Persistence.createEntityManagerFactory("KhipuApp");
    } catch (Throwable e) {
      System.err.println("falló la creación inicial" + e);
      throw new ExceptionInInitializerError();
    }

  }

  public static EntityManagerFactory getEntityManagerFactory() {
    return emf;
  }

  public static void test1() {
    EntityManager em = DAOFactory.getEntityManagerFactory().createEntityManager();
    String stmt = "SELECT z FROM User z";
    Query query = em.createQuery(stmt);
    ArrayList<User> lista = new ArrayList<User>(query.getResultList());
    System.out.println(lista.size());

    for (User usuario : lista) {
      System.out.println(usuario.getName() + " : " + usuario.getPassword());
    }
  }

  public static void test2() {
    EntityManager em = DAOFactory.getEntityManagerFactory().createEntityManager();

    String stmt = "SELECT distinct pd.function.component.idComponent, pd.function.component.className, pd.function.component.imageFileName, pd.function.id.idFunction "
        + "FROM UserProfile up, ProfileDetail pd WHERE up.user.idUser = 1 "
        + "AND pd.profile.idProfile = up.profile.idProfile "
        + "AND pd.function.component.idComponent = 5";

    Query query = em.createQuery(stmt);
    List<Object[]> resultList = query.getResultList();
    for (Object[] result : resultList) {
      System.out.println(result[0] + " " + result[1] + " " + result[2] + " " + result[3]);
    }

    System.out.println(resultList.size());
  }

  public static void test3() {
    EntityManager em = DAOFactory.getEntityManagerFactory().createEntityManager();
    String stmtJPQL = "SELECT distinct pd.function.component.name, pd.function.component.className, "
        + "pd.function.component.imageFileName "
        + "FROM UserProfile up, ProfileDetail pd WHERE up.user.idUser=:p_idUser "
        + "AND pd.profile.idProfile = up.profile.idProfile AND pd.function.component.name LIKE '%"
        + "pro" + "%' ";

    Query q = em.createQuery(stmtJPQL);
    q.setParameter("p_idUser", 1);
    // q.setParameter("p_filter", "");

    // List<Object[]> for non-entity results
    List<Object[]> resultList = q.getResultList();
    em.clear();
    em.close();

    Map<String, String> map = new HashMap<String, String>(resultList.size());
    for (Object[] result : resultList) {
      System.out.println((String) result[0]);
      map.put((String) result[2], (String) result[0]);
    }
  }

  public static void main(String[] args) {
    // test1();
    // test2();
    // test3();
    GeneralDao.execProcedureJPA();
    // GeneralDao.execProcedureJDBC();
  }

}
