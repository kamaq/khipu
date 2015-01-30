package com.raw.khipu.form;

import java.util.ArrayList;
import java.util.List;

import com.raw.khipu.dao.GeneralDao;
import com.raw.khipu.dao.ProfileDao;
import com.raw.khipu.dto.Component;
import com.raw.khipu.dto.User;

public class FormModel {

  public List<String> getGrantedComponentFunctions(User user, Component component) throws Exception {
    List<String> functions = new ArrayList<String>();

    if (component == null) {
      System.out.println("brnchFormModel component is null ");
    }

    List<Object[]> grantedComponentsFunctions = ProfileDao.getUserGrantedComponentFunction(user,
        component);

    // Get granted component's functions
    for (Object[] function : grantedComponentsFunctions) {
      functions.add((String) function[3].toString().trim());
    }

    return functions;
  }

  // Insert a new record from entity object
  public void insertRecord(Object object) throws Exception {
    GeneralDao.insertRecord(object);
  }

  // Update a record from entity object
  public void updateRecord(Class objectClass, Object objectNew, Object objectId) throws Exception {
    GeneralDao.updateRecord(objectClass, objectNew, objectId);
  }

  // Delete a record from entity object
  public void deleteRecord(Class objectClass, Object objectId) throws Exception {
    GeneralDao.deleteRecord(objectClass, objectId);
  }

}
