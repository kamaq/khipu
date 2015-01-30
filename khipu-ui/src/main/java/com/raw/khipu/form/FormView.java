package com.raw.khipu.form;

import java.util.List;
import java.util.Map;

import com.raw.khipu.KhipuAppUI;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Window;

public interface FormView {

  public interface FormViewListener {

    public void buttonClick(String operation);

  }

  public void setBeanFieldGroupWithComponent(Object entity, FormView formView, int entityId);

  public void setBeanFieldGroupWithComponent(Object entity, FormView formView, Object entityId);

  public BeanFieldGroup<Object> getFieldGroup();

  public void openEditWindow(String mode);

  public void setEditWindowTitle(String title);

  public FormModel getFormModel();

  public void setFormModel(FormModel formModel);

  public FormLayout getFormEditLayout();

  public Label getFormTitle();

  public void setFormTitle(String formTitle);

  public Table getFirstTable();

  public GridLayout getToolBar();

  public void addListener(FormViewListener listener);

  public List<String> getGrantedFunctions();

  public void init();

  public Class<?> getEntityClass();

  public void setEntityClass(Class<?> entityClass);

  public KhipuAppUI getApp();

  public void setApp(KhipuAppUI app);

  public void setRights();

  public List<Object> getTableModelList();

  public BeanItemContainer getPropertyBeanItemContainer();

  public void setPropertyBeanItemContainer(BeanItemContainer beanItemProperty);

  public void populateComponents();

  public void populateComponent(int index);

  public void setTableModelWithComponent(int index, Object tableModel, Component component);

  public void setWindow(Window window);

  public void removeWindow();

  public String getFormNameClass();

  public void setFormNameClass(String formNameClass);

  public Object getParameter(String paramName);

  public void setParameter(String paramName, Object value);

  public Map<String, Object> getParameters();

  public void setParameters(Map<String, Object> parameters);

  public void bindTableModelWithComponents();

  public void prepareEdition(String mode);

  public void setVisibleColumns();

  public void setLocaleComponents();

  public void applyValidations();

  public void refreshComponents();

  public void searchByParameters();

  public void setEntityIndexedContainer(AbstractSelect selectComponent, Object dataList);

  public void addDetailForm(String formName, CustomComponent form);

  public void synchronizeWithParentForm(Object parent);

  public void registerChildForms() throws Exception;

  public void setPrimaryKey(Object entity);
}
