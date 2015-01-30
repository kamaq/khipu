package com.raw.khipu.mail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.raw.khipu.dto.Property;

public class MailModel implements Serializable {

  private Map<String, String> attachments = new HashMap<String, String>();
  private Map<String, String> recipients = new HashMap<String, String>();

  private static final long serialVersionUID = -8277967293236270860L;

  public void addAttachment(String pathFile, String fileName) {
    attachments.put(pathFile, fileName);
  }

  public void removeAttachment(String pathFile) {
    attachments.remove(pathFile);
  }

  public List<Property> getAttachments() {
    List<Property> list = new ArrayList<Property>();
    for (Entry entry : attachments.entrySet()) {
      list.add(new Property(entry.getKey().toString(), entry.getValue().toString()));
    }
    return list;
  }

  public void addRecipient(String recipient) {
    recipients.put(recipient, recipient);
  }

  public void removeRecipient(String recipient) {
    recipients.remove(recipient);
  }

  public List<Property> getRecipients() {
    List<Property> list = new ArrayList<Property>();
    for (Entry entry : recipients.entrySet()) {
      list.add(new Property(entry.getKey().toString(), entry.getValue().toString()));
    }
    return list;
  }

}
