package com.raw.khipu.form.component;

import java.util.HashMap;
import java.util.Map;

import com.raw.khipu.dto.Component;
import com.raw.khipu.dto.Module;
import com.raw.khipu.form.DialogBox;
import com.raw.khipu.form.DialogBox.ConfirmListener;
import com.raw.khipu.form.FormViewImpl;
import com.raw.khipu.i18n.AppMessages;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

public class ComponentFormViewImpl extends FormViewImpl {

  private static final long serialVersionUID = 5011463172197746695L;
  private Button btnCreate;
  private Button btnModify;
  private Button btnEnable;
  private Button btnDelete;
  private ComboBox module;
  private TextField idComponent;
  private TextField name;
  private TextField description;
  private TextField className;
  private TextField imageFileName;

  public ComponentFormViewImpl() {
    btnCreate = new Button("", this);
    btnCreate.setData("btnCreate");
    btnModify = new Button("", this);
    btnModify.setData("btnModify");
    btnEnable = new Button("", this);
    btnEnable.setData("btnEnable");
    btnDelete = new Button("", this);
    btnDelete.setData("btnDelete");

    getToolBar().addComponent(btnCreate);
    getToolBar().addComponent(btnModify);
    getToolBar().addComponent(btnEnable);
    getToolBar().addComponent(btnDelete);

    // add fields to edit form layout
    module = new ComboBox();
    idComponent = new TextField();
    name = new TextField();
    description = new TextField();
    className = new TextField();
    imageFileName = new TextField();

    getFormEditLayout().addComponent(idComponent);
    getFormEditLayout().addComponent(module);
    getFormEditLayout().addComponent(name);
    getFormEditLayout().addComponent(description);
    getFormEditLayout().addComponent(className);
    getFormEditLayout().addComponent(imageFileName);
  }

  @Override
  public void bindTableModelWithComponents() {
    try {
      // Bind datamodel list with Table Component
      setTableModelWithComponent(0,
          ((ComponentFormModel) getFormModel()).getComponents((Module) getParameters()
              .get("module")), getFirstTable());

      // Bind datamodel list with ComboBox using indexed container to achieve entity-id conversion
      setEntityIndexedContainer(module, ((ComponentFormModel) getFormModel()).getModules());

      // Set BeanItemContainer with Entity Class
      setPropertyBeanItemContainer(new BeanItemContainer<Component>(Component.class));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void prepareEdition(String mode) {
    try {
      // Define the entity class for the first table
      setEntityClass(Component.class);

      if (mode.equalsIgnoreCase(CREATE_MODE)) {
        // Bind form component fields with Bean Field Group for CREATE edition
        setBeanFieldGroupWithComponent(new Component(), this, null);

        // Set values from parent form (only exists)
        module.setConvertedValue((Module) getParameters().get("module"));

        // Locale edit window title
        setEditWindowTitle(getApp().getMessageLocale(AppMessages.btnCreate) + " "
            + getApp().getMessageLocale(AppMessages.Component));
      }

      if (mode.equalsIgnoreCase(MODIFY_MODE)) {
        // Bind form component fields with Bean Field Group for MODIFY edition
        setBeanFieldGroupWithComponent(((Component) getFirstTable().getValue()), this,
            ((Component) getFirstTable().getValue()).getIdComponent());

        // Set value (Entity) from selected record on table using converted value
        Module mm = ((Component) getFieldGroup().getItemDataSource().getBean()).getModule();
        module.setConvertedValue(mm);

        // Locale edit window title
        setEditWindowTitle(getApp().getMessageLocale(AppMessages.btnModify) + " "
            + getApp().getMessageLocale(AppMessages.Component));
      }

      if (mode.equalsIgnoreCase(CHANGE_STATUS_MODE)) {
        // Bind form component fields with Bean Field Group for MODIFY edition
        setBeanFieldGroupWithComponent(((Component) getFirstTable().getValue()), this,
            ((Component) getFirstTable().getValue()).getIdComponent());

        DialogBox box = new DialogBox(getApp());
        box.show(getApp().getMessageLocale(AppMessages.FormWarning),
            getApp().getMessageLocale(AppMessages.FormAreYouSureDisable), new ConfirmListener() {
              private static final long serialVersionUID = 3500651267535289099L;

              @Override
              public void onClose(DialogBox dialog) throws Exception {
                if (dialog.isConfirmed()) {
                  // Get selected record and udpdate it
                  Component record = (Component) getFieldGroup().getItemDataSource().getBean();
                  record.setImageFileName("[Deprecated]");

                  // Update record in database
                  getFormModel().updateRecord(getEntityClass(), record, record.getIdComponent());
                  // Refresh table component
                  getFirstTable().refreshRowCache();
                }
              }
            });
      }

      if (mode.equalsIgnoreCase(DELETE_MODE)) {
        // Bind form component fields with Bean Field Group for MODIFY edition
        setBeanFieldGroupWithComponent(((Component) getFirstTable().getValue()), this,
            ((Component) getFirstTable().getValue()).getIdComponent());

        DialogBox box = new DialogBox(getApp());
        box.show(getApp().getMessageLocale(AppMessages.FormWarning),
            getApp().getMessageLocale(AppMessages.FormAreYouSureDelete), new ConfirmListener() {
              private static final long serialVersionUID = 3500651267535289099L;

              @Override
              public void onClose(DialogBox dialog) throws Exception {
                if (dialog.isConfirmed()) {

                  // Get selected record
                  Component record = (Component) getFieldGroup().getItemDataSource().getBean();
                  // Delete record in database
                  getFormModel().deleteRecord(getEntityClass(), record.getIdComponent());
                  // Delete record on table compoenent and refresh it
                  getFirstTable().removeItem(record);
                  getFirstTable().refreshRowCache();
                }
              }
            });
      }

    } catch (java.lang.NullPointerException e) {
      Notification.show(getApp().getMessageLocale(AppMessages.FormWarning), getApp()
          .getMessageLocale(AppMessages.FormMustSelectRecord), Notification.Type.WARNING_MESSAGE);
    } catch (Exception e) {
      Notification.show(getApp().getMessageLocale(AppMessages.FormWarning), "General Error",
          Notification.Type.ERROR_MESSAGE);
      e.printStackTrace();
    }
  }

  @Override
  public void applyValidations() {
    module.setImmediate(true);
    idComponent.setEnabled(false);

    name.setRequired(true);
    name.setImmediate(true);
    name.setNullRepresentation("");
    name.setRequiredError(getApp().getMessageLocale(AppMessages.FormItemIsRequired));

    description.setRequired(true);
    description.setImmediate(true);
    description.setNullRepresentation("");
    description.setRequiredError(getApp().getMessageLocale(AppMessages.FormItemIsRequired));

    className.setRequired(true);
    className.setImmediate(true);
    className.setNullRepresentation("");
    className.setRequiredError(getApp().getMessageLocale(AppMessages.FormItemIsRequired));

    imageFileName.setNullRepresentation("");
  }

  @Override
  public void refreshComponents() {
    try {
      // Get new data from model
      bindTableModelWithComponents();
      // Populate data on component
      populateComponent(0);
      // Apply locale charactheristics
      setVisibleColumns();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void setVisibleColumns() {
    getFirstTable().setVisibleColumns("module", "idComponent", "name", "description", "className",
        "imageFileName");

    getFirstTable().sort(new Object[] { "module", "idComponent" }, new boolean[] { true, true });
  }

  @Override
  public void setLocaleComponents() {
    setFormTitle(getApp().getMessageLocale(AppMessages.Component));

    // Locale Columns Table
    getFirstTable().setColumnHeader("module", getApp().getMessageLocale(AppMessages.ModuleId));
    getFirstTable().setColumnHeader("idComponent",
        getApp().getMessageLocale(AppMessages.ComponentId));
    getFirstTable().setColumnHeader("name", getApp().getMessageLocale(AppMessages.ComponentName));
    getFirstTable().setColumnHeader("description",
        getApp().getMessageLocale(AppMessages.ComponentDescription));
    getFirstTable().setColumnHeader("className",
        getApp().getMessageLocale(AppMessages.ComponentClassName));
    getFirstTable().setColumnHeader("imageFileName",
        getApp().getMessageLocale(AppMessages.ComponentImageFileName));

    // Locale main buttons
    btnCreate.setCaption(getApp().getMessageLocale(AppMessages.btnCreate));
    btnModify.setCaption(getApp().getMessageLocale(AppMessages.btnModify));
    btnEnable.setCaption(getApp().getMessageLocale(AppMessages.btnEnable));
    btnDelete.setCaption(getApp().getMessageLocale(AppMessages.btnDelete));

    // Locale Edit Form
    module.setCaption(getApp().getMessageLocale(AppMessages.ModuleId));
    idComponent.setCaption(getApp().getMessageLocale(AppMessages.ComponentId));
    name.setCaption(getApp().getMessageLocale(AppMessages.ComponentName));
    description.setCaption(getApp().getMessageLocale(AppMessages.ComponentDescription));
    className.setCaption(getApp().getMessageLocale(AppMessages.ComponentClassName));
    imageFileName.setCaption(getApp().getMessageLocale(AppMessages.ComponentImageFileName));
  }

  @Override
  public void synchronizeWithParentForm(Object parent) {
    if (parent != null) {
      getParameters().put("module", parent);
      refreshComponents();
      getApp().getMainView().addForm(this);
    }
  }

  @Override
  public void registerChildForms() throws Exception {
    // Prepare parameters to child form
    Map<String, Object> params = new HashMap<String, Object>();

    // Register child forms
    addDetailForm("form.function.FunctionFormFacade",
        getApp().getMainView().openForm("form.function.FunctionFormFacade", params, null, false));
  }

}
