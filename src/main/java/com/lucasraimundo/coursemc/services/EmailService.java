package com.lucasraimundo.coursemc.services;

import org.springframework.mail.SimpleMailMessage;

import com.lucasraimundo.coursemc.domain.Orders;

public interface EmailService {

	void sendOrderConfirmationEmail(Orders orders);
	
	void sendEmail(SimpleMailMessage msg);
}
