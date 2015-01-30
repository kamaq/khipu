package com.raw.khipu.form;

import java.util.Locale;

import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.converter.Converter;

/**
 * {@link Converter} implementation for converting the item IDs of an {@link IndexedContainer} into
 * the corresponding domain model, which is the {@link Entity} entity bean class in this example.
 */
public class IndexToEntityConverter implements Converter<Object, Object> {

  private static final String BEAN_PROPERTY_ID = "bean";
  private static final long serialVersionUID = -4854942758136647077L;

  /**
   * {@link IndexedContainer} instance that contains the Entity beans in an item property with ID
   * "bean".
   */
  private IndexedContainer container;

  public IndexToEntityConverter(IndexedContainer container) {
    this.container = container;
  }

  @Override
  public Object convertToModel(Object itemID, Class<? extends Object> targetType, Locale locale)
      throws com.vaadin.data.util.converter.Converter.ConversionException {

    // return null if the given item ID object is null to prevent
    // NullPointerExceptions
    if (itemID == null) {
      return null;
    }

    // fetch the item referenced by the given item ID from the container
    Item item = container.getItem(itemID);
    if (item == null) {
      return null;
    }

    // read the property with ID "bean" from this item and return the property's
    // value as a Entity instance
    return (Object) item.getItemProperty(BEAN_PROPERTY_ID).getValue();
  }

  @Override
  public Integer convertToPresentation(Object model, Class<? extends Object> targetType,
      Locale locale) throws com.vaadin.data.util.converter.Converter.ConversionException {

    // return null if the given model object is null to prevent
    // NullPointerExceptions
    if (model == null) {
      return null;
    }

    // walk over all item IDs in the container
    for (Object itemId : container.getItemIds()) {
      // fetch the current item
      Item item = container.getItem(itemId);
      // if the item's "bean" property contains the Entity object passed in
      // as parameter then the item's ID is returned.

      Object ob = (Object) item.getItemProperty(BEAN_PROPERTY_ID).getValue();

      // if (model.equals((Object) item.getItemProperty(BEAN_PROPERTY_ID).getValue())) {
      if (model.equals(ob)) {
        // System.out.println("found: " + itemId);
        return (Integer) itemId;
      }
    }

    return null;
  }

  @Override
  public Class<Object> getModelType() {
    return Object.class;
  }

  @Override
  public Class<Object> getPresentationType() {
    return Object.class;
  }
}
