package demo.oci.mvc.common;

import java.util.Properties;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;
 
public class DataSource{
    // final static String DB_URL= "jdbc:oracle:thin:@//localhost:1521/pdb1.subnetdb.pocvcn.oraclevcn.com";
    final static String DB_URL= "jdbc:oracle:thin:@150.136.221.162:1521/DB1118_iad3fb.subnetpub.demovcn.oraclevcn.com";
        final static String DB_USER = "test";
    final static String DB_PASSWORD = "We1c0me###DBA";
    OracleConnection connection;

    public OracleConnection getConnection()  throws Exception {

        Properties info = new Properties();     
        info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_USER);
        info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_PASSWORD);          
        info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");    
      
        OracleDataSource ods = new OracleDataSource();
        ods.setURL(DB_URL);    
        ods.setConnectionProperties(info);
    
        try{
            connection = (OracleConnection) ods.getConnection();
        }catch(Exception Ex){
            throw new RuntimeException();
        }        
        
        return connection;
    }

}