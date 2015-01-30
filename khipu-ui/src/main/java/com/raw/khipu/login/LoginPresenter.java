package com.raw.khipu.login;

import java.io.Serializable;

import com.raw.khipu.KhipuAppUI;
import com.raw.khipu.login.LoginView.LoginViewListener;
import com.raw.khipu.main.MainModel;
import com.raw.khipu.main.MainPresenter;
import com.raw.khipu.main.MainView;
import com.raw.khipu.main.MainViewImpl;
import com.vaadin.navigator.View;
import com.vaadin.ui.UI;

public class LoginPresenter implements LoginViewListener, Serializable {

  private static final long serialVersionUID = 8629755849990453864L;
  private LoginModel loginModel;
  private LoginView loginView;

  public LoginPresenter(LoginModel model, LoginView view) {
    this.loginModel = model;
    this.loginView = view;
    loginView.addListener(this);
  }

  @Override
  public void buttonClick(String operation, String userName, String userPassword, String eMail) {

    if (operation == "btnSignIn") {
      try {
        if (loginModel.userHasAccess(userName, userPassword)) {
          ((KhipuAppUI) UI.getCurrent()).setRegisteredUser(loginModel.getUser(userName,
              userPassword));
          loginView.showSucceedMessage();

          // Create the main page with model-view-presenter pattern
          MainModel mainModel = new MainModel();
          MainView mainView = new MainViewImpl(loginView.getUIApp());
          new MainPresenter(mainModel, mainView);

          ((KhipuAppUI) UI.getCurrent()).setMainView(mainView);

          loginView.getUIApp().getNavigator().addView(KhipuAppUI.MAINVIEW, (View) mainView);
          loginView.getUIApp().getNavigator().navigateTo(KhipuAppUI.MAINVIEW);

        } else {
          loginView.showFailureMessage();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    if (operation == "btnForgot") {
      // Create the main page with model-view-presenter pattern
      LoginModel restoreAccountModel = new LoginModel();
      RestoreAccountViewImpl restoreAccountView = new RestoreAccountViewImpl(loginView.getUIApp());
      new RestoreAccountPresenter(restoreAccountModel, restoreAccountView);

      loginView.getUIApp().getNavigator().addView(KhipuAppUI.RESTOREVIEW, restoreAccountView);
      loginView.getUIApp().getNavigator().navigateTo(KhipuAppUI.RESTOREVIEW);

    }
  }

}
