package com.lucasraimundo.coursemc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.lucasraimundo.coursemc.domain.enums.StatusPayment;

@Entity
public class PaymentWithTicket extends Payment {
	private static final long serialVersionUID = 1L;

	private Date dueDate;
	private Date datePayment;
	
	public PaymentWithTicket() {
		
	}

	public PaymentWithTicket(Integer id, StatusPayment statusPayment, Orders order, Date dueDate, Date datePayment) {
		super(id, statusPayment, order);
		this.datePayment = datePayment;
		this.dueDate = dueDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getDatePayment() {
		return datePayment;
	}

	public void setDatePayment(Date datePayment) {
		this.datePayment = datePayment;
	}
	
	
}
