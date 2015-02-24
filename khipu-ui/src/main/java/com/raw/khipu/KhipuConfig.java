package com.raw.khipu;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.raw.khipu.util.OperatingSystem;
import com.vaadin.server.VaadinServlet;

public class KhipuConfig extends DefaultHandler {
  public static String CONFIG_FOLDER = "/WEB-INF/conf/";
  private String xmlFileName;
  private String tmpValue;
  private String mailServerSmtp;
  private String mailServerPort;
  private String mailServerUser;
  private String mailServerPassword;
  private Boolean fMailServerSmtp;
  private Boolean fMailServerPort;
  private Boolean fMailServerUser;
  private Boolean fMailServerPassword;
  private OperatingSystem OS = new OperatingSystem();

  public KhipuConfig() {
    // this.xmlFileName = "/home/wil/workspace/APM/conf/config.xml" ;
    // this.xmlFileName = "C:\\Users\\wil\\workspace\\APM\\conf\\config.xml" ;
    // this.xmlFileName = new File("").getAbsolutePath() + "\\conf\\config.xml" ;
    String servletPath = VaadinServlet.getCurrent().getServletContext().getRealPath("/");

    // Debe ejecutarse la clase dentro del home del proyecto
    if (OS.isWindows()) {
      // this.xmlFileName = "conf\\config.xml"; // Windows
      this.xmlFileName = servletPath + "\\WEB-INF\\conf\\" + "khipu-config.xml";
    }
    if (OS.isUnix()) {
      // this.xmlFileName = "conf/config.xml"; // Linux
      this.xmlFileName = servletPath + "/WEB-INF/conf/" + "khipu-config.xml";
    }
    parseDocument();
  }

  private void parseDocument() {
    // parse
    SAXParserFactory factory = SAXParserFactory.newInstance();
    fMailServerSmtp = false;
    fMailServerPort = false;
    fMailServerUser = false;
    fMailServerPassword = false;

    try {
      SAXParser parser = factory.newSAXParser();
      parser.parse(this.xmlFileName, this);
    } catch (ParserConfigurationException e) {
      System.out.println("ParserConfig error: " + e.getMessage());
      e.printStackTrace();
    } catch (SAXException e) {
      System.out.println("SAXException : xml not well formed - " + e.getMessage());
      e.printStackTrace();
    } catch (IOException e) {
      System.out.println("IO error: " + e.getMessage());
      e.printStackTrace();
    }
  }

  @Override
  public void startElement(String s, String s1, String elementName, Attributes attributes)
      throws SAXException {

    if (elementName.equalsIgnoreCase("mail-smtp-server")) {
      fMailServerSmtp = true;
    }
    if (elementName.equalsIgnoreCase("mail-smtp-port")) {
      fMailServerPort = true;
    }
    if (elementName.equalsIgnoreCase("mail-smtp-user")) {
      fMailServerUser = true;
    }
    if (elementName.equalsIgnoreCase("mail-smtp-password")) {
      fMailServerPassword = true;
    }

  }

  @Override
  public void endElement(String s, String s1, String element) throws SAXException {
    // if end of book element add to list

  }

  @Override
  public void characters(char[] ac, int i, int j) throws SAXException {
    if (fMailServerSmtp) {
      this.setMailServerSmtp(new String(ac, i, j));
      fMailServerSmtp = false;
    }
    if (fMailServerPort) {
      this.setMailServerPort(new String(ac, i, j));
      fMailServerPort = false;
    }
    if (fMailServerUser) {
      this.setMailServerUser(new String(ac, i, j));
      fMailServerUser = false;
    }
    if (fMailServerPassword) {
      this.setMailServerPassword(new String(ac, i, j));
      fMailServerPassword = false;
    }
  }

  public String getMailServerSmtp() {
    return mailServerSmtp;
  }

  public void setMailServerSmtp(String mailServerSmtp) {
    this.mailServerSmtp = mailServerSmtp;
  }

  public String getMailServerPort() {
    return mailServerPort;
  }

  public void setMailServerPort(String mailServerPort) {
    this.mailServerPort = mailServerPort;
  }

  public String getMailServerUser() {
    return mailServerUser;
  }

  public void setMailServerUser(String mailServerUser) {
    this.mailServerUser = mailServerUser;
  }

  public String getMailServerPassword() {
    return mailServerPassword;
  }

  public void setMailServerPassword(String mailServerPassword) {
    this.mailServerPassword = mailServerPassword;
  }

}
