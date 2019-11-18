package demo.oci.mvc.samples;
/* Copyright (c) 2015, Oracle and/or its affiliates. All rights reserved.*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceSample11g {  
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
    Connection conn1 = null;
    Connection conn2 = null;
    Connection conn3 = null;
    // With AutoCloseable, the connection is closed automatically.
    try {
      Class.forName("oracle.jdbc.OracleDriver");

      // METHOD #1
      String dbURL1 = "jdbc:oracle:thin:test/We1c0me###DBA@150.136.221.162:1521/DB1118_iad3fb.subnetpub.demovcn.oraclevcn.com";
      conn1 = DriverManager.getConnection(dbURL1);
      if (conn1 != null) {
          System.out.println("Connected with connection #1");
      }

      // METHOD #2
      String dbURL2 = "jdbc:oracle:thin:@150.136.221.162:1521/DB1118_iad3fb.subnetpub.demovcn.oraclevcn.com";
      String username = "test";
      String password = "We1c0me###DBA";
      conn2 = DriverManager.getConnection(dbURL2, username, password);
      if (conn2 != null) {
          System.out.println("Connected with connection #2");
      }

      // METHOD #3
      // TODO: check - Exception in thread "main" java.lang.UnsatisfiedLinkError: no ocijdbc11 in java.library.path
      String dbURL3 = "jdbc:oracle:oci:@DB1118_iad3fb.subnetpub.demovcn.oraclevcn.com";
      Properties properties = new Properties();
      properties.put("user", "test");
      properties.put("password", "We1c0me###DBA");
      properties.put("defaultRowPrefetch", "20");
      conn3 = DriverManager.getConnection(dbURL3, properties);

      if (conn3 != null) {
          System.out.println("Connected with connection #3");
      }
      } catch (ClassNotFoundException ex) {
      ex.printStackTrace();
      } catch (SQLException ex) {
      ex.printStackTrace();
      } finally {
      
        try {
          if (conn1 != null && !conn1.isClosed()) {
              conn1.close();
          }
          if (conn2 != null && !conn2.isClosed()) {
              conn2.close();
          }
          if (conn3 != null && !conn3.isClosed()) {
              conn3.close();
          }
      } catch (SQLException ex) {
          ex.printStackTrace();
      }
      }
  }
}
