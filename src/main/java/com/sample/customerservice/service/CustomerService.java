package com.sample.customerservice.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.customerservice.model.Customer;
//import com.sample.customerservice.repository.CustomerRepository;

@Service
public class CustomerService {
	
	private Map<Long, Customer> customers = new HashMap<>();
	
	//@Autowired
	//private CustomerRepository customerRepository;
	
	public Customer create(Customer customer) {
		customer.setId(101L);
		customers.put(customer.getId(), customer);
		return customer;
		//return customerRepository.save(customer);
	}

	public Collection<Customer> getAllCustomers(){
		if(customers.isEmpty()){
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date dob = null;
			try {
				dob = formatter.parse("10/10/2001");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return Collections.singletonList(new Customer(101L, "Mark", "Strange", dob.toInstant()
					.atZone(ZoneId.systemDefault())
					.toLocalDate(), "suresh@company.com","1234567890", "111-11-1111"));
		}
		return customers.values();
	}

}
