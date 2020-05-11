package com.programozzteis.cardealer.cardealer.customer;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
	public String showCustomer(
			@PathVariable(name = "custId") int custId,
			Map<String, Object> model)
	{
		/** Customer with custId */
		Customer customer = this.custRepo.findById(custId);
		model.put("customer", customer);
		
		/** All Cars */
		model.put("cars", this.carRepo.findAll());
		
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
		
		
		if( customerMoney > carPrice )
		{
			/** SUCCESS to BUY */
			/** STEP 1 */
			customer.setCurrentMoney(customerMoney - carPrice);
			
			/** STEP 2 */
			this.carRepo.delete(car);
			
			/** STEP 3 */
			model.put("goodNews", "Congratulation for your new Car: " + car.getType());
		}
		else
		{
			/** FAILED to BUY */
			model.put("badNews", "Failed to buy the Car: " + car.getType());
		}
		
		model.put("customer", customer);
		model.put("cars", this.carRepo.findAll());
		
		
		return "customers/customerDetails";
	}
	
	
	@GetMapping("/customer/{custId}/edit")
	public String startEditCustomer(
			@PathVariable(name = "custId") int custId,
			Map<String, Object> model)
	{
		/** Customer with custId */
		Customer customer = this.custRepo.findById(custId);
		model.put("customer", customer);
		
		
		return "customers/updateCustomerForm";
	}
	
	
	@PostMapping("/customer/{custId}/edit")
	public String finishEditCustomer(
			Customer newCustomer,
			@PathVariable(name = "custId") int custId,
			Map<String, Object> model)
	{
		newCustomer.setId(custId);
		this.custRepo.save(newCustomer);
		
		return "redirect:/customer/{custId}";
	}
	
	
	
	
	
}