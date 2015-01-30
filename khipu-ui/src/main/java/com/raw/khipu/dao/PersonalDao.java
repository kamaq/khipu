package com.raw.khipu.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.raw.khipu.dto.Personal;
import com.raw.khipu.util.DAOFactory;

public class PersonalDao {

  public static List<Personal> getPersonalList() throws Exception {

    EntityManager em = DAOFactory.getEntityManagerFactory().createEntityManager();
    String stmtJPQL = "SELECT p FROM Personal p";

    Query q = em.createQuery(stmtJPQL);
    List<Object> resultList = q.getResultList();
    em.clear();
    em.close();

    List<Personal> personas = new ArrayList<Personal>();
    for (Object p : resultList) {
      personas.add((Personal) p);
      System.out.println("personal : " + ((Personal) p).getNombre());
    }

    return personas;
  }

}
