package com.programozzteis.cardealer.cardealer.car;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface CarRepository extends Repository<Car, Integer> {

	/** Read all Cars from DB */
	List<Car> findAll();

	Car findById(Integer carId);

	void delete(Car car);
	
	void save(Car car);

	void deleteById(Integer carId);
}
