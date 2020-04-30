package com.programozzteis.cardealer.cardealer.customer;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.programozzteis.cardealer.cardealer.car.Car;
import com.programozzteis.cardealer.cardealer.car.CarRepository;

@Controller
public class CustomerController {

	private CarRepository carRepo;
	private CustomerRepository custRepo;

	public CustomerController(CarRepository carRepo, CustomerRepository custRepo) {
		super();
		this.carRepo = carRepo;
		this.custRepo = custRepo;
	}



	@GetMapping("/customer/{custId}")
	public String listAllCars(
			@PathVariable(name = "custId") int custId,
			Map<String, Object> model)
	{
		Customer customer = this.custRepo.findById(custId);
		model.put("customer", customer);
		
		List<Car> cars = this.carRepo.findAll();
		model.put("cars", cars);
		
		return "customers/customerDetails";
	}
	
	
	
	
	@GetMapping("/customer/{custId}/buycar/{carId}")
	public String buyCarById(
			@PathVariable(name = "custId") int custId,
			@PathVariable(name = "carId") int carId,
			Map<String, Object> model)
	{
		Customer customer = this.custRepo.findById(custId);
		Car car = this.carRepo.findById(carId);
		
		int customerMoney = customer.getCurrentMoney();
		int carPrice = car.getPrice();
		
		if(customerMoney >= carPrice)
		{
			/** BUY THE CAR */
			
			int customerNewMoney = customerMoney - carPrice;
			customer.setCurrentMoney(customerNewMoney);
			
			this.carRepo.delete(car);
			
			model.put("goodNews", "Congratulation for your new " + car.getType() + " Car!");
		}
		else
		{
			/** FAILED */
			
			model.put("badNews", "Failed to buy this Car: " + car.getType());
		}
		this.custRepo.save(customer);
		model.put("customer", customer);
		
		
		List<Car> cars = this.carRepo.findAll();
		model.put("cars", cars);
		
		return "customers/customerDetails";
	}

}