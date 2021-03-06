package com.raw.khipu.form.function;

import java.io.Serializable;
import java.util.Map;

import com.raw.khipu.KhipuAppUI;
import com.raw.khipu.form.FormView;
import com.raw.khipu.form.FormView.FormViewListener;
import com.raw.khipu.form.FormViewImpl;

public class FunctionFormPresenter implements FormViewListener, Serializable {

  private static final long serialVersionUID = -6117780258826160344L;
  private FormView formView;
  private FunctionFormModel formModel;

  public FunctionFormPresenter(FormView view, FunctionFormModel model, KhipuAppUI app,
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
    if (operation.equalsIgnoreCase("btnFunctions")) {
      formView.openEditWindow(FormViewImpl.FIRST_DETAIL_MODE);
    }
  }

}