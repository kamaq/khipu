package com.raw.khipu.form.userProfile;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.raw.khipu.dto.Profile;
import com.raw.khipu.dto.User;
import com.raw.khipu.dto.UserProfile;
import com.raw.khipu.dto.UserprofilePK;
import com.raw.khipu.form.DialogBox;
import com.raw.khipu.form.DialogBox.ConfirmListener;
import com.raw.khipu.form.FormViewImpl;
import com.raw.khipu.i18n.AppMessages;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

public class UserProfileFormViewImpl extends FormViewImpl {

  private static final long serialVersionUID = 5011463172197746695L;
  private Button btnCreate;
  private Button btnModify;
  private Button btnEnable;
  private Button btnDelete;
  private ComboBox user;
  private ComboBox profile;
  private DateField dateInsert;
  private DateField dateUpdate;
  private TextField userInsert;
  private TextField userUpdate;
  private UserprofilePK id;

  public UserProfileFormViewImpl() {
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
    user = new ComboBox();
    profile = new ComboBox();
    dateInsert = new DateField();
    dateUpdate = new DateField();
    userInsert = new TextField();
    userUpdate = new TextField();

    getFormEditLayout().addComponent(user);
    getFormEditLayout().addComponent(profile);
    getFormEditLayout().addComponent(dateInsert);
    getFormEditLayout().addComponent(dateUpdate);
    getFormEditLayout().addComponent(userInsert);
    getFormEditLayout().addComponent(userUpdate);
  }

  @Override
  public void bindTableModelWithComponents() {
    try {
      // Bind datamodel list with Table Component
      setTableModelWithComponent(0,
          ((UserProfileFormModel) getFormModel()).getUserProfiles((User) getParameters()
              .get("user")), getFirstTable());

      // Bind datamodel list with ComboBox using indexed container to achieve entity-id conversion
      setEntityIndexedContainer(user, ((UserProfileFormModel) getFormModel()).getUsers());

      // Bind datamodel list with ComboBox using indexed container to achieve entity-id conversion
      setEntityIndexedContainer(profile, ((UserProfileFormModel) getFormModel()).getProfiles());

      // Set BeanItemContainer with Entity Class
      setPropertyBeanItemContainer(new BeanItemContainer<UserProfile>(UserProfile.class));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void setPrimaryKey(Object entity) {
    id = new UserprofilePK();
    id.setIdProfile(((Profile) profile.getConvertedValue()).getIdProfile());
    id.setIdUser(((User) user.getConvertedValue()).getIdUser());
    // add primary key to entity
    ((UserProfile) entity).setId(id);
  }

  @Override
  public void prepareEdition(String mode) {
    try {
      // Define the entity class for the first table
      setEntityClass(UserProfile.class);

      if (mode.equalsIgnoreCase(CREATE_MODE)) {
        // Bind form component fields with Bean Field Group for CREATE edition
        setBeanFieldGroupWithComponent(new UserProfile(), this, null);

        // Set values from parent form (only exists)
        user.setConvertedValue((User) getParameters().get("user"));
        user.setEnabled(true);
        profile.setEnabled(true);
        dateInsert.setValue(new Date());
        userInsert.setValue(Integer.toString(getApp().getRegisteredUser().getIdUser()));
        dateUpdate.setValue(new Date());
        userUpdate.setValue(Integer.toString(getApp().getRegisteredUser().getIdUser()));

        // Locale edit window title
        setEditWindowTitle(getApp().getMessageLocale(AppMessages.btnCreate) + " "
            + getApp().getMessageLocale(AppMessages.UserProfile));
      }

      if (mode.equalsIgnoreCase(MODIFY_MODE)) {
        // Bind form component fields with Bean Field Group for MODIFY edition
        setBeanFieldGroupWithComponent(((UserProfile) getFirstTable().getValue()), this,
            ((UserProfile) getFirstTable().getValue()).getId());

        // Set value (Entity) from selected record on table using converted value
        Profile mm = ((UserProfile) getFieldGroup().getItemDataSource().getBean()).getProfile();
        profile.setConvertedValue(mm);
        profile.setEnabled(false);

        dateUpdate.setValue(new Date());
        userUpdate.setValue(Integer.toString(getApp().getRegisteredUser().getIdUser()));

        // Locale edit window title
        setEditWindowTitle(getApp().getMessageLocale(AppMessages.btnModify) + " "
            + getApp().getMessageLocale(AppMessages.UserProfile));
      }

      if (mode.equalsIgnoreCase(CHANGE_STATUS_MODE)) {
        // Bind form component fields with Bean Field Group for MODIFY edition
        setBeanFieldGroupWithComponent(((UserProfile) getFirstTable().getValue()), this,
            ((UserProfile) getFirstTable().getValue()).getId());

        DialogBox box = new DialogBox(getApp());
        box.show(getApp().getMessageLocale(AppMessages.FormWarning),
            getApp().getMessageLocale(AppMessages.FormAreYouSureDisable), new ConfirmListener() {
              private static final long serialVersionUID = 3500651267535289099L;

              @Override
              public void onClose(DialogBox dialog) throws Exception {
                if (dialog.isConfirmed()) {
                  // Get selected record and udpdate it
                  UserProfile record = (UserProfile) getFieldGroup().getItemDataSource().getBean();
                  // record.setsetEnabled(0);

                  // Update record in database
                  getFormModel().updateRecord(getEntityClass(), record, record.getId());
                  // Refresh table component
                  getFirstTable().refreshRowCache();
                }
              }
            });
      }

      if (mode.equalsIgnoreCase(DELETE_MODE)) {
        // Bind form component fields with Bean Field Group for MODIFY edition
        setBeanFieldGroupWithComponent(((UserProfile) getFirstTable().getValue()), this,
            ((UserProfile) getFirstTable().getValue()).getId());

        DialogBox box = new DialogBox(getApp());
        box.show(getApp().getMessageLocale(AppMessages.FormWarning),
            getApp().getMessageLocale(AppMessages.FormAreYouSureDelete), new ConfirmListener() {
              private static final long serialVersionUID = 3500651267535289099L;

              @Override
              public void onClose(DialogBox dialog) throws Exception {
                if (dialog.isConfirmed()) {
                  // Get selected record
                  UserProfile record = (UserProfile) getFieldGroup().getItemDataSource().getBean();
                  // Delete record in database
                  getFormModel().deleteRecord(getEntityClass(), record.getId());
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
    user.setEnabled(false);
    user.setRequired(true);

    profile.setImmediate(true);
    profile.setRequired(true);

    dateInsert.setImmediate(true);
    dateInsert.setEnabled(false);

    dateUpdate.setImmediate(true);
    dateUpdate.setEnabled(false);

    userInsert.setImmediate(true);
    userInsert.setEnabled(false);

    userUpdate.setEnabled(false);
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
    getFirstTable().setVisibleColumns("user", "profile", "dateInsert", "dateUpdate", "userInsert",
        "userUpdate");

    getFirstTable().sort(new Object[] { "user", "profile" }, new boolean[] { true, true });
  }

  @Override
  public void setLocaleComponents() {
    setFormTitle(getApp().getMessageLocale(AppMessages.UserProfile));

    // Locale Columns Table
    getFirstTable().setColumnHeader("profile", getApp().getMessageLocale(AppMessages.Profile));
    getFirstTable().setColumnHeader("user", getApp().getMessageLocale(AppMessages.User));
    getFirstTable().setColumnHeader("dateInsert",
        getApp().getMessageLocale(AppMessages.UserProfileDateInsert));
    getFirstTable().setColumnHeader("dateUpdate",
        getApp().getMessageLocale(AppMessages.UserProfileDateUpdate));
    getFirstTable().setColumnHeader("userInsert",
        getApp().getMessageLocale(AppMessages.UserProfileUserInsert));
    getFirstTable().setColumnHeader("userUpdate",
        getApp().getMessageLocale(AppMessages.UserProfileUserUpdate));

    // Locale main buttons
    btnCreate.setCaption(getApp().getMessageLocale(AppMessages.btnCreate));
    btnModify.setCaption(getApp().getMessageLocale(AppMessages.btnModify));
    btnEnable.setCaption(getApp().getMessageLocale(AppMessages.btnEnable));
    btnDelete.setCaption(getApp().getMessageLocale(AppMessages.btnDelete));

    // Locale Edit Form
    profile.setCaption(getApp().getMessageLocale(AppMessages.UserProfile));
    user.setCaption(getApp().getMessageLocale(AppMessages.User));
    dateInsert.setCaption(getApp().getMessageLocale(AppMessages.UserProfileDateInsert));
    dateUpdate.setCaption(getApp().getMessageLocale(AppMessages.UserProfileDateUpdate));
    userInsert.setCaption(getApp().getMessageLocale(AppMessages.UserProfileUserInsert));
    userUpdate.setCaption(getApp().getMessageLocale(AppMessages.UserProfileUserUpdate));
  }

  @Override
  public void synchronizeWithParentForm(Object parent) {
    if (parent != null) {
      getParameters().put("user", parent);
      refreshComponents();
      getApp().getMainView().addForm(this);
    }
  }

  @Override
  public void registerChildForms() throws Exception {
    // Prepare parameters to child form
    Map<String, Object> params = new HashMap<String, Object>();
  }

}
