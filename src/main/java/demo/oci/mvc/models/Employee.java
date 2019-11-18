package demo.oci.mvc.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Employee {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String hireDate;
    private String jobTitle;
    // getter, setter and other properties
}