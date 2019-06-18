package com.stl.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stl.crm.domain.Customer;
import com.stl.crm.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

    /**
     * 
     * this method maps the following URL & http method
     * URL: http://hostname:port/crm-base/customers
     * HTTP method: GET
     * 
     */	
	@RequestMapping(value="/customers", method = RequestMethod.GET)
	public ResponseEntity<?> getCustomers() {
		List<Customer> customerList = customerService.getCustomers();
		return new ResponseEntity<>(customerList, HttpStatus.OK);
	}

    /**
     * 
     * this method maps the following URL & http method
     * URL: http://hostname:port/appName/customers/{customerId}
     * HTTP method: GET
     * 
     */		
	@RequestMapping(value="/customers/{customerId}", method = RequestMethod.GET)
	public ResponseEntity<?> getCustomer(@PathVariable long customerId) {
		Customer customer = customerService.getCustomer(customerId);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}	

    /**
     * 
     * this method maps the following URL & http method
     * URL: http://hostname:port/appName/customers
     * HTTP method: POST
     * 
     */		
	@RequestMapping(value="/customers", method = RequestMethod.POST)
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
		Customer newCustomer = customerService.addCustomer(customer);
		return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
	}
	
    /**
     * 
     * this method maps the following URL & http method
     * URL: http://hostname:port/appName/customers/customerId
     * HTTP method: PUT
     * 
     */	
    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCustomer(@PathVariable long customerId, 
    										@RequestBody Customer customer) {
    	Customer updatedCustomer = customerService.updateCustomer(customerId, customer);
    	return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }
	
    /**
     * 
     * this method maps the following URL & http method
     * URL: http://hostname:port/appName/customers/customerId
     * HTTP method: DELETE
     * 
     */
    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCustomer(@PathVariable long customerId) {
    	Customer customer = customerService.getCustomer(customerId);
    	customerService.deleteCustomer(customer);
    	return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/customers/setup", method = RequestMethod.GET)
    public ResponseEntity<Void> fill() {
    	customerService.setupCustomers();
    	return new ResponseEntity<Void>(HttpStatus.OK);
    }

    /**
     * 
     * this method maps the following URL & http method
     * URL: http://hostname:port/appName
     * HTTP method: GET
     * 
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> home() {
    	return new ResponseEntity<>("CRM REST API - Base 1", HttpStatus.OK);
    }
  
}
