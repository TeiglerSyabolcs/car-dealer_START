package com.programozzteis.cardealer.cardealer.car;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface CarRepository extends Repository<Car, Integer> {

	/** Read all Cars from DB */
	List<Car>findAll();
	
	/** Read one Car from DB by ID */
	Car findById(Integer carId);

	/** Delete the Car from DB */
	void delete(Car car);


	
	
}
