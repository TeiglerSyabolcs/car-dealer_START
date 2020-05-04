package com.programozzteis.cardealer.cardealer.salesman;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.programozzteis.cardealer.cardealer.car.Car;
import com.programozzteis.cardealer.cardealer.car.CarRepository;

@Controller
public class SalesmanController {

	private SalesmanRepository salesmanRepo;
	private CarRepository carRepo;
	
	public SalesmanController(SalesmanRepository salesmanRepo, CarRepository carRepo) {
		super();
		this.salesmanRepo = salesmanRepo;
		this.carRepo = carRepo;
	}



	@GetMapping("/salesman/{smanId}")
	public String showSalesman(
			@PathVariable(name = "smanId") int smanId,
			Map<String, Object> model)
	{
		Salesman sman = this.salesmanRepo.findById(smanId);
		model.put("salesman", sman);

		List<Car> cars = sman.getCars();
		model.put("cars", cars);
		
		
		return "salesman/salesmanDetails";
	}
	
	
	
	@GetMapping("/salesman/*/new")
	public String startAddNewCar(Map<String, Object> model)
	{
		Car newCar = new Car();
		model.put("car", newCar);
		
		model.put("car_types", Car.getCarTypes());
		
		
		return "salesman/createNewAd";
	}
	
	
	@PostMapping("/salesman/{smanId}/new")
	public String finishAddNewCar(
			Car car,
			@PathVariable(name = "smanId") int smanId,
			Map<String, Object> model)
	{
		/** Connect CAR and SALESMAN */
		Salesman sman = this.salesmanRepo.findById(smanId);
		sman.addCar(car);
		
		/** Save CAR to DB */
		car.setSalesman(sman);
		this.carRepo.save(car);
		
		model.put("salesman", sman);
		model.put("cars", sman.getCars());
		
		
		return "redirect:/salesman/{smanId}";
	}
	
}
