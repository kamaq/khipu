package com.raw.khipu.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.raw.khipu.dto.User;
import com.raw.khipu.dto.UserProfile;
import com.raw.khipu.util.DAOFactory;

public class UserDao {

  // Get registered user with given values
  @SuppressWarnings("unchecked")
  public static ArrayList<User> getRegisterUserList(String userName, String userPassword)
      throws Exception {
    ArrayList<User> UserList;
    EntityManager em = DAOFactory.getEntityManagerFactory().createEntityManager();
    String stmtJPQL = "SELECT u FROM User u WHERE u.name=:userName AND u.password=:userPassword";
    Query q = em.createQuery(stmtJPQL);
    q.setParameter("userName", userName);
    q.setParameter("userPassword", userPassword);
    UserList = new ArrayList<User>(q.getResultList());
    em.clear();
    em.close();
    return UserList;
  }

  public static ArrayList<User> getRegisterUserListFromEmail(String email) throws Exception {
    ArrayList<User> UserList;
    EntityManager em = DAOFactory.getEntityManagerFactory().createEntityManager();
    String stmtJPQL = "SELECT u FROM User u WHERE u.email=:email";
    Query q = em.createQuery(stmtJPQL);
    q.setParameter("email", email);
    UserList = new ArrayList<User>(q.getResultList());
    em.clear();
    em.close();
    return UserList;
  }

  // Get User List
  public static List<User> getUserList() throws Exception {
    EntityManager em = DAOFactory.getEntityManagerFactory().createEntityManager();
    String stmtJPQL = "SELECT u FROM User u";
    Query q = em.createQuery(stmtJPQL);
    List<User> resultList = q.getResultList();
    em.clear();
    em.close();
    return resultList;
  }

  // Get UserProfile List
  public static List<UserProfile> getUserProfileList(User user) throws Exception {
    EntityManager em = DAOFactory.getEntityManagerFactory().createEntityManager();
    String stmtJPQL;
    Query query;
    if (user != null) {
      stmtJPQL = "SELECT up FROM UserProfile up WHERE up.user.idUser=:p_IdUser";
      query = em.createQuery(stmtJPQL);
      query.setParameter("p_IdUser", user.getIdUser());
    } else {
      stmtJPQL = "SELECT up FROM UserProfile up";
      query = em.createQuery(stmtJPQL);
    }
    List<UserProfile> resultList = query.getResultList();
    em.clear();
    em.close();
    return resultList;
  }

}
