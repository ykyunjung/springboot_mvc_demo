package demo.oci.dbs.services;
/* Copyright (c) 2015, Oracle and/or its affiliates. All rights reserved.*/

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Service;

import demo.oci.dbs.common.DataSource;
import demo.oci.dbs.models.Employee;
import oracle.jdbc.OracleConnection;

@Service
public class EmployeeService {
  // final static String DB_URL= "jdbc:oracle:thin:@dbjungin191029_high?TNS_ADMIN=C:/workspace/sqldeveloper/wallet";
  // final static String DB_USER = "admin";
  // final static String DB_PASSWORD = "Dbrhksflwk123!";
  
  public Employee getEmployeesDetail(String name) throws Exception {

    DataSource ds = new DataSource();
    OracleConnection connection =  ds.getConnection();
    ResultSet resultSet;
    Employee employee;

    String query = "select first_name, last_name, email, phone, hire_date, job_title from employees where LOWER(first_name) = LOWER(?)";
    try{
      PreparedStatement pstatement = connection.prepareStatement(query);
      pstatement.setString(1, name);
      resultSet = pstatement.executeQuery();
      employee = new Employee();
      
      while(resultSet.next()){
        employee.setFirstName(resultSet.getString("first_name"));
        employee.setLastName(resultSet.getString("last_name"));
        employee.setEmail(resultSet.getString("email"));
        employee.setPhone(resultSet.getString("phone"));
        employee.setHireDate(resultSet.getString("hire_date"));
        employee.setJobTitle(resultSet.getString("job_title"));

        System.out.println("First Name : " + employee.getFirstName());
        System.out.println("Last Name : " + employee.getLastName());
        System.out.println("email : " + employee.getEmail());
        System.out.println("phone Name : " + employee.getPhone());
        System.out.println("hire date : " + employee.getHireDate());
        System.out.println("job title : " + employee.getJobTitle());
      }
      
    }catch(Exception Ex){
      throw new RuntimeException(Ex.getMessage());
    }finally{
      connection.close();
    }
    
    return employee;
  }
}
