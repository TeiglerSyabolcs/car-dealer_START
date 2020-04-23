package com.programozzteis.cardealer.car;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarController {

	private CarRepository carRepo;
	
	

	public CarController(CarRepository carRepo) {
		super();
		this.carRepo = carRepo;
	}



	@GetMapping("/advertisementList.html")
	public String showCars(Map<String, Object> model)
	{
		System.out.println("HEEEEELO");
		model.put("cars", this.carRepo.findAll());
		
	
		return "advertisements/advertisementList";
	}
}