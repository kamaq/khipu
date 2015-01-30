package com.raw.khipu.form.user;

import java.util.HashMap;
import java.util.Map;

import com.raw.khipu.dto.User;
import com.raw.khipu.form.DialogBox;
import com.raw.khipu.form.DialogBox.ConfirmListener;
import com.raw.khipu.form.FormViewImpl;
import com.raw.khipu.i18n.AppMessages;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

public class UserFormViewImpl extends FormViewImpl {

  private static final long serialVersionUID = 5011463172197746695L;
  private Button btnCreate;
  private Button btnModify;
  private Button btnEnable;
  private Button btnDelete;
  private TextField idUser;
  private TextField name;
  private TextField password;
  private TextField email;
  private TextField enabled;

  public UserFormViewImpl() {
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
    idUser = new TextField();
    name = new TextField();
    password = new TextField();
    email = new TextField();
    enabled = new TextField();

    getFormEditLayout().addComponent(idUser);
    getFormEditLayout().addComponent(name);
    getFormEditLayout().addComponent(password);
    getFormEditLayout().addComponent(email);
    getFormEditLayout().addComponent(enabled);
  }

  @Override
  public void bindTableModelWithComponents() {
    try {
      // Bind dat model list with Table Component
      setTableModelWithComponent(0, ((UserFormModel) getFormModel()).getUsers(), getFirstTable());

      // Set BeanItemContainer with Entity Class
      setPropertyBeanItemContainer(new BeanItemContainer<User>(User.class));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void prepareEdition(String mode) {
    try {
      // Define the entity class for the first table
      setEntityClass(User.class);

      if (mode.equalsIgnoreCase(CREATE_MODE)) {
        // Bind form component fields with Bean Field Group for CREATE edition
        setBeanFieldGroupWithComponent(new User(), this, null);
        // Locale edit window title
        setEditWindowTitle(getApp().getMessageLocale(AppMessages.btnCreate) + " "
            + getApp().getMessageLocale(AppMessages.User));
      }

      if (mode.equalsIgnoreCase(MODIFY_MODE)) {
        // Bind form component fields with Bean Field Group for MODIFY edition
        setBeanFieldGroupWithComponent(((User) getFirstTable().getValue()), this,
            ((User) getFirstTable().getValue()).getIdUser());
        // Locale edit window title
        setEditWindowTitle(getApp().getMessageLocale(AppMessages.btnModify) + " "
            + getApp().getMessageLocale(AppMessages.User));
      }

      if (mode.equalsIgnoreCase(CHANGE_STATUS_MODE)) {
        // Bind form component fields with Bean Field Group for MODIFY edition
        setBeanFieldGroupWithComponent(((User) getFirstTable().getValue()), this,
            ((User) getFirstTable().getValue()).getIdUser());

        DialogBox box = new DialogBox(getApp());
        box.show(getApp().getMessageLocale(AppMessages.FormWarning),
            getApp().getMessageLocale(AppMessages.FormAreYouSureDisable), new ConfirmListener() {
              private static final long serialVersionUID = 3500651267535289099L;

              @Override
              public void onClose(DialogBox dialog) throws Exception {
                if (dialog.isConfirmed()) {

                  // Get selected record and udpdate it
                  User record = (User) getFieldGroup().getItemDataSource().getBean();
                  record.setEnabled(0);

                  // Update record in database
                  getFormModel().updateRecord(getEntityClass(), record, record.getIdUser());
                  // Refresh table component
                  getFirstTable().refreshRowCache();
                }
              }
            });
      }

      if (mode.equalsIgnoreCase(DELETE_MODE)) {
        // Bind form component fields with Bean Field Group for MODIFY edition
        setBeanFieldGroupWithComponent(((User) getFirstTable().getValue()), this,
            ((User) getFirstTable().getValue()).getIdUser());

        DialogBox box = new DialogBox(getApp());
        box.show(getApp().getMessageLocale(AppMessages.FormWarning),
            getApp().getMessageLocale(AppMessages.FormAreYouSureDelete), new ConfirmListener() {
              private static final long serialVersionUID = 3500651267535289099L;

              @Override
              public void onClose(DialogBox dialog) throws Exception {
                if (dialog.isConfirmed()) {

                  // Get selected record
                  User record = (User) getFieldGroup().getItemDataSource().getBean();

                  // Delete record in database
                  getFormModel().deleteRecord(getEntityClass(), record.getIdUser());

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
      Notification.show(getApp().getMessageLocale(AppMessages.FormError), e.getMessage(),
          Notification.Type.ERROR_MESSAGE);
    }
  }

  @Override
  public void applyValidations() {
    idUser.setEnabled(false);

    name.setRequired(true);
    name.setImmediate(true);
    name.setNullRepresentation("");
    name.setRequiredError(getApp().getMessageLocale(AppMessages.FormItemIsRequired));

    password.setRequired(true);
    password.setImmediate(true);
    password.setNullRepresentation("");
    password.setRequiredError(getApp().getMessageLocale(AppMessages.FormItemIsRequired));

    email.setImmediate(true);
    email.setNullRepresentation("");

    enabled.setRequired(true);
    enabled.setImmediate(true);
    enabled.setNullRepresentation("");
    enabled.setRequiredError(getApp().getMessageLocale(AppMessages.FormItemIsRequired));
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
    getFirstTable().setVisibleColumns("idUser", "name", "password", "email", "enabled");

    getFirstTable().sort(new Object[] { "idUser" }, new boolean[] { true });
  }

  @Override
  public void setLocaleComponents() {
    setFormTitle(getApp().getMessageLocale(AppMessages.User));

    // Locale Columns Table
    getFirstTable().setColumnHeader("idUser", getApp().getMessageLocale(AppMessages.UserId));
    getFirstTable().setColumnHeader("name", getApp().getMessageLocale(AppMessages.UserName));
    getFirstTable()
        .setColumnHeader("password", getApp().getMessageLocale(AppMessages.UserPassword));
    getFirstTable().setColumnHeader("email", getApp().getMessageLocale(AppMessages.UserEmail));
    getFirstTable().setColumnHeader("enabled", getApp().getMessageLocale(AppMessages.UserEnabled));

    // Locale main buttons
    btnCreate.setCaption(getApp().getMessageLocale(AppMessages.btnCreate));
    btnModify.setCaption(getApp().getMessageLocale(AppMessages.btnModify));
    btnEnable.setCaption(getApp().getMessageLocale(AppMessages.btnEnable));
    btnDelete.setCaption(getApp().getMessageLocale(AppMessages.btnDelete));

    // Locale Edit Form
    idUser.setCaption(getApp().getMessageLocale(AppMessages.UserId));
    name.setCaption(getApp().getMessageLocale(AppMessages.UserName));
    password.setCaption(getApp().getMessageLocale(AppMessages.UserPassword));
    email.setCaption(getApp().getMessageLocale(AppMessages.UserEmail));
    enabled.setCaption(getApp().getMessageLocale(AppMessages.UserEnabled));
  }

  @Override
  public void registerChildForms() throws Exception {
    // Prepare parameters to child form
    Map<String, Object> params = new HashMap<String, Object>();

    // Register child forms
    addDetailForm(
        "form.userProfile.UserProfileFormFacade",
        getApp().getMainView().openForm("form.userProfile.UserProfileFormFacade", params, null,
            false));

  }
}
