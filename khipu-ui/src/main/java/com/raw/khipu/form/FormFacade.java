package com.raw.khipu.form;

import java.util.Map;

import com.vaadin.ui.CustomComponent;

public interface FormFacade {

  public CustomComponent getForm(Map<String, Object> parameters);

  public void setForm(CustomComponent form);

}
