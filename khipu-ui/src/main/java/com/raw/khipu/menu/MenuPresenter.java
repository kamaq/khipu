package com.raw.khipu.menu;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.raw.khipu.main.MainView;
import com.raw.khipu.menu.MenuView.MenuViewListener;
import com.vaadin.ui.Window;

public class MenuPresenter implements MenuViewListener, Serializable {

  private static final long serialVersionUID = 3328002925273399709L;
  private MenuView menuView;
  private MenuModel menuModel;
  private MainView mainView;
  private Window window;
  private String optionSelected;

  public MenuPresenter(MenuModel model, MenuView view, MainView _mainView) throws Exception {
    this.menuView = view;
    this.menuModel = model;
    this.mainView = _mainView;
    menuView.addListener(this);

    // Transfering options from model to view
    menuView.setMenuModel(model);
    menuView.setOptions(menuModel.getOptions(mainView.getUIApp().getRegisteredUser(), ""));
    this.window = menuView.buildMainLayout();
  }

  @Override
  public void buttonClick(String operation) {
    try {
      setOptionSelected(operation);

      Map<String, Object> params = new HashMap<String, Object>();
      mainView.openForm(optionSelected, params, null, true);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Window getWindow() {
    return window;
  }

  public String getOptionSelected() {
    return optionSelected;
  }

  public void setOptionSelected(String optionSelected) {
    this.optionSelected = optionSelected;
  }

}
