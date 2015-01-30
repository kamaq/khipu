package com.raw.khipu.report.module;

import java.util.Map;

import com.raw.khipu.KhipuAppUI;
import com.raw.khipu.form.FormFacade;
import com.raw.khipu.report.ReportView;
import com.vaadin.ui.CustomComponent;

public class ModuleReportFacade implements FormFacade {

  private static ModuleReportFacade instance = null;
  private CustomComponent form;

  protected ModuleReportFacade(KhipuAppUI app, Map<String, Object> formParams) throws Exception {
    // View-Model-Presenter pattern for the form
    ReportView reportView = new ModuleReportViewImpl();
    ModuleReportModel reportModel = new ModuleReportModel();
    new ModuleReportPresenter(reportView, reportModel, app, formParams);

    setForm((CustomComponent) reportView);
  }

  public static ModuleReportFacade getInstance(KhipuAppUI app, Map<String, Object> formParams)
      throws Exception {
    // Singleton pattern
    if (instance == null) {
      return new ModuleReportFacade(app, formParams);
    } else {
      return instance;
    }
  }

  @Override
  public CustomComponent getForm(Map<String, Object> parameters) {
    // ((ReportView) this.form).setParameters(parameters);
    return form;
  }

  @Override
  public void setForm(CustomComponent form) {
    this.form = form;
  }

}
