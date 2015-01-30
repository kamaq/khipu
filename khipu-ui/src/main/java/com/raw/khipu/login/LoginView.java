package com.raw.khipu.login;

import com.raw.khipu.KhipuAppUI;
import com.raw.khipu.dto.User;

public interface LoginView {

  public interface LoginViewListener {

    void buttonClick(String operation, String userName, String userPassword, String eMail);

  }

  public void setMessageStatus(String message);

  public void addListener(LoginViewListener listener);

  public void showSucceedMessage();

  public void showFailureMessage();

  public void setRegisteredUser(User userDTO);

  public KhipuAppUI getUIApp();

}
