package com.programozzteis.cardealer.cardealer.salesman;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.programozzteis.cardealer.cardealer.car.Car;
import com.programozzteis.cardealer.cardealer.car.CarRepository;
import com.programozzteis.cardealer.cardealer.car.CarType;

@Controller
public class SalesmanController {

	private SalesmanRepository smanRepo;
	private CarRepository carRepo;

	public SalesmanController(SalesmanRepository smanRepo, CarRepository carRepo) {
		super();
		this.smanRepo = smanRepo;
		this.carRepo = carRepo;
	}



	@GetMapping("/salesman/{smanId}")
	public String showSalesman(
			@PathVariable(name = "smanId") int smanId,
			Map<String, Object> model)
	{
		Salesman sman = this.smanRepo.findById(smanId);
		model.put("salesman", sman);
		
		List<Car> smanCars = sman.getCars();
		model.put("cars", smanCars);
		
		
		return "salesman/salesmanDetails";
	}
	
	
	@GetMapping("/salesman/{smanId}/new")
	public String startCreateNewAd(
			@PathVariable(name = "smanId") int smanId,
			Map<String, Object> model)
	{
		Car newCar = new Car();
		model.put("car", newCar);
		
		model.put("car_types", Car.getCarTypes());
		
		
		return "salesman/createNewAd";
	}
	
	
	@PostMapping("/salesman/{smanId}/new")
	public String finishCreateNewAd(
			Car newCar,
			@PathVariable(name = "smanId") int smanId,
			Map<String, Object> model)
	{
		Salesman sman = this.smanRepo.findById(smanId);
		sman.addCar(newCar);
		
		this.carRepo.save(newCar);
		
		
		return "redirect:/salesman/{smanId}";
	}

	
	@GetMapping("/salesman/find")
	public String startFindSalesman(Map<String, Object> model)
	{
		Salesman sman = new Salesman();
		model.put("salesman", sman);
		
		return "salesman/findSalesman";
	}
	
	
	@GetMapping("/salesman")
	public String finishFindSalesman(
			Salesman salesman,
			Map<String, Object> model)
	{
		
		String searchedName = salesman.getName();
		
		String destinationURL = "";
		String searchResult = "";
		
		
		List<Salesman> results = this.smanRepo.findByName(searchedName);
		
		/** EMPTY */
		if(results.isEmpty())
		{
			destinationURL = "salesman/findSalesman";
			searchResult = "Noone has found with name: " + searchedName;
		}
		/** 1 */
		else if(results.size() == 1)
		{
			destinationURL = "redirect:/salesman/" + ((results.get(0)).getId());
		}
		/** >1 */
		else
		{
			destinationURL = "salesman/findSalesman";
			searchResult = "More Salesman has found with name: " + searchedName;
		}
		
		model.put("searchResult", searchResult);
		
		
		return destinationURL;
	}
	
}
