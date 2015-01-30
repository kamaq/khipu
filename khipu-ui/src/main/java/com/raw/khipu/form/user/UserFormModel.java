package com.raw.khipu.form.user;

import java.io.Serializable;
import java.util.List;

import com.raw.khipu.dao.ProfileDao;
import com.raw.khipu.dao.UserDao;
import com.raw.khipu.dto.Profile;
import com.raw.khipu.dto.User;
import com.raw.khipu.form.FormModel;

public class UserFormModel extends FormModel implements Serializable {

  private static final long serialVersionUID = 1L;

  // Get User List
  public List<User> getUsers() throws Exception {
    List<User> list = UserDao.getUserList();
    return list;
  }

  // Get Profile List
  public List<Profile> getProfiles() throws Exception {
    List<Profile> list = ProfileDao.getProfileList();
    return list;
  }

}
