package com.programozzteis.cardealer.cardealer.system;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WelcomeHandler {



	@GetMapping("/")
	public String welcome(Map<String, Object> model)
	{
		/** show all default users */
		
		/** ADMIN */

		/** SALESMANS */
		
		/** CUSTOMERS */
		
		
		return "welcome";
	}

}
