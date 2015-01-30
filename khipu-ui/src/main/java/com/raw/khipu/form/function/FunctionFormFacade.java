package com.raw.khipu.form.function;

import java.util.Map;

import com.raw.khipu.KhipuAppUI;
import com.raw.khipu.form.FormFacade;
import com.raw.khipu.form.FormView;
import com.vaadin.ui.CustomComponent;

public class FunctionFormFacade implements FormFacade {

  private static FunctionFormFacade instance = null;
  private CustomComponent form;

  protected FunctionFormFacade(KhipuAppUI app, Map<String, Object> formParams) throws Exception {
    // View-Model-Presenter pattern for the form
    FormView formView = new FunctionFormViewImpl();
    FunctionFormModel formModel = new FunctionFormModel();
    new FunctionFormPresenter(formView, formModel, app, formParams);

    formView.setFormNameClass(this.getClass().getName());

    setForm((CustomComponent) formView);
  }

  public static FunctionFormFacade getInstance(KhipuAppUI app, Map<String, Object> formParams)
      throws Exception {
    // Singleton pattern
    if (instance == null) {
      return new FunctionFormFacade(app, formParams);
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
