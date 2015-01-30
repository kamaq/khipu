package com.raw.khipu.form.module;

import java.util.Map;

import com.raw.khipu.KhipuAppUI;
import com.raw.khipu.form.FormFacade;
import com.raw.khipu.form.FormView;
import com.vaadin.ui.CustomComponent;

public class ModuleFormFacade implements FormFacade {

  private static ModuleFormFacade instance = null;
  private CustomComponent form;

  protected ModuleFormFacade(KhipuAppUI app, Map<String, Object> formParams) throws Exception {
    // View-Model-Presenter pattern for the form
    FormView formView = new ModuleFormViewImpl();
    ModuleFormModel formModel = new ModuleFormModel();
    new ModuleFormPresenter(formView, formModel, app, formParams);

    formView.setFormNameClass(this.getClass().getName());

    setForm((CustomComponent) formView);
  }

  public static ModuleFormFacade getInstance(KhipuAppUI app, Map<String, Object> formParams)
      throws Exception {
    // Singleton pattern
    if (instance == null) {
      return new ModuleFormFacade(app, formParams);
    } else {
      return instance;
    }
  }

  @Override
  public CustomComponent getForm(Map<String, Object> parameters) {
    ((FormView) this.form).setParameters(parameters);
    return form;
  }

  @Override
  public void setForm(CustomComponent form) {
    this.form = form;
  }

}
