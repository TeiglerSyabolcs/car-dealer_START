package com.programozzteis.cardealer.cardealer.car;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.programozzteis.cardealer.cardealer.model.BaseEntity;
import com.programozzteis.cardealer.cardealer.salesman.Salesman;

@Entity
@Table(name = "advertisements")
public class Car extends BaseEntity {


	
	@Column(name = "car_power")
	private String power;
	
	@Column(name = "car_consumption")
	private String consumption;
	
	@Column(name = "car_price")
	private int price;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "car_prod_date")
	private LocalDate prodYear;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "car_type")
	private CarType type;
	
	@ManyToOne
	@JoinColumn(name = "car_salesman_id")
	private Salesman salesman;


	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getConsumption() {
		return consumption;
	}

	public void setConsumption(String consumption) {
		this.consumption = consumption;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public LocalDate getProdYear() {
		return prodYear;
	}

	public void setProdYear(LocalDate prodYear) {
		this.prodYear = prodYear;
	}

	public CarType getType() {
		return type;
	}

	public void setType(CarType type) {
		this.type = type;
	}

	public Salesman getSalesman() {
		return salesman;
	}

	public void setSalesman(Salesman salesman) {
		this.salesman = salesman;
	}
	public static List<CarType> getCarTypes()
	{
		return Arrays.asList(CarType.values());
	}
	
	
	
}