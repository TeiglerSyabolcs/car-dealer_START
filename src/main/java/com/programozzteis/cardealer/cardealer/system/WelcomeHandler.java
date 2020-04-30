package com.programozzteis.cardealer.cardealer.system;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.programozzteis.cardealer.cardealer.customer.CustomerRepository;


@Controller  
public class WelcomeHandler {

	private CustomerRepository custRepo;
	
	public WelcomeHandler(CustomerRepository custRepo) {
		super();
		this.custRepo = custRepo;
	}

	@GetMapping("/")
	public String welcome(Map<String, Object> model)
	{
		/** show all default users */

		/** ADMIN */
		
		/** SALESMANS */
		
		/** CUSTOMERS */
		model.put("customers", this.custRepo.findAll());
		
		
		return "welcome";
	}

}
