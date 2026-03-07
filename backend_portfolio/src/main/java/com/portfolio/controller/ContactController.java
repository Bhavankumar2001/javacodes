package com.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*")
public class ContactController {

	@Autowired
	private EmailService emailService;

	@PostMapping
	public Map<String, String> submitContact(@RequestBody Map<String, String> contactForm) {

		String name = contactForm.get("name");
		String email = contactForm.get("email");
		String message = contactForm.get("message");

		emailService.sendContactEmail(name, email, message);

		Map<String, String> response = new HashMap<>();
		response.put("status", "success");
		response.put("message", "Message received successfully!");

		return response;
	}
}