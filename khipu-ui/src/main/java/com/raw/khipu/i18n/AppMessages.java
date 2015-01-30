package com.raw.khipu.i18n;

import java.io.Serializable;
import java.util.ListResourceBundle;

public class AppMessages extends ListResourceBundle implements Serializable {
  private static final long serialVersionUID = -1381196948880320212L;

  // Application
  public static final String AppTitle = generateId();
  public static final String AppWelcome = generateId();
  public static final String AppFootInfo = generateId();
  public static final String AppToolBarMenu = generateId();
  public static final String AppToolBarHome = generateId();
  public static final String AppToolBarFind = generateId();
  public static final String AppToolBarConfig = generateId();
  public static final String AppToolBarMessages = generateId();
  public static final String AppToolBarPerfil = generateId();
  public static final String AppTheme = generateId();
  public static final String AppTrade = generateId();
  public static final String AppProduct = generateId();
  public static final String AppProductDescription = generateId();
  public static final String AppSignature = generateId();
  public static final String AppSideBarParameters = generateId();
  public static final String AppSideBarBookmarks = generateId();
  public static final String AppSideBarMessages = generateId();
  public static final String AppSideBarAlerts = generateId();

  // Main
  public static final String MenuTitle = generateId();
  public static final String MenuSearch = generateId();

  // LoginScreen
  public static final String Username = generateId();
  public static final String Password = generateId();
  public static final String Login = generateId();
  public static final String LoginButton = generateId();
  public static final String ForgotPassword = generateId();
  public static final String InvalidUserOrPassword = generateId();
  public static final String InvalidUserOrPasswordLong = generateId();
  public static final String UsernameInputPrompt = generateId();
  public static final String UserRestoreAccount = generateId();
  public static final String UserMail = generateId();
  public static final String UserRestoreDescription = generateId();
  public static final String RestoreAccountSuccessMessage = generateId();
  public static final String RestoreAccountFailureMessage = generateId();

  // Buttons
  public static final String txtSearchBy = generateId();
  public static final String btnYes = generateId();
  public static final String btnNot = generateId();
  public static final String btnClose = generateId();
  public static final String btnCommit = generateId();
  public static final String btnRollback = generateId();
  public static final String btnSend = generateId();
  public static final String btnCreate = generateId();
  public static final String btnModify = generateId();
  public static final String btnEnable = generateId();
  public static final String btnCancel = generateId();
  public static final String btnReport = generateId();
  public static final String btnDelete = generateId();
  public static final String btnGrant = generateId();
  public static final String btnPrint = generateId();
  public static final String btnProcess = generateId();
  public static final String btnValid = generateId();
  public static final String btnVerify = generateId();
  public static final String btnChange = generateId();
  public static final String btnExit = generateId();
  public static final String btnExecute = generateId();
  public static final String btnExportToExcel = generateId();
  public static final String btnSendByEmail = generateId();
  public static final String btnDelFromListPrompt = generateId();

  // Profile
  public static final String Module = generateId();
  public static final String ModuleId = generateId();
  public static final String ModuleName = generateId();
  public static final String ModuleDescription = generateId();
  public static final String ModuleImageFileName = generateId();
  public static final String ReportModuleTitle = generateId();
  public static final String Component = generateId();
  public static final String ComponentId = generateId();
  public static final String ComponentName = generateId();
  public static final String ComponentDescription = generateId();
  public static final String ComponentClassName = generateId();
  public static final String ComponentImageFileName = generateId();
  public static final String ReportComponentTitle = generateId();
  public static final String Function = generateId();
  public static final String FunctionId = generateId();
  public static final String FunctionName = generateId();
  public static final String Profile = generateId();
  public static final String ProfileId = generateId();
  public static final String ProfileName = generateId();
  public static final String ProfileEnabled = generateId();
  public static final String ProfileDetail = generateId();
  public static final String ProfileDetailEnabled = generateId();

  // Forms
  public static final String FormWarning = generateId();
  public static final String FormError = generateId();
  public static final String FormInformation = generateId();
  public static final String FormMustSelectRecord = generateId();
  public static final String FormAreYouSureDisable = generateId();
  public static final String FormAreYouSureDelete = generateId();
  public static final String FormMustFillMandatoryItems = generateId();
  public static final String FormItemIsRequired = generateId();
  public static final String FormMustSelectColumnForSearch = generateId();
  public static final String FormAreYouSureSend = generateId();
  public static final String FormMailTitle = generateId();
  public static final String FormMailSubject = generateId();
  public static final String FormMailTo = generateId();
  public static final String FormMailAddEmail = generateId();
  public static final String FormMailAttachments = generateId();
  public static final String FormMailRecipients = generateId();
  public static final String FormMailMessage = generateId();
  public static final String FormMsgDefaultGreeting = generateId();
  public static final String FormMsgDefaultIntro = generateId();
  public static final String FormMsgDefaultEssential = generateId();
  public static final String FormMsgDefaultBye = generateId();

  // User
  public static final String User = generateId();
  public static final String UserId = generateId();
  public static final String UserName = generateId();
  public static final String UserPassword = generateId();
  public static final String UserEmail = generateId();
  public static final String UserEnabled = generateId();
  public static final String UserProfile = generateId();
  public static final String UserProfileDateInsert = generateId();
  public static final String UserProfileDateUpdate = generateId();
  public static final String UserProfileUserInsert = generateId();
  public static final String UserProfileUserUpdate = generateId();

  // Personal
  public static final String Personal = generateId();
  public static final String PersonalId = generateId();
  public static final String PersonalName = generateId();
  public static final String PersonalEmail = generateId();

  public static String generateId() {
    return new Integer(ids++).toString();
  }

  static int ids = 0;

  @Override
  public Object[][] getContents() {
    return null;
  }
}