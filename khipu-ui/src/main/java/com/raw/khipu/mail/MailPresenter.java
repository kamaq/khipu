package com.raw.khipu.mail;

import java.io.Serializable;

import com.raw.khipu.KhipuAppUI;
import com.raw.khipu.mail.MailView.MailViewListener;
import com.vaadin.ui.Window;

public class MailPresenter implements MailViewListener, Serializable {

  private static final long serialVersionUID = 3220378718962788644L;

  private MailModel mailModel;
  private MailViewImpl mailView;
  private KhipuAppUI app;
  private Window window;

  public MailPresenter(MailModel model, MailView view, KhipuAppUI _app) {
    this.mailModel = model;
    this.mailView = (MailViewImpl) view;
    this.app = _app;

    mailView.addListener(this);
    mailView.setMailModel(model);
    mailView.setUIApp(app);

    this.window = mailView.buildMainLayout();
    mailView.populateAttachments();
  }

  @Override
  public void buttonClick(String operation) {

    if (operation == "btnAddMailTo") {
      this.mailView.addEmail();
    }

    if (operation == "btnSend") {
      this.mailView.sendMail();
    }

    if (operation == "btnCancel") {
      this.mailView.removeWindow();
    }

  }

  public Window getWindow() {
    return window;
  }

}
