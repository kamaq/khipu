package com.raw.khipu.form.profile;

import java.util.HashMap;
import java.util.Map;

import com.raw.khipu.dto.Profile;
import com.raw.khipu.form.DialogBox;
import com.raw.khipu.form.DialogBox.ConfirmListener;
import com.raw.khipu.form.FormViewImpl;
import com.raw.khipu.i18n.AppMessages;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

public class ProfileFormViewImpl extends FormViewImpl {

  private static final long serialVersionUID = 5011463172197746695L;
  private Button btnCreate;
  private Button btnModify;
  private Button btnEnable;
  private Button btnDelete;
  private TextField idProfile;
  private TextField name;
  private TextField enabled;

  public ProfileFormViewImpl() {
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
    idProfile = new TextField();
    name = new TextField();
    enabled = new TextField();

    getFormEditLayout().addComponent(idProfile);
    getFormEditLayout().addComponent(name);
    getFormEditLayout().addComponent(enabled);
  }

  @Override
  public void bindTableModelWithComponents() {
    try {
      // Bind dat model list with Table Component
      setTableModelWithComponent(0, ((ProfileFormModel) getFormModel()).getProfiles(),
          getFirstTable());

      // Set BeanItemContainer with Entity Class
      setPropertyBeanItemContainer(new BeanItemContainer<Profile>(Profile.class));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void prepareEdition(String mode) {
    try {
      // Define the entity class for the first table
      setEntityClass(Profile.class);

      if (mode.equalsIgnoreCase(CREATE_MODE)) {
        // Bind form component fields with Bean Field Group for CREATE edition
        setBeanFieldGroupWithComponent(new Profile(), this, null);
        // Locale edit window title
        setEditWindowTitle(getApp().getMessageLocale(AppMessages.btnCreate) + " "
            + getApp().getMessageLocale(AppMessages.Profile));
      }

      if (mode.equalsIgnoreCase(MODIFY_MODE)) {
        // Bind form component fields with Bean Field Group for MODIFY edition
        setBeanFieldGroupWithComponent(((Profile) getFirstTable().getValue()), this,
            ((Profile) getFirstTable().getValue()).getIdProfile());
        // Locale edit window title
        setEditWindowTitle(getApp().getMessageLocale(AppMessages.btnModify) + " "
            + getApp().getMessageLocale(AppMessages.Profile));
      }

      if (mode.equalsIgnoreCase(CHANGE_STATUS_MODE)) {
        // Bind form component fields with Bean Field Group for MODIFY edition
        setBeanFieldGroupWithComponent(((Profile) getFirstTable().getValue()), this,
            ((Profile) getFirstTable().getValue()).getIdProfile());

        DialogBox box = new DialogBox(getApp());
        box.show(getApp().getMessageLocale(AppMessages.FormWarning),
            getApp().getMessageLocale(AppMessages.FormAreYouSureDisable), new ConfirmListener() {
              private static final long serialVersionUID = 3500651267535289099L;

              @Override
              public void onClose(DialogBox dialog) throws Exception {
                if (dialog.isConfirmed()) {

                  // Get selected record and udpdate it
                  Profile record = (Profile) getFieldGroup().getItemDataSource().getBean();
                  record.setEnabled(0);

                  // Update record in database
                  getFormModel().updateRecord(getEntityClass(), record, record.getIdProfile());
                  // Refresh table component
                  getFirstTable().refreshRowCache();
                }
              }
            });
      }

      if (mode.equalsIgnoreCase(DELETE_MODE)) {
        // Bind form component fields with Bean Field Group for MODIFY edition
        setBeanFieldGroupWithComponent(((Profile) getFirstTable().getValue()), this,
            ((Profile) getFirstTable().getValue()).getIdProfile());

        DialogBox box = new DialogBox(getApp());
        box.show(getApp().getMessageLocale(AppMessages.FormWarning),
            getApp().getMessageLocale(AppMessages.FormAreYouSureDelete), new ConfirmListener() {
              private static final long serialVersionUID = 3500651267535289099L;

              @Override
              public void onClose(DialogBox dialog) throws Exception {
                if (dialog.isConfirmed()) {

                  // Get selected record
                  Profile record = (Profile) getFieldGroup().getItemDataSource().getBean();

                  // Delete record in database
                  getFormModel().deleteRecord(getEntityClass(), record.getIdProfile());

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
    idProfile.setEnabled(false);

    name.setRequired(true);
    name.setImmediate(true);
    name.setNullRepresentation("");
    name.setRequiredError(getApp().getMessageLocale(AppMessages.FormItemIsRequired));

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
    getFirstTable().setVisibleColumns("idProfile", "name", "enabled");

    getFirstTable().sort(new Object[] { "idProfile" }, new boolean[] { true, true });
  }

  @Override
  public void setLocaleComponents() {
    setFormTitle(getApp().getMessageLocale(AppMessages.Profile));

    // Locale Columns Table
    getFirstTable().setColumnHeader("idProfile", getApp().getMessageLocale(AppMessages.ProfileId));
    getFirstTable().setColumnHeader("name", getApp().getMessageLocale(AppMessages.ProfileName));
    getFirstTable().setColumnHeader("enabled",
        getApp().getMessageLocale(AppMessages.ProfileEnabled));

    // Locale main buttons
    btnCreate.setCaption(getApp().getMessageLocale(AppMessages.btnCreate));
    btnModify.setCaption(getApp().getMessageLocale(AppMessages.btnModify));
    btnEnable.setCaption(getApp().getMessageLocale(AppMessages.btnEnable));
    btnDelete.setCaption(getApp().getMessageLocale(AppMessages.btnDelete));

    // Locale Edit Form
    idProfile.setCaption(getApp().getMessageLocale(AppMessages.ProfileId));
    name.setCaption(getApp().getMessageLocale(AppMessages.ProfileName));
    enabled.setCaption(getApp().getMessageLocale(AppMessages.ProfileEnabled));
  }

  @Override
  public void registerChildForms() throws Exception {
    // Prepare parameters to child form
    Map<String, Object> params = new HashMap<String, Object>();

    // Register child forms
    addDetailForm(
        "form.profileDetail.ProfileDetailFormFacade",
        getApp().getMainView().openForm("form.profileDetail.ProfileDetailFormFacade", params, null,
            false));

  }
}
