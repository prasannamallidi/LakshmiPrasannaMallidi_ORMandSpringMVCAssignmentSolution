package com.crm.service;

import java.util.List;
import com.crm.model.Customer;


public interface CustomerService {
	
	public List<Customer> findAll();
	public Customer findById(int theId);
	public void save(Customer customer);
	public void deleteById(int theId);
}
