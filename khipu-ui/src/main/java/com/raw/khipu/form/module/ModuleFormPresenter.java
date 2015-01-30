package com.raw.khipu.form.module;

import java.io.Serializable;
import java.util.Map;

import com.raw.khipu.KhipuAppUI;
import com.raw.khipu.form.FormView;
import com.raw.khipu.form.FormView.FormViewListener;
import com.raw.khipu.form.FormViewImpl;

public class ModuleFormPresenter implements FormViewListener, Serializable {

  private static final long serialVersionUID = -6117780258826160344L;
  private FormView formView;
  private ModuleFormModel formModel;

  public ModuleFormPresenter(FormView view, ModuleFormModel model, KhipuAppUI app,
      Map<String, Object> formParams) throws Exception {
    formView = view;
    formModel = model;

    formView.addListener(this);
    formView.setApp(app);
    formView.setFormModel(formModel);
    formView.setParameters(formParams);
    formView.init();
  }

  @Override
  public void buttonClick(String operation) {
    if (operation.equalsIgnoreCase("btnCreate")) {
      formView.openEditWindow(FormViewImpl.CREATE_MODE);
    }
    if (operation.equalsIgnoreCase("btnModify")) {
      formView.openEditWindow(FormViewImpl.MODIFY_MODE);
    }
    if (operation.equalsIgnoreCase("btnEnable")) {
      formView.openEditWindow(FormViewImpl.CHANGE_STATUS_MODE);
    }
    if (operation.equalsIgnoreCase("btnDelete")) {
      formView.openEditWindow(FormViewImpl.DELETE_MODE);
    }
  }

}