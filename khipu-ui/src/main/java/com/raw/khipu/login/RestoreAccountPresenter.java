package com.raw.khipu.login;

import java.io.Serializable;

import com.raw.khipu.KhipuAppUI;
import com.raw.khipu.dto.User;
import com.raw.khipu.login.RestoreAccountView.RestoreAccountViewListener;
import com.raw.khipu.mail.Mail;

public class RestoreAccountPresenter implements RestoreAccountViewListener, Serializable {

  private static final long serialVersionUID = 8629755849990453864L;
  private LoginModel loginModel;
  private RestoreAccountViewImpl restoreAccountView;

  public RestoreAccountPresenter(LoginModel model, RestoreAccountView view) {
    this.loginModel = model;
    this.restoreAccountView = (RestoreAccountViewImpl) view;
    restoreAccountView.addListener(this);
  }

  @Override
  public void buttonClick(String operation, String email) {

    if (operation == "btnSend") {
      try {
        User user = loginModel.restoreAccount(email);
        if (user != null) {

          Mail mail = new Mail();
          mail.addRecipientTO(user.getEmail().trim());
          mail.setSubject("Recovery password account");
          mail.setBody("<p>Dear "
              + user.getName().trim()
              + "</p>"
              + "<p>This mail is about the recovery password request that you made from Khipu Application.</p>"
              + "<p>Your Khipu account password is " + user.getPassword().trim() + " </p>"
              + "<p>Best regards. </p>" + "<p><h3>khipu App.<h3></p>");
          mail.send();

          restoreAccountView.showSucceedMessage();
        } else {
          restoreAccountView.showFailureMessage();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    if (operation == "btnSignIn") {
      restoreAccountView.getUIApp().getNavigator().navigateTo(KhipuAppUI.LOGINVIEW);
    }
  }

}
