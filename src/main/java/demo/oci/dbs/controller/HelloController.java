package demo.oci.dbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import demo.oci.dbs.models.Employee;
import demo.oci.dbs.services.EmployeeService;

 
@Controller 
public class HelloController{

   @Autowired
   EmployeeService employeeService;

   @RequestMapping("/")
   public String index() {
      return "index";
   }

   @PostMapping("/hello")
   public String sayHello(@RequestParam("name") String name, Model model) throws Exception {

      Employee emp = employeeService.getEmployeesDetail(name);

      model.addAttribute("firstName", emp.getFirstName());
      model.addAttribute("lastName", emp.getLastName());
      model.addAttribute("email", emp.getEmail());
      model.addAttribute("phone", emp.getPhone());
      model.addAttribute("hireDate", emp.getHireDate());
      model.addAttribute("jobTitle", emp.getJobTitle());

      System.out.println(model.getAttribute("firstName"));
      
      return "hello";
   }

}