package com.raw.khipu.mail;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.raw.khipu.KhipuConfig;

public class Mail {

  private List<String> lRecipientsTO = new ArrayList<String>();
  private List<String> lRecipientsCC = new ArrayList<String>();
  private List<String> lRecipientsBCC = new ArrayList<String>();
  private List<String> attachments = new ArrayList<String>();
  private static String userMail = new String();
  private static String userMailPassword = new String();
  private String subject;
  private String body;

  public Mail() {

  }

  public void send() {

    KhipuConfig config = new KhipuConfig();

    // Sender's email ID needs to be mentioned
    String from = config.getMailServerUser(); // "khipu.app@gmail.com";

    // Get system properties
    Properties properties = new Properties();

    // Setup mail server
    userMail = config.getMailServerUser(); // "khipu.app@gmail.com";
    userMailPassword = config.getMailServerPassword(); // "khipu2014";

    properties.put("mail.smtp.host", config.getMailServerSmtp());// "smtp.gmail.com");
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.port", config.getMailServerPort()); // "587");

    Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userMail, userMailPassword);
      }
    });

    try {
      // Create a default MimeMessage object.
      MimeMessage message = new MimeMessage(session);

      // Set From: header field of the header.
      message.setFrom(new InternetAddress(from));

      // Set To: header field of the header.
      if (getRecipientsTO().length > 0) {
        message.addRecipients(Message.RecipientType.TO, getRecipientsTO());
      }
      if (getRecipientsCC().length > 0) {
        message.addRecipients(Message.RecipientType.CC, getRecipientsCC());
      }
      if (getRecipientsBCC().length > 0) {
        message.addRecipients(Message.RecipientType.BCC, getRecipientsBCC());
      }

      // Set Subject: header field
      message.setSubject(getSubject());

      // Create the message part
      BodyPart messageBodyPart = new MimeBodyPart();

      // Fill the message
      messageBodyPart.setContent(getBody(), "text/html");

      // Create a multipar message
      Multipart multipart = new MimeMultipart();

      // Set text message part
      multipart.addBodyPart(messageBodyPart);

      for (String filename : this.attachments) {
        // Part two is attachment
        messageBodyPart = new MimeBodyPart();

        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);
        multipart.addBodyPart(messageBodyPart);
      }

      // Send the complete message parts
      message.setContent(multipart);

      // Send message
      Transport.send(message);

    } catch (MessagingException ex) {
      ex.printStackTrace();
    }
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public void addRecipientTO(String _recipient) {
    this.lRecipientsTO.add(_recipient);
  }

  public void addRecipientCC(String _recipient) {
    this.lRecipientsCC.add(_recipient);
  }

  public void addRecipientBCC(String _recipient) {
    this.lRecipientsBCC.add(_recipient);
  }

  public void addAttachment(String attachment) {
    this.attachments.add(attachment);
  }

  public Address[] getRecipientsTO() {
    Address[] recipients = new Address[this.lRecipientsTO.size()];
    int i = 0;
    for (String rec : this.lRecipientsTO) {
      try {
        recipients[i] = new InternetAddress(rec);
        i++;
      } catch (AddressException e) {
        e.printStackTrace();
      }
    }
    return recipients;
  }

  public Address[] getRecipientsCC() {
    Address[] recipients = new Address[this.lRecipientsCC.size()];
    int i = 0;
    for (String rec : this.lRecipientsCC) {
      try {
        recipients[i] = new InternetAddress(rec);
        i++;
      } catch (AddressException e) {
        e.printStackTrace();
      }
    }
    return recipients;
  }

  public Address[] getRecipientsBCC() {
    Address[] recipients = new Address[this.lRecipientsBCC.size()];
    int i = 0;
    for (String rec : this.lRecipientsBCC) {
      try {
        recipients[i] = new InternetAddress(rec);
        i++;
      } catch (AddressException e) {
        e.printStackTrace();
      }
    }
    return recipients;
  }

}
