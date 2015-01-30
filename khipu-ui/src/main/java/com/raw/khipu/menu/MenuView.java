package com.raw.khipu.menu;

import java.util.List;

import com.vaadin.ui.Window;

public interface MenuView {

  public interface MenuViewListener {

    void buttonClick(String operation);
  }

  public void addListener(MenuViewListener listener);

  public void setOptions(List<Object[]> options);

  public void setMenuModel(MenuModel model);

  public Window buildMainLayout();

}
