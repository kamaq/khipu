package com.raw.khipu.form.profileDetail;

import java.util.HashMap;
import java.util.Map;

import com.raw.khipu.dto.Component;
import com.raw.khipu.dto.Function;
import com.raw.khipu.dto.Module;
import com.raw.khipu.dto.Profile;
import com.raw.khipu.dto.ProfileDetail;
import com.raw.khipu.dto.ProfiledetailPK;
import com.raw.khipu.form.DialogBox;
import com.raw.khipu.form.DialogBox.ConfirmListener;
import com.raw.khipu.form.FormViewImpl;
import com.raw.khipu.i18n.AppMessages;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnHeaderMode;
import com.vaadin.ui.TextField;

public class ProfileDetailFormViewImpl extends FormViewImpl {

  private static final long serialVersionUID = 5011463172197746695L;
  private Button btnCreate;
  private Button btnModify;
  private Button btnEnable;
  private Button btnDelete;
  private ComboBox profile;
  private Table module;
  private Table component;
  private Table function;
  private TextField enabled;
  private ProfiledetailPK id;

  public ProfileDetailFormViewImpl() {
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
    profile = new ComboBox();
    module = new Table();
    component = new Table();
    function = new Table();
    enabled = new TextField();

    getFormEditLayout().addComponent(profile);
    getFormEditLayout().addComponent(module);
    getFormEditLayout().addComponent(component);
    getFormEditLayout().addComponent(function);
    getFormEditLayout().addComponent(enabled);
  }

  @Override
  public void bindTableModelWithComponents() {
    try {
      // Bind datamodel list with Table Component
      setTableModelWithComponent(0,
          ((ProfileDetailFormModel) getFormModel()).getProfileDetails((Profile) getParameters()
              .get("profile")), getFirstTable());

      // Bind datamodel list with ComboBox using indexed container to achieve entity-id conversion
      setEntityIndexedContainer(profile, ((ProfileDetailFormModel) getFormModel()).getProfiles());

      // Bind datamodel list with ComboBox using indexed container to achieve entity-id conversion
      setEntityIndexedContainer(module, ((ProfileDetailFormModel) getFormModel()).getModules());

      module.addValueChangeListener(new ValueChangeListener() {
        private static final long serialVersionUID = 6516822672080583577L;

        @Override
        public void valueChange(ValueChangeEvent event) {
          try {
            // Bind datamodel list with ComboBox using indexed container to achieve entity-id
            // conversion
            setEntityIndexedContainer(component, ((ProfileDetailFormModel) getFormModel())
                .getComponents((Module) module.getConvertedValue()));
            component.setVisibleColumns("bean");
          } catch (Exception e) {
            e.printStackTrace();
          }

        }
      });

      component.addValueChangeListener(new ValueChangeListener() {
        private static final long serialVersionUID = 8190992988656646404L;

        @Override
        public void valueChange(ValueChangeEvent event) {
          try {
            // Bind datamodel list with ComboBox using indexed container to achieve entity-id
            // conversion
            setEntityIndexedContainer(function, ((ProfileDetailFormModel) getFormModel())
                .getFunctions((Component) component.getConvertedValue()));
            function.setVisibleColumns("bean");
          } catch (Exception e) {
            e.printStackTrace();
          }

        }
      });

      // Set BeanItemContainer with Entity Class
      setPropertyBeanItemContainer(new BeanItemContainer<ProfileDetail>(ProfileDetail.class));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void setPrimaryKey(Object entity) {
    id = new ProfiledetailPK();
    id.setIdComponent(((Component) component.getConvertedValue()).getIdComponent());
    id.setIdFunction(((Function) function.getConvertedValue()).getId().getIdFunction());
    id.setIdProfile(((Profile) profile.getConvertedValue()).getIdProfile());
    // add pimary key to entity
    ((ProfileDetail) entity).setId(id);
  }

  @Override
  public void prepareEdition(String mode) {
    try {
      // Define the entity class for the first table
      setEntityClass(ProfileDetail.class);

      if (mode.equalsIgnoreCase(CREATE_MODE)) {
        // Bind form component fields with Bean Field Group for CREATE edition
        setBeanFieldGroupWithComponent(new ProfileDetail(), this, null);

        // Set values from parent form (only exists)
        profile.setConvertedValue((Profile) getParameters().get("profile"));

        profile.setEnabled(true);
        module.setEnabled(true);
        component.setEnabled(true);
        function.setEnabled(true);

        // Locale edit window title
        setEditWindowTitle(getApp().getMessageLocale(AppMessages.btnCreate) + " "
            + getApp().getMessageLocale(AppMessages.ProfileDetail));
      }

      if (mode.equalsIgnoreCase(MODIFY_MODE)) {
        // Bind form component fields with Bean Field Group for MODIFY edition
        setBeanFieldGroupWithComponent(((ProfileDetail) getFirstTable().getValue()), this,
            ((ProfileDetail) getFirstTable().getValue()).getId());

        // Set value (Entity) from selected record on table using converted value
        ProfileDetail mm = ((ProfileDetail) getFieldGroup().getItemDataSource().getBean());

        profile.setConvertedValue(mm.getProfile());
        profile.setEnabled(false);
        module.setConvertedValue(mm.getFunction().getComponent().getModule());
        module.setEnabled(false);
        component.setConvertedValue(mm.getFunction().getComponent());
        component.setEnabled(false);
        function.setConvertedValue(mm.getFunction());
        function.setEnabled(false);

        // Locale edit window title
        setEditWindowTitle(getApp().getMessageLocale(AppMessages.btnModify) + " "
            + getApp().getMessageLocale(AppMessages.ProfileDetail));
      }

      if (mode.equalsIgnoreCase(CHANGE_STATUS_MODE)) {
        // Bind form component fields with Bean Field Group for MODIFY edition
        setBeanFieldGroupWithComponent(((ProfileDetail) getFirstTable().getValue()), this,
            ((ProfileDetail) getFirstTable().getValue()).getId());

        DialogBox box = new DialogBox(getApp());
        box.show(getApp().getMessageLocale(AppMessages.FormWarning),
            getApp().getMessageLocale(AppMessages.FormAreYouSureDisable), new ConfirmListener() {
              private static final long serialVersionUID = 3500651267535289099L;

              @Override
              public void onClose(DialogBox dialog) throws Exception {
                if (dialog.isConfirmed()) {
                  // Get selected record and udpdate it
                  ProfileDetail record = (ProfileDetail) getFieldGroup().getItemDataSource()
                      .getBean();
                  record.setEnabled(0);

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
        setBeanFieldGroupWithComponent(((ProfileDetail) getFirstTable().getValue()), this,
            ((ProfileDetail) getFirstTable().getValue()).getId());

        DialogBox box = new DialogBox(getApp());
        box.show(getApp().getMessageLocale(AppMessages.FormWarning),
            getApp().getMessageLocale(AppMessages.FormAreYouSureDelete), new ConfirmListener() {
              private static final long serialVersionUID = 3500651267535289099L;

              @Override
              public void onClose(DialogBox dialog) throws Exception {
                if (dialog.isConfirmed()) {

                  // Get selected record
                  ProfileDetail record = (ProfileDetail) getFieldGroup().getItemDataSource()
                      .getBean();
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
    profile.setImmediate(true);

    module.setImmediate(true);
    module.setPageLength(4);
    module.setSelectable(true);
    module.setVisibleColumns("bean");
    module.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);

    component.setImmediate(true);
    component.setPageLength(4);
    component.setSelectable(true);
    component.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);

    function.setRequired(true);
    function.setImmediate(true);
    function.setPageLength(4);
    function.setSelectable(true);
    function.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
    function.setRequiredError(getApp().getMessageLocale(AppMessages.FormItemIsRequired));

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
    getFirstTable().setVisibleColumns("profile", "function", "enabled");

    getFirstTable().sort(new Object[] { "profile", "function" }, new boolean[] { true, true });
  }

  @Override
  public void setLocaleComponents() {
    setFormTitle(getApp().getMessageLocale(AppMessages.ProfileDetail));

    // Locale Columns Table
    getFirstTable().setColumnHeader("profile", getApp().getMessageLocale(AppMessages.Profile));
    getFirstTable().setColumnHeader("id", getApp().getMessageLocale(AppMessages.ProfileDetail));
    getFirstTable().setColumnHeader("function", getApp().getMessageLocale(AppMessages.Function));
    getFirstTable().setColumnHeader("enabled",
        getApp().getMessageLocale(AppMessages.ProfileDetailEnabled));

    // Locale main buttons
    btnCreate.setCaption(getApp().getMessageLocale(AppMessages.btnCreate));
    btnModify.setCaption(getApp().getMessageLocale(AppMessages.btnModify));
    btnEnable.setCaption(getApp().getMessageLocale(AppMessages.btnEnable));
    btnDelete.setCaption(getApp().getMessageLocale(AppMessages.btnDelete));

    // Locale Edit Form
    profile.setCaption(getApp().getMessageLocale(AppMessages.Profile));
    module.setCaption(getApp().getMessageLocale(AppMessages.Module));
    component.setCaption(getApp().getMessageLocale(AppMessages.Component));
    function.setCaption(getApp().getMessageLocale(AppMessages.Function));
    enabled.setCaption(getApp().getMessageLocale(AppMessages.ProfileDetailEnabled));
  }

  @Override
  public void synchronizeWithParentForm(Object parent) {
    if (parent != null) {
      getParameters().put("profile", parent);
      refreshComponents();
      getApp().getMainView().addForm(this);
    }
  }

  @Override
  public void registerChildForms() throws Exception {
    // Prepare parameters to child form
    Map<String, Object> params = new HashMap<String, Object>();

    // Register child forms
    // addDetailForm("form.function.FunctionFormFacade",
    // getApp().getMainView().openForm("form.function.FunctionFormFacade", params, null, false));
  }

}
