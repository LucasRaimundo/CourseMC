package com.lucasraimundo.coursemc.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ItemOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemOrderPK id = new ItemOrderPK();
	
	private Double discount;
	private Integer quantity;
	private Double price;
	
	public ItemOrder() {
		
	}

	public ItemOrder(Orders orders, Product product, Double discount, Integer quantity, Double price) {
		super();
		id.setOrders(orders);
		id.setProduct(product);
		this.discount = discount;
		this.quantity = quantity;
		this.price = price;
	}

	public ItemOrderPK getId() {
		return id;
	}

	public void setId(ItemOrderPK id) {
		this.id = id;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Orders getOrders() {
		return id.getOrders();
	}
	
	public Product getProduct() {
		return id.getProduct();
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
		ItemOrder other = (ItemOrder) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
