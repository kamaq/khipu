package com.raw.khipu.login;

import java.io.Serializable;
import java.util.ArrayList;

import com.raw.khipu.dao.UserDao;
import com.raw.khipu.dto.User;

public class LoginModel implements Serializable {

  private static final long serialVersionUID = 5577861223638231098L;

  // Get registered users
  public ArrayList<User> getUserList(String userName, String userPassword) throws Exception {
    return UserDao.getRegisterUserList(userName, userPassword);
  }

  public boolean userHasAccess(String userName, String userPassword) throws Exception {
    boolean estado = false;
    if (getUserList(userName, userPassword).size() == 0) {
      estado = false;
    } else if (getUserList(userName, userPassword).size() > 1) {
      estado = false;
    } else if (getUserList(userName, userPassword).size() == 1) {
      estado = true;
    }
    return estado;
  }

  public User getUser(String userName, String userPassword) throws Exception {
    User user = new User();
    for (User _userDAO : UserDao.getRegisterUserList(userName, userPassword)) {
      user = _userDAO;
    }
    return user;
  }

  public User restoreAccount(String email) throws Exception {
    User user = null;
    for (User _user : UserDao.getRegisterUserListFromEmail(email)) {
      user = _user;
    }
    return user;
  }

}
