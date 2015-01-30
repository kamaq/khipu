package com.raw.khipu.report;

import java.util.List;
import java.util.Map;

import com.raw.khipu.KhipuAppUI;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;

public interface ReportView {

  public interface ReportViewListener {

    public void buttonClick(String operation);

    public void valueChange(String component, String value);
  }

  public void addListener(ReportViewListener listener);

  public List<String> getGrantedFunctions();

  public void setGrantedFunctions(List<String> functions);

  public KhipuAppUI getApp();

  public void setApp(KhipuAppUI app);

  public void setRights();

  public String getReportFileName();

  public void setReportFileName(String reportFileName);

  public Map getReportParams();

  public void setReportParams(String paramName, String paramValue);

  public void execute();

  public void showPDFPreview();

  public String getReportFileNameTemporal();

  public void setReportFileNameTemporal(String reportFileNameTemporal);

  public Object getListOfValues();

  public void addListOfValues(int index, Object cmbList);

  public void populateComponents();

  public void populateComponent(int index);

  public void setListOfValueWithComponent(int index, Object list, Component component);

  public GridLayout getParamLayout();

  public void addParameterLayout(Component component);

  public void setLocaleComponents();

  public void bindListOfValuesWithComponents();

  public void setParams();

  public void sendByEmail();
}
