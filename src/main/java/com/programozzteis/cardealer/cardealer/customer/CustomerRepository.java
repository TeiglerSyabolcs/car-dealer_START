package com.programozzteis.cardealer.cardealer.customer;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface CustomerRepository extends Repository<Customer, Integer> {
	
	List<Customer> findAll();

	Customer findById(int id);

	void save(Customer customer);

}