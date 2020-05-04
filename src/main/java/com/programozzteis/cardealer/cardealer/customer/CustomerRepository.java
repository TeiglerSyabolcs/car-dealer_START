package com.programozzteis.cardealer.cardealer.customer;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface CustomerRepository extends Repository<Customer, Integer> {
	

	/** Read all Customer from DB */
	List<Customer> findAll();
	
	/** Read 1 Customer from DB */
	Customer findById(Integer id);
	
	/** Save the Customer to the DB */
	void save(Customer customer);
}