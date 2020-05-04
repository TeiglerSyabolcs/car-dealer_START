package com.programozzteis.cardealer.cardealer.customer;

import java.util.List;
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
		Customer cust = this.custRepo.findById(custId);
		model.put("customer", cust);
		
		
		List<Car> cars = this.carRepo.findAll();
		model.put("cars", cars);
		
		return "customers/customerDetails";
	}

	
	@GetMapping("/customer/{custId}/buycar/{carId}")
	public String customerBuyCarById(
			@PathVariable(name = "custId") int custId,
			@PathVariable(name = "carId") int carId,
			Map<String, Object> model)
	{
		Customer cust = this.custRepo.findById(custId);
		Car car = this.carRepo.findById(carId);
		
		int custMoney = cust.getCurrentMoney();
		int carPrice = car.getPrice();
		
		if( custMoney >= carPrice )
		{
			/** SUCCESS TO BUY */
			
			/** STEP 1 */
			cust.setCurrentMoney(custMoney - carPrice);
			
			/** STEP 2 */
			this.carRepo.delete(car);
			
			/** STEP 3 */
			model.put("goodNews", "Congratulation to Buy this Car: " + car.getType());
			
		}
		else
		{
			/** FAILED */
			
			model.put("badNews", "Failed to Buy this Car: " + car.getType());
		}
		model.put("customer", cust);
		
		
		List<Car> cars = this.carRepo.findAll();
		model.put("cars", cars);
		
		
		return "customers/customerDetails";
	}
	
	
	@GetMapping("/customer/{custId}/edit")
	public String startEditFunc(
			@PathVariable(name = "custId") int custId,
			Map<String, Object> model)
	{
		Customer cust = this.custRepo.findById(custId);
		model.put("customer", cust);
		
		return "customers/updateCustomerForm";
	}
	
	
	@PostMapping("/customer/{custId}/edit")
	public String finishEditFunc(
			Customer cust,
			@PathVariable(name = "custId") int custId,
			Map<String, Object> model)
	{
		cust.setId(custId);
		this.custRepo.save(cust);
		
		return "redirect:/customer/{custId}";
	}
}