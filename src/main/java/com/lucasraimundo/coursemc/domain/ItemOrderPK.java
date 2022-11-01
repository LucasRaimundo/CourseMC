package com.lucasraimundo.coursemc.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ItemOrderPK implements Serializable{

	private static final long serialVersionUID = 1L;

	
	@ManyToOne
	@JoinColumn(name="order_id")
	private Orders orders;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	public Orders getOrders() {
		return orders;
	}
	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public int hashCode() {
		return Objects.hash(orders, product);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemOrderPK other = (ItemOrderPK) obj;
		return Objects.equals(orders, other.orders) && Objects.equals(product, other.product);
	}
	
	
}
