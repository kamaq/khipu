package com.raw.khipu.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import com.raw.khipu.util.DAOFactory;

public class GeneralDao {

  // Insert a new record from entity object
  public static void insertRecord(Object object) throws Exception {
    EntityManager em = DAOFactory.getEntityManagerFactory().createEntityManager();
    em.getTransaction().begin();
    em.persist(object);
    em.getTransaction().commit();
  }

  // Update a record from entity object
  public static void updateRecord(Class objectClass, Object objectNew, Object objectId)
      throws Exception {
    EntityManager em = DAOFactory.getEntityManagerFactory().createEntityManager();
    em.getTransaction().begin();

    if (em.find(objectClass, objectId) == null) {
      throw new IllegalArgumentException("Unknown record id");
    } else {
      em.merge(objectNew);
    }
    em.getTransaction().commit();
  }

  // Delete a record from entity object
  public static void deleteRecord(Class objectClass, Object objectId) throws Exception {
    EntityManager em = DAOFactory.getEntityManagerFactory().createEntityManager();
    em.getTransaction().begin();

    Object entityToBeRemove = em.find(objectClass, objectId);
    if (entityToBeRemove == null) {
      throw new IllegalArgumentException("Unknown record id");
    } else {
      em.remove(entityToBeRemove);
    }
    em.getTransaction().commit();
  }

  // execute a stored procedure from JPA
  public static void execProcedureJPA() {
    EntityManager em = DAOFactory.getEntityManagerFactory().createEntityManager();
    StoredProcedureQuery sp = em.createStoredProcedureQuery("USP_UserList");

    sp.registerStoredProcedureParameter("p_codigo", Integer.class, ParameterMode.IN);
    // sp.registerStoredProcedureParameter("tax", Double.class, ParameterMode.OUT);
    sp.setParameter("p_codigo", 3);

    sp.execute();
    // get result
    // Double tax = (Double)sp.getOutputParameterValue("tax");

    List<Object[]> result = sp.getResultList();
    System.out.println("col1  col2");
    for (Object[] row : result) {
      System.out.println(row[0].toString() + "     " + row[1].toString());
    }
  }

  // execute a stored procedure from JDBC
  public static void execProcedureJDBC() {
    try {
      EntityManager em = DAOFactory.getEntityManagerFactory().createEntityManager();
      em.getTransaction().begin();

      Connection con = (Connection) em.unwrap(java.sql.Connection.class);
      String query = "CALL USP_UserList(3)";
      ResultSet rs = con.createStatement().executeQuery(query);
      ResultSetMetaData rsmd = rs.getMetaData();

      em.getTransaction().commit();
      int colCount = rsmd.getColumnCount();

      System.out.println("Presentacion tabular");
      System.out.println(rsmd.getColumnName(1) + "     " + rsmd.getColumnName(2));
      while (rs.next()) {
        System.out.println(rs.getObject(1) + "          " + rs.getObject(2));
      }

      rs.beforeFirst();
      System.out.println("\nPresentacion vertical");
      while (rs.next()) {
        for (int i = 1; i <= colCount; i++) {
          String columnName = rsmd.getColumnName(i);
          Object value = rs.getObject(i);
          System.out.println(columnName.toString() + "     " + value.toString());
        }
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
