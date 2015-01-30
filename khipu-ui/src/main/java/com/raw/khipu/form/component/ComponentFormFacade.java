package com.raw.khipu.form.component;

import java.util.Map;

import com.raw.khipu.KhipuAppUI;
import com.raw.khipu.form.FormFacade;
import com.raw.khipu.form.FormView;
import com.vaadin.ui.CustomComponent;

public class ComponentFormFacade implements FormFacade {

  private static ComponentFormFacade instance = null;
  private CustomComponent form;

  protected ComponentFormFacade(KhipuAppUI app, Map<String, Object> formParams) throws Exception {
    // View-Model-Presenter pattern for the form
    FormView formView = new ComponentFormViewImpl();
    ComponentFormModel formModel = new ComponentFormModel();
    new ComponentFormPresenter(formView, formModel, app, formParams);

    formView.setFormNameClass(this.getClass().getName());

    setForm((CustomComponent) formView);
  }

  public static ComponentFormFacade getInstance(KhipuAppUI app, Map<String, Object> formParams)
      throws Exception {
    // Singleton pattern
    if (instance == null) {
      return new ComponentFormFacade(app, formParams);
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
