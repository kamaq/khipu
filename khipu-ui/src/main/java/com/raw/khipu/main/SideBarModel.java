package com.raw.khipu.main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.raw.khipu.dto.Property;

public class SideBarModel implements Serializable {

  private Map<String, String> properties = new HashMap<String, String>();

  private static final long serialVersionUID = -8277967293236270860L;

  public void addProperty(String variable, String value) {
    properties.put(variable, value);
  }

  public void removeProperty(String variable) {
    properties.remove(variable);
  }

  public List<Property> getProperties() {
    List<Property> list = new ArrayList<Property>();
    for (Entry entry : properties.entrySet()) {
      list.add(new Property(entry.getKey().toString(), entry.getValue().toString()));
    }
    return list;
  }

}
