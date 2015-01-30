package com.raw.khipu.form.module;

import java.io.Serializable;
import java.util.List;

import com.raw.khipu.dao.ProfileDao;
import com.raw.khipu.dto.Component;
import com.raw.khipu.dto.Module;
import com.raw.khipu.form.FormModel;

public class ModuleFormModel extends FormModel implements Serializable {

  private static final long serialVersionUID = 1L;

  // Get Module List
  public List<Module> getModules() throws Exception {
    List<Module> modules = ProfileDao.getModuleList();
    return modules;
  }

  // Get Componentes from Module entity
  public List<Component> getComponents(Module module) throws Exception {
    List<Component> components = ProfileDao.getComponentList(module);
    return components;
  }

}
