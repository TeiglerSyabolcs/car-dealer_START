package com.programozzteis.cardealer.cardealer.system;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.programozzteis.cardealer.cardealer.admin.AdminRepository;
import com.programozzteis.cardealer.cardealer.customer.CustomerRepository;
import com.programozzteis.cardealer.cardealer.salesman.SalesmanRepository;


@Controller  
public class WelcomeHandler {

	private CustomerRepository custRepo;
	private SalesmanRepository smanRepo;
	private AdminRepository adminRepo;

	public WelcomeHandler(CustomerRepository custRepo, SalesmanRepository smanRepo, AdminRepository adminRepo) {
		super();
		this.custRepo = custRepo;
		this.smanRepo = smanRepo;
		this.adminRepo = adminRepo;
	}



	@GetMapping("/")
	public String welcome(Map<String, Object> model)
	{
		/** show all default users */

		/** ADMIN */
		model.put("admins", this.adminRepo.findAll());
		
		/** SALESMANS */
		model.put("salesmans", this.smanRepo.findAll());
		
		/** CUSTOMERS */
		model.put("customers", this.custRepo.findAll());
		
		
		return "welcome";
	}

}
