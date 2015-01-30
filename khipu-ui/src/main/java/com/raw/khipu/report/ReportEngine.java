package com.raw.khipu.report;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.persistence.EntityManager;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import com.raw.khipu.util.DAOFactory;
import com.vaadin.server.VaadinServlet;

public class ReportEngine {
  static Connection conn = null;
  public static String PDF_FORMAT = "Pdf";
  public static String EXCEL_FORMAT = "Excel";
  public static String HTML_FORMAT = "Html";
  public static String XML_FORMAT = "Xml";
  public static String PDF_VIEWER_PATH = "mozilla-pdf.js-5/web/viewer.html?file=tmp/";
  public static String TEMP_FOLDER = "";
  public static String REPORT_FOLDER = "/WEB-INF/rep/";

  public static void main(Object[] args) {
    // Get Report Object
    ReportView reportView = (ReportView) args[0];
    String theme = (String) args[1];

    TEMP_FOLDER = "/VAADIN/themes/" + theme.trim() + "/mozilla-pdf.js-5/web/tmp/";

    String reportFileName = reportView.getReportFileName();
    String reportFileNameTemporal = reportView.getReportFileNameTemporal();
    Map reportParams = reportView.getReportParams();
    String userTempFolder = reportView.getApp().getRegisteredUser().getName().trim() + "/";

    try {
      // Cargamos el driver JDBC
      // Class.forName("com.mysql.jdbc.Driver");
      // String user = "root";
      // String password = "admin";
      // conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/khipu", user, password);

      EntityManager em = DAOFactory.getEntityManagerFactory().createEntityManager();
      em.getTransaction().begin();
      conn = (Connection) em.unwrap(java.sql.Connection.class);
      conn.setAutoCommit(false);
      em.getTransaction().commit();

    } catch (SQLException e) {
      System.out.println("Error de conexión: " + e.getMessage());
      // } catch (ClassNotFoundException e) {
      // System.out.println("MYSQL JDBC Driver not found.");
    }

    try {
      String servletPath = VaadinServlet.getCurrent().getServletContext().getRealPath("/");

      JasperReport report = JasperCompileManager.compileReport(servletPath + REPORT_FOLDER
          + reportFileName + ".jrxml");

      JasperPrint print = JasperFillManager.fillReport(report, reportParams, conn);

      // Create user temporary folder, if exists, del all temporary files
      File userFolder = new File(servletPath + TEMP_FOLDER + userTempFolder);
      if (!userFolder.exists()) {
        userFolder.mkdirs();
      } else {
        String[] myFiles;
        if (userFolder.isDirectory()) {
          myFiles = userFolder.list();
          for (int i = 0; i < myFiles.length; i++) {
            File myFile = new File(userFolder, myFiles[i]);
            myFile.delete();
          }
        }
      }

      // Export to PDF
      JasperExportManager.exportReportToPdfFile(print, servletPath + TEMP_FOLDER + userTempFolder
          + reportFileNameTemporal + ".pdf");
      ByteArrayOutputStream output = new ByteArrayOutputStream();
      OutputStream outputfile = new FileOutputStream(new File(servletPath + TEMP_FOLDER
          + userTempFolder + reportFileNameTemporal + ".xls"));

      // Export to Excel
      JRXlsExporter exporterXLS = new JRXlsExporter();
      exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
      exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, output);
      exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
      exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
      exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
      exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
          Boolean.TRUE);
      exporterXLS.exportReport();
      outputfile.write(output.toByteArray());

      // Set PDF to Report Object
      reportView.showPDFPreview();

    } catch (java.io.FileNotFoundException e) {
      System.out.println("Bug : File not found.");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      // Cleanup antes de salir
      try {
        if (conn != null) {
          conn.rollback();
          conn.close();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  }

}