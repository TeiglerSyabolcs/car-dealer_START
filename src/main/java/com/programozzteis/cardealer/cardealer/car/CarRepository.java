package com.programozzteis.cardealer.cardealer.car;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface CarRepository extends Repository<Car, Integer> {

	List<Car> findAll();
}
