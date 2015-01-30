package com.raw.khipu.report.module;

import com.raw.khipu.i18n.AppMessages;
import com.raw.khipu.report.ReportViewImpl;
import com.vaadin.ui.ComboBox;

public class ModuleReportViewImpl extends ReportViewImpl {

  private static final long serialVersionUID = 5011463172197746695L;
  private ComboBox cmbModule;
  private ComboBox cmbComponent;

  public ModuleReportViewImpl() {
    // This name must be equal to report definition file (*.jrxml) without the extension.
    setReportFileName("RepComponent");

    cmbModule = new ComboBox(null);
    cmbModule.setData("cmbModule");
    cmbModule.addValueChangeListener(this);
    cmbModule.setImmediate(true);

    cmbComponent = new ComboBox(null);
    cmbComponent.setData("cmbComponent");
    cmbComponent.addValueChangeListener(this);
    cmbComponent.setImmediate(true);

    addParameterLayout(cmbModule);
    addParameterLayout(cmbComponent);
  }

  @Override
  public void setLocaleComponents() {
    setFormTitle(getApp().getMessageLocale(AppMessages.ReportModuleTitle));

    cmbModule.setCaption(getApp().getMessageLocale(AppMessages.Module));
    cmbComponent.setCaption(getApp().getMessageLocale(AppMessages.Component));
  }

  @Override
  public void bindListOfValuesWithComponents() {
    setListOfValueWithComponent(0, getListOfValues().get(0), cmbModule);
    setListOfValueWithComponent(1, getListOfValues().get(1), cmbComponent);
  }

  @Override
  public void setParams() {
    this.setReportParams("P_IDMODULE", cmbModule.getValue().toString());
  }

}
