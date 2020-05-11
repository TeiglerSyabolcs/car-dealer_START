package com.programozzteis.cardealer.cardealer.salesman;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.programozzteis.cardealer.cardealer.car.Car;
import com.programozzteis.cardealer.cardealer.model.NamedEntity;

@Entity
@Table(name = "salesmans")
public class Salesman extends NamedEntity {

	@OneToMany(mappedBy = "salesman")
	private List<Car> cars;

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	
	public void addCar(Car car) {
		this.cars.add(car);
		car.setSalesman(this);
	}
	
}