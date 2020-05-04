package com.programozzteis.cardealer.cardealer.system;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.programozzteis.cardealer.cardealer.customer.CustomerRepository;
import com.programozzteis.cardealer.cardealer.salesman.SalesmanRepository;


@Controller  
public class WelcomeHandler {


	private CustomerRepository customerRepository;
	private SalesmanRepository salesmanRepository;
	
	public WelcomeHandler(CustomerRepository customerRepository, SalesmanRepository salesmanRepository) {
		super();
		this.customerRepository = customerRepository;
		this.salesmanRepository = salesmanRepository;
	}



	@GetMapping("/")
	public String welcome(Map<String, Object> model)
	{
		/** show all default users */

		/** ADMIN */
		
		/** SALESMANS */
		model.put("salesmans", this.salesmanRepository.findAll());
		
		/** CUSTOMERS */
		model.put("customers", this.customerRepository.findAll());
		
		
		return "welcome";
	}

}