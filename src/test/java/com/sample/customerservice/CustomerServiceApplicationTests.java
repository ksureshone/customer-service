package com.sample.customerservice;

import com.sample.customerservice.model.Customer;
import com.sample.customerservice.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.CoreMatchers.is;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import static org.junit.Assert.*;

@SpringBootTest
class CustomerServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private CustomerService customerService;

	// TODO mockito can be used to avoid DB changes.
	@Test
	void testCreateCustomer(){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dob = null;
		try {
			dob = formatter.parse("10/10/2001");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Customer cust = new Customer(0L, "Mark", "Strange", dob.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDate(), "suresh@company.com","1234567890", "111-11-1111");
		customerService.create(cust);
		Collection<Customer> allCustomers = customerService.getAllCustomers();
		assertTrue(allCustomers.size()== 1);


	}

}
