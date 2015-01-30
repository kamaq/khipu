package com.raw.khipu.menu;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.raw.khipu.KhipuAppUI;
import com.raw.khipu.i18n.AppMessages;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

public class MenuViewImpl extends CustomComponent implements MenuView, LayoutClickListener, View,
    Serializable {

  private static final long serialVersionUID = 1489085342585240637L;
  @AutoGenerated
  private List<Object[]> options;
  private KhipuAppUI app;
  private MenuModel menuModel;
  private Window subwindow = new Window("Menu");
  private String optionSelected = new String();
  private List<VerticalLayout> listaOpciones;
  private GridLayout menuLayout;
  private String basepath;
  private TextField tfSearch;

  public MenuViewImpl(KhipuAppUI _app, List<Object[]> _options) {
    this.app = _app;
    this.options = _options;

    basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();

    subwindow.setModal(true);
    subwindow.setWidth("85%");
    subwindow.setHeight("85%");
    subwindow.setImmediate(true);

    tfSearch = new TextField("Search");
    tfSearch.addStyleName("inline-icon");
    tfSearch.setIcon(FontAwesome.SEARCH);
    tfSearch.setCursorPosition(1);
    tfSearch.setWidth("100%");
    tfSearch.addTextChangeListener(new FieldEvents.TextChangeListener() {
      private static final long serialVersionUID = 2122902067576191445L;

      @Override
      public void textChange(TextChangeEvent event) {
        applyFilter(event.getText());
      }
    });

    VerticalLayout mainLayout = new VerticalLayout();
    menuLayout = new GridLayout(8, 30);
    // menuLayout.setStyleName("backColorGrey");
    menuLayout.setImmediate(true);
    menuLayout.setSizeFull();

    mainLayout.addComponent(tfSearch);
    mainLayout.setExpandRatio(tfSearch, 1);
    mainLayout.addComponent(menuLayout);
    mainLayout.setExpandRatio(menuLayout, 10);
    mainLayout.setImmediate(true);
    mainLayout.setMargin(true);
    mainLayout.setSpacing(true);
    mainLayout.setSizeFull();

    buildMenu();

    subwindow.setContent(mainLayout);
  }

  public void buildMenu() {
    subwindow.setCaption(app.getMessageLocale(AppMessages.MenuTitle));
    tfSearch.setCaption(app.getMessageLocale(AppMessages.MenuSearch));

    menuLayout.removeAllComponents();
    listaOpciones = new ArrayList<VerticalLayout>();

    // Add buttons and send click events to this class
    int i = 0;
    for (Object[] component : this.options) {

      String nameComponent = (String) component[0].toString().trim();
      String classNameComponent = (String) component[1].toString().trim();
      String imageFileNameComponent = (String) component[2].toString().trim();

      listaOpciones.add(i, new VerticalLayout());
      listaOpciones.get(i).addLayoutClickListener(this);
      listaOpciones.get(i).setStyleName("optionSelected");
      listaOpciones.get(i).setWidth("100px");
      listaOpciones.get(i).setHeight("100px");
      listaOpciones.get(i).addComponent(new Label(""));
      listaOpciones.get(i).getComponent(i).setVisible(false);
      listaOpciones.get(i).addComponent(
          new Image(null, new FileResource(new File(basepath + "/WEB-INF/images/menu/"
              + imageFileNameComponent))));
      listaOpciones.get(i).getComponent(i + 1).setStyleName(Reindeer.BUTTON_LINK);
      listaOpciones.get(i).setData(classNameComponent);
      listaOpciones.get(i).setComponentAlignment(listaOpciones.get(i).getComponent(i + 1),
          Alignment.MIDDLE_CENTER);
      listaOpciones.get(i).getComponent(i + 1).setWidth("60px");
      listaOpciones.get(i).getComponent(i + 1).setHeight("60px");
      listaOpciones.get(i).addComponent(new Label(nameComponent));
      listaOpciones.get(i).setComponentAlignment(listaOpciones.get(i).getComponent(i + 2),
          Alignment.BOTTOM_CENTER);
      listaOpciones.get(i).getComponent(i + 2).setSizeUndefined();

      menuLayout.addComponent(listaOpciones.get(i));
      i = i++;
    }

  }

  public void applyFilter(String filter) {
    try {
      this.options = menuModel.getOptions(app.getRegisteredUser(), filter);
      buildMenu();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @AutoGenerated
  public Window buildMainLayout() {
    return subwindow;
  }

  @Override
  public void setMenuModel(MenuModel model) {
    this.menuModel = model;
  }

  List<MenuViewListener> listeners = new ArrayList<MenuViewListener>();

  @Override
  public void addListener(MenuViewListener listener) {
    listeners.add(listener);
  }

  @Override
  public void layoutClick(LayoutClickEvent event) {
    for (MenuViewListener listener : listeners) {
      VerticalLayout layoutSelected = (VerticalLayout) event.getComponent();
      listener.buttonClick(layoutSelected.getData().toString());
      getUI();
      UI.getCurrent().removeWindow(subwindow);
    }
  }

  @Override
  public void setOptions(List<Object[]> options) {
    this.options = options;
  }

  public List<Object[]> getOptions() {
    return options;
  }

  public Window getWindow() {
    return subwindow;
  }

  public String getOptionSelected() {
    return optionSelected;
  }

  @Override
  public void enter(ViewChangeEvent event) {
    tfSearch.setCursorPosition(1);
  }

}