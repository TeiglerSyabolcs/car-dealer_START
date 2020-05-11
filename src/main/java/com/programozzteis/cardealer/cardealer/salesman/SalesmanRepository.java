package com.programozzteis.cardealer.cardealer.salesman;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SalesmanRepository extends Repository<Salesman, Integer> {

	List<Salesman> findAll();

	Salesman findById(Integer smanId);

	
	@Query("SELECT salesman FROM Salesman salesman WHERE salesman.name LIKE ('%' || :name || '%')")
	List<Salesman> findByName(@Param("name") String name);

	void deleteById(Integer salesmanId);
}