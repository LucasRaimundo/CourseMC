package com.lucasraimundo.coursemc.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucasraimundo.coursemc.domain.enums.StatusPayment;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private Integer statusPayment;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="Pedido_id")
	@MapsId
	private Orders order;
	
	public Payment() {
		
	}

	public Payment(Integer id, StatusPayment statusPayment, Orders order) {
		super();
		this.id = id;
		this.statusPayment = statusPayment.getCod();
		this.order = order;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StatusPayment getStatusPayment() {
		return StatusPayment.toEnum(statusPayment);
	}

	public void setStatusPayment(StatusPayment statusPayment) {
		this.statusPayment = statusPayment.getCod();
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
