package com.programozzteis.cardealer.cardealer.salesman;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface SalesmanRepository extends Repository<Salesman, Integer> {

	List<Salesman> findAll();

	Salesman findById(Integer smanId);

}
