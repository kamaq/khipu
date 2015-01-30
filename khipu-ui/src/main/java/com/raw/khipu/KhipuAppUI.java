package com.raw.khipu;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.annotation.WebServlet;

import com.raw.khipu.dto.Component;
import com.raw.khipu.dto.User;
import com.raw.khipu.i18n.AppMessages;
import com.raw.khipu.login.LoginModel;
import com.raw.khipu.login.LoginPresenter;
import com.raw.khipu.login.LoginViewImpl;
import com.raw.khipu.main.MainView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

// Khipu Application - 2014
// Developed by Wilmer Reyes Alfaro
// A RAW Application, all rights reserved. 
@Theme("khipu")
public class KhipuAppUI extends UI implements Serializable {
  private static final long serialVersionUID = -2182236126343379879L;
  private ResourceBundle i18nBundle;
  private Navigator navigator;
  private User registeredUser;
  private Component currentFormComponent;
  private Map navigationFormMap = new HashMap();
  private boolean activateFormCache = false;
  private MainView mainView;
  public final static String LOGINVIEW = "login";
  public final static String MAINVIEW = "main";
  public final static String RESTOREVIEW = "restoreAccount";

  @WebServlet(value = "/*", asyncSupported = true)
  @VaadinServletConfiguration(productionMode = false, ui = KhipuAppUI.class)
  public static class Servlet extends VaadinServlet {
    private static final long serialVersionUID = 5679001364493947743L;
  }

  @Override
  protected void init(VaadinRequest request) {

    try {
      // Model-view-presenter pattern for login
      LoginModel lModel = new LoginModel();
      LoginViewImpl lView = new LoginViewImpl(this);
      new LoginPresenter(lModel, lView);

      navigator = new Navigator(this, this);
      navigator.addView(LOGINVIEW, lView);
      navigator.navigateTo(LOGINVIEW);

      navigator.addViewChangeListener(new ViewChangeListener() {
        private static final long serialVersionUID = -3738329210646663956L;

        @Override
        public boolean beforeViewChange(ViewChangeEvent event) {
          if (event.getNewView() instanceof MainView
              && ((KhipuAppUI) UI.getCurrent()).getRegisteredUser() == null) {
            Notification.show("Permission denied");
            return false;
          } else {
            return true;
          }
        }

        @Override
        public void afterViewChange(ViewChangeEvent event) {
        }

      });

    } catch (Exception e) {
      e.printStackTrace();
      Notification.show("You can't enter to this URL by this way, please register you.");
    }
  }

  public String getMessageLocale(String key) {
    return i18nBundle.getString(key);
  }

  public void setMessageLocale(String _locale) {
    UI.getCurrent().setLocale(new Locale(_locale));
    i18nBundle = ResourceBundle.getBundle(AppMessages.class.getName(), UI.getCurrent().getLocale());
  }

  public Image getAppLogo() {
    String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
    FileResource resource = new FileResource(new File(basepath
        + "/WEB-INF/images/main/khipuLogo.png"));
    return new Image(null, resource);
  }

  public Image getMainImage() {
    String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
    FileResource resource = new FileResource(new File(basepath
        + "/WEB-INF/images/main/main_image.png"));
    return new Image(null, resource);
  }

  public Image getTradeImage() {
    String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
    FileResource resource = new FileResource(new File(basepath
        + "/WEB-INF/images/main/trade_image.png"));
    return new Image(null, resource);
  }

  public User getRegisteredUser() {
    return registeredUser;
  }

  public void setRegisteredUser(User user) {
    this.registeredUser = user;
  }

  public Component getCurrentFormComponent() {
    return currentFormComponent;
  }

  public void setCurrentFormComponent(Component currentFormComponent) {
    this.currentFormComponent = currentFormComponent;
  }

  public CustomComponent getFormFromNavigationMap(String formName) {
    return (CustomComponent) this.navigationFormMap.get(formName);
  }

  public void addFormToNavigationMap(String formName, CustomComponent form) {
    this.navigationFormMap.put(formName, form);
  }

  public void removeFormFromNavigationMap(String formName) {
    this.navigationFormMap.remove(formName);
  }

  public boolean existFormInNavigationMap(String formName) {
    return this.navigationFormMap.containsKey(formName);
  }

  public MainView getMainView() {
    return mainView;
  }

  public void setMainView(MainView mainView) {
    this.mainView = mainView;
  }

  public boolean isActivateFormCache() {
    return activateFormCache;
  }

  public void setActivateFormCache(boolean activateFormCache) {
    this.activateFormCache = activateFormCache;
  }
}