package com.raw.khipu.form.module;

import java.util.HashMap;
import java.util.Map;

import com.raw.khipu.dto.Module;
import com.raw.khipu.form.DialogBox;
import com.raw.khipu.form.DialogBox.ConfirmListener;
import com.raw.khipu.form.FormViewImpl;
import com.raw.khipu.i18n.AppMessages;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

public class ModuleFormViewImpl extends FormViewImpl {

  private static final long serialVersionUID = 5011463172197746695L;
  private Button btnCreate;
  private Button btnModify;
  private Button btnEnable;
  private Button btnDelete;
  private TextField idModule;
  private TextField name;
  private TextField description;
  private TextField imageFileName;

  public ModuleFormViewImpl() {
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
    idModule = new TextField();
    name = new TextField();
    description = new TextField();
    imageFileName = new TextField();

    getFormEditLayout().addComponent(idModule);
    getFormEditLayout().addComponent(name);
    getFormEditLayout().addComponent(description);
    getFormEditLayout().addComponent(imageFileName);
  }

  @Override
  public void bindTableModelWithComponents() {
    try {
      // Bind dat model list with Table Component
      setTableModelWithComponent(0, ((ModuleFormModel) getFormModel()).getModules(),
          getFirstTable());

      // Set BeanItemContainer with Entity Class
      setPropertyBeanItemContainer(new BeanItemContainer<Module>(Module.class));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void prepareEdition(String mode) {
    try {
      // Define the entity class for the first table
      setEntityClass(Module.class);

      if (mode.equalsIgnoreCase(CREATE_MODE)) {
        // Bind form component fields with Bean Field Group for CREATE edition
        setBeanFieldGroupWithComponent(new Module(), this, null);
        // Locale edit window title
        setEditWindowTitle(getApp().getMessageLocale(AppMessages.btnCreate) + " "
            + getApp().getMessageLocale(AppMessages.Module));
      }

      if (mode.equalsIgnoreCase(MODIFY_MODE)) {
        // Bind form component fields with Bean Field Group for MODIFY edition
        setBeanFieldGroupWithComponent(((Module) getFirstTable().getValue()), this,
            ((Module) getFirstTable().getValue()).getIdModule());
        // Locale edit window title
        setEditWindowTitle(getApp().getMessageLocale(AppMessages.btnModify) + " "
            + getApp().getMessageLocale(AppMessages.Module));
      }

      if (mode.equalsIgnoreCase(CHANGE_STATUS_MODE)) {
        // Bind form component fields with Bean Field Group for MODIFY edition
        setBeanFieldGroupWithComponent(((Module) getFirstTable().getValue()), this,
            ((Module) getFirstTable().getValue()).getIdModule());

        DialogBox box = new DialogBox(getApp());
        box.show(getApp().getMessageLocale(AppMessages.FormWarning),
            getApp().getMessageLocale(AppMessages.FormAreYouSureDisable), new ConfirmListener() {
              private static final long serialVersionUID = 3500651267535289099L;

              @Override
              public void onClose(DialogBox dialog) throws Exception {
                if (dialog.isConfirmed()) {

                  // Get selected record and udpdate it
                  Module module = (Module) getFieldGroup().getItemDataSource().getBean();
                  module.setImageFileName("[Deprecated]");

                  // Update record in database
                  getFormModel().updateRecord(getEntityClass(), module, module.getIdModule());
                  // Refresh table component
                  getFirstTable().refreshRowCache();
                }
              }
            });
      }

      if (mode.equalsIgnoreCase(DELETE_MODE)) {
        // Bind form component fields with Bean Field Group for MODIFY edition
        setBeanFieldGroupWithComponent(((Module) getFirstTable().getValue()), this,
            ((Module) getFirstTable().getValue()).getIdModule());

        DialogBox box = new DialogBox(getApp());
        box.show(getApp().getMessageLocale(AppMessages.FormWarning),
            getApp().getMessageLocale(AppMessages.FormAreYouSureDelete), new ConfirmListener() {
              private static final long serialVersionUID = 3500651267535289099L;

              @Override
              public void onClose(DialogBox dialog) throws Exception {
                if (dialog.isConfirmed()) {

                  // Get selected record
                  Module module = (Module) getFieldGroup().getItemDataSource().getBean();

                  // Delete record in database
                  getFormModel().deleteRecord(getEntityClass(), module.getIdModule());

                  // Delete record on table compoenent and refresh it
                  getFirstTable().removeItem(module);
                  getFirstTable().refreshRowCache();

                }
              }
            });
      }

    } catch (java.lang.NullPointerException e) {
      Notification.show(getApp().getMessageLocale(AppMessages.FormWarning), getApp()
          .getMessageLocale(AppMessages.FormMustSelectRecord), Notification.Type.WARNING_MESSAGE);
    } catch (Exception e) {
      Notification.show(getApp().getMessageLocale(AppMessages.FormError), e.getMessage(),
          Notification.Type.ERROR_MESSAGE);
    }
  }

  @Override
  public void applyValidations() {
    idModule.setEnabled(false);

    name.setRequired(true);
    name.setImmediate(true);
    name.setNullRepresentation("");
    name.setRequiredError(getApp().getMessageLocale(AppMessages.FormItemIsRequired));

    description.setRequired(true);
    description.setImmediate(true);
    description.setNullRepresentation("");
    description.setRequiredError(getApp().getMessageLocale(AppMessages.FormItemIsRequired));

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
    getFirstTable().setVisibleColumns("idModule", "name", "description", "imageFileName");

    getFirstTable().sort(new Object[] { "idModule" }, new boolean[] { true, true });
  }

  @Override
  public void setLocaleComponents() {
    setFormTitle(getApp().getMessageLocale(AppMessages.Module));

    // Locale Columns Table
    getFirstTable().setColumnHeader("idModule", getApp().getMessageLocale(AppMessages.ModuleId));
    getFirstTable().setColumnHeader("name", getApp().getMessageLocale(AppMessages.ModuleName));
    getFirstTable().setColumnHeader("description",
        getApp().getMessageLocale(AppMessages.ModuleDescription));
    getFirstTable().setColumnHeader("imageFileName",
        getApp().getMessageLocale(AppMessages.ModuleImageFileName));

    // Locale main buttons
    btnCreate.setCaption(getApp().getMessageLocale(AppMessages.btnCreate));
    btnModify.setCaption(getApp().getMessageLocale(AppMessages.btnModify));
    btnEnable.setCaption(getApp().getMessageLocale(AppMessages.btnEnable));
    btnDelete.setCaption(getApp().getMessageLocale(AppMessages.btnDelete));

    // Locale Edit Form
    idModule.setCaption(getApp().getMessageLocale(AppMessages.ModuleId));
    name.setCaption(getApp().getMessageLocale(AppMessages.ModuleName));
    description.setCaption(getApp().getMessageLocale(AppMessages.ModuleDescription));
    imageFileName.setCaption(getApp().getMessageLocale(AppMessages.ModuleImageFileName));
  }

  @Override
  public void registerChildForms() throws Exception {
    // Prepare parameters to child form
    Map<String, Object> params = new HashMap<String, Object>();

    // Register child forms
    addDetailForm("form.component.ComponentFormFacade",
        getApp().getMainView().openForm("form.component.ComponentFormFacade", params, null, false));
  }

}
