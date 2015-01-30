package com.raw.khipu.util;

import com.raw.khipu.mail.Mail;

public class MailTest {

  public static void main(String[] args) {
    Mail mail = new Mail();
    mail.addRecipientTO("wilmer.reyes.mx@ajegroup.com");
    mail.addRecipientCC("wilmer_reyes@hotmail.com");
    mail.setSubject("Recovery pass");
    mail.setBody("Dear User"
        + "<p>This mail is about the recovery password request that you made from Khipu Application.</p>"
        + "<p>Your Khipu account password is ??????? </p>" + "<p>Best regards. </p>"
        + "<p><h3>khipu App.<h3></p>");
    // mail.addAttachment("/home/wil/Desktop/file.txt");
    // mail.addAttachment("/home/wil/Desktop/img1.png");
    mail.send();

  }

}
