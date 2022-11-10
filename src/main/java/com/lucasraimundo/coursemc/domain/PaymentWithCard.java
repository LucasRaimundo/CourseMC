package com.lucasraimundo.coursemc.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.lucasraimundo.coursemc.domain.enums.StatusPayment;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PaymentWithCard extends Payment {
	private static final long serialVersionUID = 1L;

	private Integer numOfInstallments;
	
	public PaymentWithCard() {
		
	}

	public PaymentWithCard(Integer id, StatusPayment statusPayment, Orders order, Integer numOfInstallments) {
		super(id, statusPayment, order);
		this.numOfInstallments = numOfInstallments;
	}

	public Integer getNumOfInstallments() {
		return numOfInstallments;
	}

	public void setNumOfInstallments(Integer numOfInstallments) {
		this.numOfInstallments = numOfInstallments;
	}
	
	
}
