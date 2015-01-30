package com.raw.khipu.form.profile;

import java.io.Serializable;
import java.util.List;

import com.raw.khipu.dao.ProfileDao;
import com.raw.khipu.dto.Profile;
import com.raw.khipu.form.FormModel;

public class ProfileFormModel extends FormModel implements Serializable {

  private static final long serialVersionUID = 1L;

  // Get Profile List
  public List<Profile> getProfiles() throws Exception {
    List<Profile> list = ProfileDao.getProfileList();
    return list;
  }

}
