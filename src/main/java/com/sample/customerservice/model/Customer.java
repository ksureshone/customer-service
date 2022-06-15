package com.sample.customerservice.model;


//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
import javax.validation.constraints.*;

import com.sample.customerservice.validation.IAgeAnnotation;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

//@Entity
@Data
//@Table(name="customer")
@AllArgsConstructor
public class Customer {
	
	//@Id
	private Long id;

    @NotBlank(message = "First name is be empty")
    @NotNull(message = "First name is null")
    private String firstName;
    
    @NotBlank(message = "Last name shouldn't be empty")
    @NotNull(message = "Last name shouldn't be null")
    private String lastName;

    @IAgeAnnotation(message = "Age should be greater than 18 yrs")
    @DateTimeFormat( style= "dd/MM/yyyy")
    private Date dob;

    @Email
    private String email;

    private String phoneNum;
    
    @Pattern( regexp = "^(?!000)\\d{3}-(?!00)\\d{2}-(?!0{4})\\d{4}$")
    private String ssn;

}
