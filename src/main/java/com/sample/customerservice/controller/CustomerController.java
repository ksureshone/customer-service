package com.sample.customerservice.controller;

import com.sample.customerservice.exception.CustomerValidationException;
import com.sample.customerservice.model.Customer;
import com.sample.customerservice.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Collection;
import java.util.Set;


@RestController
@RequestMapping("/customer")
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private Validator validator;


    @PostMapping("/create")
    public ResponseEntity<Long> createCustomer(@RequestBody Customer customer) throws CustomerValidationException {
    	try {
            Set<ConstraintViolation<Customer>> violations= validator.validate(customer);
            for (ConstraintViolation<Customer> violation : violations) {
                StringBuilder sb = new StringBuilder();
                sb.append(violation.getMessage()+'\n');
                log.error(violation.getMessage());
                throw new CustomerValidationException(sb.toString());
            }
    		Customer customerEntity = customerService.create(customer);
    		return new ResponseEntity<Long>(customerEntity.getId(), HttpStatus.OK);
    	}catch(Exception e) {
    		log.error("Exception while saving customer ");
    	}
    	return new ResponseEntity<Long>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<Collection<Customer>> getAllCustomer(){
        try {
            return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
        }catch(Exception e){
            log.error("Exception while fetching all customers : {}", e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
