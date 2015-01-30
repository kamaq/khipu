package com.raw.khipu.mail;

import com.raw.khipu.KhipuAppUI;

public interface MailView {

  public interface MailViewListener {

    void buttonClick(String operation);

  }

  public void addListener(MailViewListener listener);

  public void showSucceedMessage();

  public void showFailureMessage();

  public KhipuAppUI getUIApp();

  public void setUIApp(KhipuAppUI app);

  public void setMailModel(MailModel _mailModel);

  public void removeWindow();
}
