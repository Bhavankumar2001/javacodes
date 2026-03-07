package com.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	public void sendContactEmail(String name, String email, String message) {

		SimpleMailMessage mail = new SimpleMailMessage();

		// Your email (receiver)
		mail.setTo("bhavankumar1862001@gmail.com");

		// User email (sender)
		mail.setReplyTo(email);

		mail.setSubject("New Portfolio Contact Message");

		mail.setText("Name: " + name + "\nEmail: " + email + "\nMessage: " + message);

		mailSender.send(mail);
	}
}