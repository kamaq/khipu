package com.raw.khipu.form.userProfile;

import java.util.Map;

import com.raw.khipu.KhipuAppUI;
import com.raw.khipu.form.FormFacade;
import com.raw.khipu.form.FormView;
import com.vaadin.ui.CustomComponent;

public class UserProfileFormFacade implements FormFacade {

  private static UserProfileFormFacade instance = null;
  private CustomComponent form;

  protected UserProfileFormFacade(KhipuAppUI app, Map<String, Object> formParams) throws Exception {
    // View-Model-Presenter pattern for the form
    FormView formView = new UserProfileFormViewImpl();
    UserProfileFormModel formModel = new UserProfileFormModel();
    new UserProfileFormPresenter(formView, formModel, app, formParams);

    formView.setFormNameClass(this.getClass().getName());

    setForm((CustomComponent) formView);
  }

  public static UserProfileFormFacade getInstance(KhipuAppUI app, Map<String, Object> formParams)
      throws Exception {
    // Singleton pattern
    if (instance == null) {
      return new UserProfileFormFacade(app, formParams);
    } else {
      return instance;
    }
  }

  @Override
  public CustomComponent getForm(Map<String, Object> parameters) {
    return form;
  }

  @Override
  public void setForm(CustomComponent form) {
    this.form = form;
  }

}
