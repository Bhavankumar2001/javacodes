package com.example.demo.securitysample;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Bhavan Kumar V
 *
 */
@Controller
@RequestMapping(value = "/capex/sterling")
public class MainController {


	
     //description screen return
	@GetMapping("/description/create")
	public String showAddDescriptionForm(Model model, Principal principal) {
		 model.addAttribute("username", principal.getName());
		return "/description_screens/description_create.html";
	}
	//save method for description screen


	//employee screen return
	@GetMapping("/employee/create")
	public String showCreateForm(Model model,Principal principal) {
		 model.addAttribute("username", principal.getName());
		return "/employee/Employee.html"; // Thymeleaf page name
	}
	
	


}

