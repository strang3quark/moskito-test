package com.stl.crm.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import com.stl.crm.domain.Customer;

@Service
public class CustomerService {
	
	public List<Customer> customerList = null;
	
	/**
	 * get all customers
	 * @return
	 */
	public List<Customer> getCustomers() {
		return customerList;
	}

	/**
	 * get a customer based on the customerId
	 * @param customerId
	 * @return
	 */
	public Customer getCustomer(long customerId) {
		for (Customer customer : customerList) {
			if (customer.getId() == customerId) {
				return customer;
			}
		}
		return null;
	}
	
	/**
	 * update existing customer
	 * @param customerId
	 * @param customer
	 * @return
	 */
	public Customer updateCustomer(long customerId, Customer customer) {
		for (Customer existingCustomer : customerList) {
			if (existingCustomer.getId() == customer.getId()) {
	    		existingCustomer.setName(customer.getName());
	    		existingCustomer.setAddress(customer.getAddress());
	    		existingCustomer.setPhone(customer.getPhone());
	    		existingCustomer.setContact(customer.getContact());
				return existingCustomer;
			}
		}
		return null;
	}

	/**
	 * add a customer 
	 * @param customer
	 * @return
	 */
	public Customer addCustomer(Customer customer) {
		customer.setId(customerList.size() + 1);
		customerList.add(customer);
		return customer;
	}	
 
	/**
	 * delete a customer
	 * @param customer
	 */
	public void deleteCustomer(Customer customer) {
		customerList.remove(customer);
	}

	/**
	 * set up a few customers on the startup
	 * 
	 */
	@PostConstruct
	private void setupCustomers() {
		customerList = new ArrayList<Customer>();
		Customer customer = new Customer();
		customer.setId(1);
		customer.setName("Google Inc");
		customer.setAddress("11600 Amphitheatre Parkway, Mountain View, CA");
		customer.setPhone("111-222-3333");
		customer.setContact("VP");
		customerList.add(customer);
		
		customer = new Customer();
		customer.setId(2);
		customer.setName("Amazon Inc");
		customer.setAddress("410 Terry Ave. North, Seattle, WA");
		customer.setPhone("777-222-3333");
		customer.setContact("Sales Manager");
		customerList.add(customer);		
	}
}
