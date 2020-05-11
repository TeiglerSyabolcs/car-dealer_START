package com.programozzteis.cardealer.cardealer.customer;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface CustomerRepository extends Repository<Customer, Integer> {

	List<Customer> findAll();
	
	Customer findById(Integer id);
	
	void save(Customer cust);

	void deleteById(Integer customerId);
	
}