package com.raw.khipu.login;

import com.raw.khipu.KhipuAppUI;

public interface RestoreAccountView {

  public interface RestoreAccountViewListener {

    void buttonClick(String operation, String email);

  }

  public void addListener(RestoreAccountViewListener listener);

  public void showSucceedMessage();

  public void showFailureMessage();

  public KhipuAppUI getUIApp();

}
