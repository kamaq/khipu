package com.raw.khipu.main;

import java.io.Serializable;

import com.raw.khipu.KhipuAppUI;
import com.raw.khipu.main.MainView.StartViewListener;
import com.raw.khipu.menu.MenuModel;
import com.raw.khipu.menu.MenuPresenter;
import com.raw.khipu.menu.MenuView;
import com.raw.khipu.menu.MenuViewImpl;
import com.raw.khipu.util.FontAwesomePallete;

public class MainPresenter implements StartViewListener, Serializable {

  private static final long serialVersionUID = -4395838599422325034L;
  private MainModel mainModel;
  private MainView mainView;
  private MenuPresenter menuPresenter;

  public MainPresenter(MainModel model, MainView view) throws Exception {
    this.mainModel = model;
    this.mainView = view;
    mainView.addListener(this);

  }

  @Override
  public void buttonClick(String operation) {
    if (operation == "btnMenu") {
      MenuModel menuModel = new MenuModel();
      MenuView menuView;
      try {
        menuView = new MenuViewImpl(mainView.getUIApp(), menuModel.getOptions(mainView.getUIApp()
            .getRegisteredUser(), ""));

        menuPresenter = new MenuPresenter(menuModel, menuView, mainView);
        mainView.setWindow((menuPresenter.getWindow()));
      } catch (Exception e) {
        e.printStackTrace();
      }

    }
    if (operation == "btnHome") {
      mainView.getUIApp().getNavigator().navigateTo(KhipuAppUI.LOGINVIEW);
    }
    if (operation == "btnFind") {
      mainView.getUIApp().setActivateFormCache(true);
    }
    if (operation == "btnConfig") {
      mainView.getUIApp().setActivateFormCache(false);
    }
    if (operation == "btnMessages") {
      FontAwesomePallete pallete = new FontAwesomePallete();
      mainView.setWindow(pallete.getWindow());
    }
    if (operation == "btnPerfil") {
      System.out.println(operation);
    }
  }
}
