package com.raw.khipu.form.profileDetail;

import java.util.Map;

import com.raw.khipu.KhipuAppUI;
import com.raw.khipu.form.FormFacade;
import com.raw.khipu.form.FormView;
import com.vaadin.ui.CustomComponent;

public class ProfileDetailFormFacade implements FormFacade {

  private static ProfileDetailFormFacade instance = null;
  private CustomComponent form;

  protected ProfileDetailFormFacade(KhipuAppUI app, Map<String, Object> formParams)
      throws Exception {
    // View-Model-Presenter pattern for the form
    FormView formView = new ProfileDetailFormViewImpl();
    ProfileDetailFormModel formModel = new ProfileDetailFormModel();
    new ProfileDetailFormPresenter(formView, formModel, app, formParams);

    formView.setFormNameClass(this.getClass().getName());

    setForm((CustomComponent) formView);
  }

  public static ProfileDetailFormFacade getInstance(KhipuAppUI app, Map<String, Object> formParams)
      throws Exception {
    // Singleton pattern
    if (instance == null) {
      return new ProfileDetailFormFacade(app, formParams);
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
