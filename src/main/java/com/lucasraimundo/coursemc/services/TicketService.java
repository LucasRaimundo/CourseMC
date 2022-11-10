package com.lucasraimundo.coursemc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.lucasraimundo.coursemc.domain.PaymentWithTicket;

@Service
public class TicketService {

	public void fillInPaymentWithTicket(PaymentWithTicket pagto, Date momentOfOrder) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(momentOfOrder);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDueDate(cal.getTime());
	}
}
