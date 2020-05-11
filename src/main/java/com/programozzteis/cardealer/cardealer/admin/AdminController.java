package com.programozzteis.cardealer.cardealer.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.programozzteis.cardealer.cardealer.car.Car;
import com.programozzteis.cardealer.cardealer.car.CarRepository;
import com.programozzteis.cardealer.cardealer.customer.Customer;
import com.programozzteis.cardealer.cardealer.customer.CustomerRepository;
import com.programozzteis.cardealer.cardealer.salesman.Salesman;
import com.programozzteis.cardealer.cardealer.salesman.SalesmanRepository;

@Controller
public class AdminController {

	private final CustomerRepository customerRepo;
	private final SalesmanRepository salesmanRepo;
	private final CarRepository carRepo;

	public AdminController(CustomerRepository customerRepo, SalesmanRepository salesmanRepo, CarRepository carRepo) {
		this.customerRepo = customerRepo;
		this.salesmanRepo = salesmanRepo;
		this.carRepo = carRepo;
	}

	
	@GetMapping("/admin/{adminId}")
	public String showAdminPage(
			@PathVariable(name = "adminId") int adminId, 
			Map<String, Object> model)
	{
		/** show all default users */
		model.put("adminId", adminId);
		
		/** SALESMANS */
		List<Salesman> salesmans = this.salesmanRepo.findAll();
		model.put("salesmans", salesmans);
		
		/** CUSTOMERS */
		List<Customer> customers = this.customerRepo.findAll();
		model.put("customers", customers);
		
		/** ADVERTISEMENTS */
		List<Car> cars = this.carRepo.findAll();
		model.put("cars", cars);
		
		
		return "admins/adminDetails";
	}
	
	
	@GetMapping("/admin/{adminId}/deletecust/{custId}")
	public String deleteCustomer(
			@PathVariable(name = "custId") int customerId,
			Map<String, Object> model)
	{
		/** Delete */
		this.customerRepo.deleteById(customerId);

		/** Go Back to Admin Panel */
		return "redirect:/admin/{adminId}";
	}
	
	
	@GetMapping("/admin/{adminId}/deletecar/{carId}")
	public String deleteCar(
			@PathVariable(name = "carId") int carId,
			Map<String, Object> model)
	{
		/** Delete */
		this.carRepo.deleteById(carId);

		/** Go Back to Admin Panel */
		return "redirect:/admin/{adminId}";
	}
	
	
	@GetMapping("/admin/{adminId}/deletesman/{smanId}")
	public String deleteSalesman(
			@PathVariable(name = "smanId") int salesmanId,
			Map<String, Object> model)
	{
		/** Delete Cars of Salesman */
		Salesman sman = this.salesmanRepo.findById(salesmanId);
		List<Car> smanCars = sman.getCars();
		
		for (Car car : smanCars) {
			this.carRepo.delete(car);
		}
		
		/** Delete Salesman */
		this.salesmanRepo.deleteById(salesmanId);
		
		
		/** Go Back to Admin Panel */
		return "redirect:/admin/{adminId}";
	}
	
}

