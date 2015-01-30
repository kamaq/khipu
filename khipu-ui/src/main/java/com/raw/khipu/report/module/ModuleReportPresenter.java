package com.raw.khipu.report.module;

import java.io.Serializable;
import java.util.Map;

import com.raw.khipu.KhipuAppUI;
import com.raw.khipu.report.ReportView;
import com.raw.khipu.report.ReportView.ReportViewListener;

public class ModuleReportPresenter implements ReportViewListener, Serializable {

  private static final long serialVersionUID = -6117780258826160344L;
  private ReportView reportView;
  private ModuleReportModel reportModel;

  public ModuleReportPresenter(ReportView view, ModuleReportModel model, KhipuAppUI app,
      Map<String, Object> formParams) throws Exception {
    reportView = view;
    reportModel = model;

    reportView.addListener(this);
    reportView.setApp(app);
    reportView.addListOfValues(0, reportModel.getModules());
    reportView.addListOfValues(1, reportModel.getComponents(0));
    reportView.setGrantedFunctions(reportModel.getGrantedComponentFunctions(reportView.getApp()
        .getRegisteredUser(), reportView.getApp().getCurrentFormComponent()));
  }

  @Override
  public void buttonClick(String operation) {
    if (operation == "btnSomeone") {
      System.out.println(operation);
    }
  }

  @Override
  public void valueChange(String component, String value) {

    if (component.equalsIgnoreCase("cmbModule")) {
      try {
        reportView.addListOfValues(1, reportModel.getComponents(Integer.parseInt(value)));
        reportView.bindListOfValuesWithComponents();
        reportView.populateComponent(1);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

}
