package com.raw.khipu.main;

import java.util.Map;

import com.raw.khipu.KhipuAppUI;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public interface MainView {

  public interface StartViewListener {

    void buttonClick(String operation);

  }

  public void addListener(StartViewListener listener);

  public void setForm(CustomComponent form);

  public void addForm(CustomComponent form);

  public void setWindow(Window window);

  public VerticalLayout getFormContainer();

  public KhipuAppUI getUIApp();

  public CustomComponent openForm(String formClassName, Map<String, Object> formParams,
      String viewParams, boolean truncateForm) throws Exception;
}
