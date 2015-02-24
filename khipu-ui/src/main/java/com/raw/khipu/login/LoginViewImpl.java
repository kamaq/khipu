package com.raw.khipu.login;

import java.util.ArrayList;
import java.util.List;

import com.raw.khipu.KhipuAppUI;
import com.raw.khipu.dto.User;
import com.raw.khipu.i18n.AppMessages;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class LoginViewImpl extends CustomComponent implements LoginView, ClickListener, View {

  private static final long serialVersionUID = -8338841961681114373L;
  private final String ITEM_WIDTH = "225px";
  private final String BUTTON_WIDTH = "100px";
  private final KhipuAppUI app;
  private Label lblProduct = new Label("Khipu Application");
  private Label lblProductDescrip = new Label("...your solution on the web.");
  private final ComboBox cmbTheme = new ComboBox("Theme");
  private TextField tfUser = new TextField("Username");
  private PasswordField tfPassword = new PasswordField("Password");
  private Button btnSignIn = new Button("SignIn", this);
  private TextField tfEmail = new TextField("Email");
  private Button btnForgot = new Button("Forgot", this);
  private Link lnkForgot = new Link();
  private Label lblFootInfo = new Label();

  public LoginViewImpl(KhipuAppUI _app) {
    this.app = _app;

    VerticalLayout itemsLayout = new VerticalLayout();
    itemsLayout.setHeight("400px");
    itemsLayout.setWidth("220px");

    final ComboBox cmbLang = new ComboBox("Language");
    cmbLang.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
    cmbLang.addItem("en");
    cmbLang.addItem("es");
    cmbLang.setItemCaption("en", "English");
    cmbLang.setItemCaption("es", "Español");
    cmbLang.setValue(UI.getCurrent().getLocale().toString());
    cmbLang.setImmediate(true);
    cmbLang.setWidth(ITEM_WIDTH);
    cmbLang.setIcon(FontAwesome.LANGUAGE);
    cmbLang.setNullSelectionAllowed(false);

    cmbLang.addValueChangeListener(new Property.ValueChangeListener() {
      private static final long serialVersionUID = 1L;

      @Override
      public void valueChange(ValueChangeEvent event) {
        app.setMessageLocale(cmbLang.getValue().toString());
        updateLocaleItems();
      }
    });

    app.setMessageLocale("es");
    updateLocaleItems();

    Page.getCurrent().setTitle(app.getMessageLocale(AppMessages.AppTitle));
    Image logoApp = app.getAppLogo();
    logoApp.setWidth("110px");
    logoApp.setHeight("70px");
    itemsLayout.addComponent(logoApp);
    itemsLayout.setComponentAlignment(logoApp, Alignment.MIDDLE_CENTER);

    cmbTheme.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
    // cmbTheme.addItem("tests-valo-blueprint");
    cmbTheme.addItem("tests-valo-dark");
    cmbTheme.addItem("tests-valo-facebook");
    // cmbTheme.addItem("tests-valo-flat");
    // cmbTheme.addItem("tests-valo-flatdark");
    cmbTheme.addItem("khipu");
    cmbTheme.addItem("tests-valo-light");
    // cmbTheme.addItem("tests-valo-metro");
    // cmbTheme.setItemCaption("tests-valo-blueprint", "Blueprint");
    cmbTheme.setItemCaption("tests-valo-dark", "Dark");
    cmbTheme.setItemCaption("tests-valo-facebook", "Facebook");
    // cmbTheme.setItemCaption("tests-valo-flat", "Flat");
    // cmbTheme.setItemCaption("tests-valo-flatdark", "Flat Dark");
    cmbTheme.setItemCaption("khipu", "Khipu");
    cmbTheme.setItemCaption("tests-valo-light", "Light");
    // cmbTheme.setItemCaption("tests-valo-metro", "Metro");
    cmbTheme.setValue("khipu");
    cmbTheme.setImmediate(true);
    cmbTheme.setWidth(ITEM_WIDTH);
    cmbTheme.setIcon(FontAwesome.PENCIL);
    cmbTheme.setNullSelectionAllowed(false);

    cmbTheme.addValueChangeListener(new Property.ValueChangeListener() {
      private static final long serialVersionUID = -1309085522762424217L;

      @Override
      public void valueChange(ValueChangeEvent event) {
        UI.getCurrent().setTheme((String) event.getProperty().getValue());
      }
    });
    tfUser.setInputPrompt("tu cuenta");
    tfUser.addStyleName("inline-icon");
    tfUser.setIcon(FontAwesome.USER);
    tfUser.setWidth(ITEM_WIDTH);
    tfUser.setCursorPosition(1);

    tfPassword.addStyleName("inline-icon");
    tfPassword.setIcon(FontAwesome.LOCK);
    tfPassword.setWidth(ITEM_WIDTH);

    itemsLayout.addComponent(cmbLang);
    itemsLayout.addComponent(cmbTheme);
    itemsLayout.addComponent(tfUser);
    itemsLayout.addComponent(tfPassword);
    itemsLayout.addComponent(btnSignIn);
    btnSignIn.setData("btnSignIn");
    btnSignIn.setWidth(BUTTON_WIDTH);
    itemsLayout.addComponent(btnForgot);
    btnForgot.setData("btnForgot");
    itemsLayout.setComponentAlignment(btnForgot, Alignment.MIDDLE_RIGHT);
    itemsLayout.setSpacing(true);
    itemsLayout.setStyleName("raw-background-color");

    VerticalLayout layoutFoot = new VerticalLayout();
    lblFootInfo.setSizeUndefined();
    layoutFoot.addComponent(lblFootInfo);
    layoutFoot.setStyleName("raw-background-color");
    layoutFoot.setComponentAlignment(lblFootInfo, Alignment.BOTTOM_CENTER);
    layoutFoot.setWidth("100%");

    VerticalLayout mainLayout = new VerticalLayout();
    HorizontalLayout topLayout = new HorizontalLayout();
    VerticalLayout leftLayout = new VerticalLayout();
    VerticalLayout righttLayout = new VerticalLayout();
    VerticalLayout bottomLayout = new VerticalLayout();

    lblProduct.setStyleName("raw-product-title");
    lblProduct.setSizeUndefined();
    leftLayout.addComponent(lblProduct);
    leftLayout.setExpandRatio(lblProduct, 1);
    leftLayout.setComponentAlignment(lblProduct, Alignment.BOTTOM_CENTER);

    Image mainImage = app.getMainImage();
    mainImage.setHeight("310px");
    mainImage.setWidth("450px");
    mainImage.setSizeUndefined();
    leftLayout.addComponent(mainImage);
    // leftLayout.setExpandRatio(mainImage, 1);
    leftLayout.setComponentAlignment(mainImage, Alignment.MIDDLE_CENTER);
    leftLayout.setSizeFull();

    lblProductDescrip.setStyleName("raw-product-description");
    lblProductDescrip.setSizeUndefined();
    leftLayout.addComponent(lblProductDescrip);
    leftLayout.setExpandRatio(lblProductDescrip, 1);
    leftLayout.setComponentAlignment(lblProductDescrip, Alignment.MIDDLE_CENTER);

    righttLayout.addComponent(itemsLayout);
    righttLayout.setComponentAlignment(itemsLayout, Alignment.MIDDLE_CENTER);
    righttLayout.setStyleName("raw-background-color");
    righttLayout.setSizeFull();

    Image tradeImage = app.getTradeImage();
    tradeImage.setHeight("30px");
    tradeImage.setWidth("50px");
    bottomLayout.addComponent(tradeImage);
    bottomLayout.setComponentAlignment(tradeImage, Alignment.MIDDLE_CENTER);
    bottomLayout.addComponent(layoutFoot);
    bottomLayout.setComponentAlignment(layoutFoot, Alignment.MIDDLE_CENTER);
    bottomLayout.setStyleName("raw-background-color");
    bottomLayout.setSizeFull();

    topLayout.addComponent(leftLayout);
    topLayout.setExpandRatio(leftLayout, 5);
    topLayout.setComponentAlignment(leftLayout, Alignment.BOTTOM_CENTER);
    topLayout.addComponent(righttLayout);
    topLayout.setExpandRatio(righttLayout, 4);
    topLayout.setComponentAlignment(righttLayout, Alignment.BOTTOM_CENTER);
    topLayout.setSizeFull();

    mainLayout.addComponent(topLayout);
    mainLayout.setExpandRatio(topLayout, 10);
    mainLayout.addComponent(bottomLayout);
    mainLayout.setExpandRatio(bottomLayout, 1.5f);
    mainLayout.setSizeFull();

    setCompositionRoot(mainLayout);
    this.setSizeFull();
  }

  public void updateLocaleItems() {
    lblProduct.setValue(app.getMessageLocale(AppMessages.AppProduct));
    lblProductDescrip.setValue(app.getMessageLocale(AppMessages.AppProductDescription));
    cmbTheme.setCaption(app.getMessageLocale(AppMessages.AppTheme));
    tfUser.setCaption(app.getMessageLocale(AppMessages.Username));
    tfUser.setInputPrompt(app.getMessageLocale(AppMessages.UsernameInputPrompt));
    tfPassword.setCaption(app.getMessageLocale(AppMessages.Password));
    tfEmail.setCaption(app.getMessageLocale(AppMessages.UserEmail));
    btnSignIn.setCaption(app.getMessageLocale(AppMessages.LoginButton));
    btnForgot.setCaption(app.getMessageLocale(AppMessages.ForgotPassword));
    lnkForgot.setCaption(app.getMessageLocale(AppMessages.ForgotPassword));
    lblFootInfo.setValue(app.getMessageLocale(AppMessages.AppFootInfo));
  }

  @Override
  public void setMessageStatus(String message) {
    Notification.show("Information: ", message, Notification.Type.WARNING_MESSAGE);
  }

  @Override
  public void showSucceedMessage() {
    Notification.show(app.getMessageLocale(AppMessages.AppWelcome) + " "
        + app.getRegisteredUser().getEmail(), "", Notification.Type.HUMANIZED_MESSAGE);
  }

  @Override
  public void showFailureMessage() {
    Notification.show("Error: ", app.getMessageLocale(AppMessages.InvalidUserOrPassword),
        Notification.Type.ERROR_MESSAGE);
  }

  @Override
  public void setRegisteredUser(User userDTO) {
    app.setRegisteredUser(userDTO);
  }

  List<LoginViewListener> listeners = new ArrayList<LoginViewListener>();

  @Override
  public void addListener(LoginViewListener listener) {
    listeners.add(listener);
  }

  @Override
  public void buttonClick(ClickEvent event) {
    for (LoginViewListener listener : listeners) {
      listener.buttonClick(event.getButton().getData().toString(), tfUser.getValue().toString(),
          tfPassword.getValue().toString(), tfEmail.getValue().toString());
    }

  }

  @Override
  public KhipuAppUI getUIApp() {
    return this.app;
  }

  @Override
  public void enter(ViewChangeEvent event) {
    tfUser.setValue("");
    tfPassword.setValue("");
    tfUser.setCursorPosition(1);
  }

}
