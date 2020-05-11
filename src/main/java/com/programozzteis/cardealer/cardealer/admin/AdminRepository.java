package com.programozzteis.cardealer.cardealer.admin;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface AdminRepository extends Repository<Admin, Integer> {

	/**
	 * Returns all instances of the type.
	 *
	 * @return all entities
	 */
	List<Admin> findAll();
	
}