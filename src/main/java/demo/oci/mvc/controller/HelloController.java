package demo.oci.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller 
public class HelloController{

   @RequestMapping("/")
   public String index() {
      return "index";
   }

   @PostMapping("/hello")
   public String sayHello(@RequestParam("name") String name, Model model) throws Exception {

      model.addAttribute("firstName", "test_first_name");
      model.addAttribute("lastName", "test_last_name");
      model.addAttribute("email", "email@oracle.com");
      model.addAttribute("phone", "82-123-456-7890");
      model.addAttribute("hireDate", "2019-11-05");
      model.addAttribute("jobTitle", "Sales Consultant");

      System.out.println(model.getAttribute("firstName"));
      
      return "hello";
   }

}