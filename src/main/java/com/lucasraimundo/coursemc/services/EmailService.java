package com.lucasraimundo.coursemc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.lucasraimundo.coursemc.domain.Client;
import com.lucasraimundo.coursemc.domain.Orders;

public interface EmailService {

	void sendOrderConfirmationEmail(Orders orders);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Orders obj);
	
	void sendHtmlEmail(MimeMessage msg);
	
	void sendNewPasswordEmail(Client client, String newPass);
}
