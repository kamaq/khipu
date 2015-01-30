package com.raw.khipu.form.function;

import com.raw.khipu.dto.Component;
import com.raw.khipu.dto.Function;
import com.raw.khipu.dto.FunctionPK;
import com.raw.khipu.form.DialogBox;
import com.raw.khipu.form.DialogBox.ConfirmListener;
import com.raw.khipu.form.FormViewImpl;
import com.raw.khipu.i18n.AppMessages;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

public class FunctionFormViewImpl extends FormViewImpl {

  private static final long serialVersionUID = 5011463172197746695L;
  private Button btnCreate;
  private Button btnModify;
  private Button btnEnable;
  private Button btnDelete;
  private ComboBox component;
  private TextField idFunction;
  private TextField name;
  private FunctionPK id;

  public FunctionFormViewImpl() {
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
    component = new ComboBox();
    idFunction = new TextField();
    name = new TextField();

    getFormEditLayout().addComponent(component);
    getFormEditLayout().addComponent(idFunction);
    getFormEditLayout().addComponent(name);
  }

  @Override
  public void bindTableModelWithComponents() {
    try {
      // Bind datamodel list with Table Component (obtain from object parent if exists as parameter)
      setTableModelWithComponent(
          0,
          ((FunctionFormModel) getFormModel()).getFunctions((Component) getParameters().get(
              "component")), getFirstTable());

      // Bind datamodel list with ComboBox using indexed container to achieve entity-id conversion
      setEntityIndexedContainer(component, ((FunctionFormModel) getFormModel()).getComponents(null));

      // Set BeanItemContainer with Entity Class
      setPropertyBeanItemContainer(new BeanItemContainer<Function>(Function.class));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void setPrimaryKey(Object entity) {
    id = new FunctionPK();
    id.setIdComponent(((Component) component.getConvertedValue()).getIdComponent());
    id.setIdFunction(idFunction.getValue());
    // add primary key to entity
    ((Function) entity).setId(id);
  }

  @Override
  public void prepareEdition(String mode) {
    try {
      // Define the entity class for the first table
      setEntityClass(Function.class);

      if (mode.equalsIgnoreCase(CREATE_MODE)) {
        // Bind form component fields with Bean Field Group for CREATE edition
        setBeanFieldGroupWithComponent(new Function(), this, null);

        // Set values from parent form (only exists)
        component.setConvertedValue((Component) getParameters().get("component"));
        component.setEnabled(true);
        idFunction.setEnabled(true);
        idFunction.setValue(null);

        // Locale edit window title
        setEditWindowTitle(getApp().getMessageLocale(AppMessages.btnCreate) + " "
            + getApp().getMessageLocale(AppMessages.Function));
      }

      if (mode.equalsIgnoreCase(MODIFY_MODE)) {
        // Bind form component fields with Bean Field Group for MODIFY edition
        setBeanFieldGroupWithComponent(((Function) getFirstTable().getValue()), this,
            ((Function) getFirstTable().getValue()).getId());

        // Set value from selected record on table
        idFunction.setValue(((Function) getFieldGroup().getItemDataSource().getBean()).getId()
            .getIdFunction());

        component.setConvertedValue(((Function) getFieldGroup().getItemDataSource().getBean())
            .getComponent());

        // Locale edit window title
        setEditWindowTitle(getApp().getMessageLocale(AppMessages.btnModify) + " "
            + getApp().getMessageLocale(AppMessages.Function));
      }

      if (mode.equalsIgnoreCase(CHANGE_STATUS_MODE)) {
        // Bind form component fields with Bean Field Group for MODIFY edition
        setBeanFieldGroupWithComponent(((Function) getFirstTable().getValue()), this,
            ((Function) getFirstTable().getValue()).getId().getIdFunction());

        DialogBox box = new DialogBox(getApp());
        box.show(getApp().getMessageLocale(AppMessages.FormWarning),
            getApp().getMessageLocale(AppMessages.FormAreYouSureDisable), new ConfirmListener() {
              private static final long serialVersionUID = 3500651267535289099L;

              @Override
              public void onClose(DialogBox dialog) throws Exception {
                if (dialog.isConfirmed()) {
                  // Get selected record and udpdate it
                  Function record = (Function) getFieldGroup().getItemDataSource().getBean();
                  record.setName("[Deprecated]");

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
        setBeanFieldGroupWithComponent(((Function) getFirstTable().getValue()), this,
            ((Function) getFirstTable().getValue()).getId().getIdFunction());

        DialogBox box = new DialogBox(getApp());
        box.show(getApp().getMessageLocale(AppMessages.FormWarning),
            getApp().getMessageLocale(AppMessages.FormAreYouSureDelete), new ConfirmListener() {
              private static final long serialVersionUID = 3500651267535289099L;

              @Override
              public void onClose(DialogBox dialog) throws Exception {
                if (dialog.isConfirmed()) {
                  // Get selected record
                  Function record = (Function) getFieldGroup().getItemDataSource().getBean();
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
    component.setImmediate(true);
    component.setEnabled(false);

    idFunction.setRequired(true);
    idFunction.setImmediate(true);
    idFunction.setEnabled(false);
    idFunction.setNullRepresentation("");
    idFunction.setRequiredError(getApp().getMessageLocale(AppMessages.FormItemIsRequired));

    name.setRequired(true);
    name.setImmediate(true);
    name.setNullRepresentation("");
    name.setRequiredError(getApp().getMessageLocale(AppMessages.FormItemIsRequired));
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
    getFirstTable().setVisibleColumns("component", "id", "name");

    getFirstTable().sort(new Object[] { "component", "id" }, new boolean[] { true, true });
  }

  @Override
  public void setLocaleComponents() {
    setFormTitle(getApp().getMessageLocale(AppMessages.Function));

    // Locale Columns Table
    getFirstTable()
        .setColumnHeader("component", getApp().getMessageLocale(AppMessages.ComponentId));
    getFirstTable().setColumnHeader("id", getApp().getMessageLocale(AppMessages.FunctionId));
    getFirstTable().setColumnHeader("name", getApp().getMessageLocale(AppMessages.FunctionName));

    // Locale main buttons
    btnCreate.setCaption(getApp().getMessageLocale(AppMessages.btnCreate));
    btnModify.setCaption(getApp().getMessageLocale(AppMessages.btnModify));
    btnEnable.setCaption(getApp().getMessageLocale(AppMessages.btnEnable));
    btnDelete.setCaption(getApp().getMessageLocale(AppMessages.btnDelete));

    // Locale Edit Form
    component.setCaption(getApp().getMessageLocale(AppMessages.ComponentId));
    idFunction.setCaption(getApp().getMessageLocale(AppMessages.FunctionId));
    name.setCaption(getApp().getMessageLocale(AppMessages.ComponentName));
  }

  @Override
  public void synchronizeWithParentForm(Object parent) {
    if (parent != null) {
      getParameters().put("component", parent);
      refreshComponents();
      getApp().getMainView().addForm(this);
    }
  }

}
