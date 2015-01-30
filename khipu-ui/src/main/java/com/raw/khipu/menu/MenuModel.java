package com.raw.khipu.menu;

import java.io.Serializable;
import java.util.List;

import com.raw.khipu.dao.ProfileDao;
import com.raw.khipu.dto.Component;
import com.raw.khipu.dto.User;

public class MenuModel implements Serializable {

  private static final long serialVersionUID = 5505423705099886851L;

  // Get granted components user
  public List<Object[]> getOptions(User user, String filter) throws Exception {
    List<Object[]> grantedComponents = ProfileDao.getUserGrantedComponent(user, filter);
    return grantedComponents;
  }

  // Get Component from class name
  public Component getComponent(String classNameComponent) throws Exception {
    return ProfileDao.getComponent(classNameComponent);
  }
}
