package com.raw.khipu.form.profileDetail;

import java.io.Serializable;
import java.util.List;

import com.raw.khipu.dao.ProfileDao;
import com.raw.khipu.dto.Component;
import com.raw.khipu.dto.Function;
import com.raw.khipu.dto.Module;
import com.raw.khipu.dto.Profile;
import com.raw.khipu.dto.ProfileDetail;
import com.raw.khipu.form.FormModel;

public class ProfileDetailFormModel extends FormModel implements Serializable {

  private static final long serialVersionUID = 1L;

  // Get Profile List
  public List<Profile> getProfiles() throws Exception {
    List<Profile> list = ProfileDao.getProfileList();
    return list;
  }

  // Get Profile Detail from Profile entity
  public List<ProfileDetail> getProfileDetails(Profile profile) throws Exception {
    List<ProfileDetail> list = ProfileDao.getProfileDetailList(profile);
    return list;
  }

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

  // Get Function List
  public List<Function> getFunctions(Component component) throws Exception {
    List<Function> functions = ProfileDao.getFunctionList(component);
    return functions;
  }

}
