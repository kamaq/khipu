package com.raw.khipu.report.module;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.raw.khipu.dao.ProfileDao;
import com.raw.khipu.dto.Component;
import com.raw.khipu.dto.Property;
import com.raw.khipu.dto.User;

public class ModuleReportModel implements Serializable {

  private static final long serialVersionUID = -125170029901193391L;

  public List<String> getGrantedComponentFunctions(User user, Component component) throws Exception {
    List<String> functions = new ArrayList<String>();

    List<Object[]> grantedComponentsFunctions = ProfileDao.getUserGrantedComponentFunction(user,
        component);

    // Get granted component's functions
    for (Object[] function : grantedComponentsFunctions) {
      functions.add((String) function[3].toString().trim());
    }

    return functions;
  }

  // Get Modules Property List
  public List<Property> getModules() throws Exception {
    List<Property> modules = ProfileDao.getModulePropertyList();
    return modules;
  }

  // Get Component Property List
  public List<Property> getComponents(int idModule) throws Exception {
    List<Property> components = ProfileDao.getComponentPropertyList(idModule);
    return components;
  }

}
