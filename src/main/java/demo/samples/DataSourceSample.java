package demo.samples;
/* Copyright (c) 2015, Oracle and/or its affiliates. All rights reserved.*/

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import demo.oci.dbs.models.Employee;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;

public class DataSourceSample {  
  // The recommended format of a connection URL is the long format with the
  // connection descriptor.
  final static String DB_URL= "jdbc:oracle:thin:@//localhost:1521/pdb1.subnetdb.pocvcn.oraclevcn.com";
  // For ATP and ADW - use the TNS Alias name along with the TNS_ADMIN when using 18.3 JDBC driver
  // final static String DB_URL="jdbc:oracle:thin:@wallet_dbname?TNS_ADMIN=/Users/test/wallet_dbname";
  // In case of windows, use the following URL 
  // final static String DB_URL="jdbc:oracle:thin:@wallet_dbname?TNS_ADMIN=C:\\Users\\test\\wallet_dbname";
  final static String DB_USER = "test";
  final static String DB_PASSWORD = "We1c0me###DBA";

 /*
  * The method gets a database connection using 
  * oracle.jdbc.pool.OracleDataSource. It also sets some connection 
  * level properties, such as,
  * OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH,
  * OracleConnection.CONNECTION_PROPERTY_THIN_NET_CHECKSUM_TYPES, etc.,
  * There are many other connection related properties. Refer to 
  * the OracleConnection interface to find more. 
  */
  public static void main(String args[]) throws SQLException {
    Properties info = new Properties();     
    info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_USER);
    info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_PASSWORD);          
    info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");    
  

    OracleDataSource ods = new OracleDataSource();
    ods.setURL(DB_URL);    
    ods.setConnectionProperties(info);

    // With AutoCloseable, the connection is closed automatically.
    try (OracleConnection connection = (OracleConnection) ods.getConnection()) {
      // Get the JDBC driver name and version 
      DatabaseMetaData dbmd = connection.getMetaData();       
      System.out.println("Driver Name: " + dbmd.getDriverName());
      System.out.println("Driver Version: " + dbmd.getDriverVersion());
      // Print some connection properties
      System.out.println("Default Row Prefetch Value is: " + 
         connection.getDefaultRowPrefetch());
      System.out.println("Database Username is: " + connection.getUserName());
      System.out.println();
      // Perform a database operation 
      printEmployeesDetail(connection);
    }   
  }

  public static Employee printEmployeesDetail(Connection connection) throws SQLException {
    String query = "select first_name, last_name, email, phone, hire_date, job_title from employees where first_name = ?";
    PreparedStatement pstatement = connection.prepareStatement(query);
    pstatement.setString(1, "Thea");
    ResultSet resultSet = pstatement.executeQuery();

    System.out.println("FIRST_NAME" + "|" + "LAST_NAME" + "|" + "EMAIL" + "|" + "PHONE" + "|" + "HIRE_DATE" + "|" + "JOB_TITLE");
    System.out.println("---------------------");
    while (resultSet.next())
      System.out.println(resultSet.getString(1) + ","
          + resultSet.getString(2) + ","
          + resultSet.getString(3) + ","
          + resultSet.getString(4) + ","
          + resultSet.getString(5) + ","
          + resultSet.getString(6) + ","
          );  

    Employee employee = new Employee();

    while(resultSet.next()){
      employee.setFirstName(resultSet.getString("first_name"));
      employee.setLastName(resultSet.getString("last_name"));
      employee.setEmail(resultSet.getString("email"));
      employee.setPhone(resultSet.getString("phone"));
      employee.setHireDate(resultSet.getString("hire_date"));
      employee.setJobTitle(resultSet.getString("job_title"));
    }
    
    return employee;
  }
 /*
  * Displays first_name and last_name from the employees table.
  */
  public static void printEmployees(Connection connection) throws SQLException {
    // Statement and ResultSet are AutoCloseable and closed automatically. 
    try (Statement statement = connection.createStatement()) {      
      try (ResultSet resultSet = statement
          .executeQuery("select first_name, last_name, email, phone, hire_date, job_title from employees")) {
        System.out.println("FIRST_NAME" + "|" + "LAST_NAME" + "|" + "EMAIL" + "|" + "PHONE" + "|" + "HIRE_DATE" + "|" + "JOB_TITLE");
        System.out.println("---------------------");
        while (resultSet.next())
          System.out.println(resultSet.getString(1) + " "
              + resultSet.getString(2) + " "
              + resultSet.getString(3) + " "
              + resultSet.getString(4) + " "
              + resultSet.getString(5) + " "
              + resultSet.getString(6) + " "
              );       
      }
    }   
  } 
}
