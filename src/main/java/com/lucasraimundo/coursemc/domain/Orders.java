package com.lucasraimundo.coursemc.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Orders implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date instant; 
	
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy = "order")
	private Payment payment;
	

	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name="endereco_de_entrega_id")
	private Adress finalAdress;
	
	@OneToMany(mappedBy = "id.orders")
	private Set<ItemOrder> items= new HashSet<>();
	
	public Orders() {
		
	}

	public Orders(Integer id, Date instant,  Client client, Adress finalAdress) {
		super();
		this.id = id;
		this.instant = instant;
		this.client = client;
		this.finalAdress = finalAdress;
	}
	
	public double getTotalValue() {
		double som =0;
		for (ItemOrder ip : items) {
			som = som + ip.getSubTotal();
		}
		
		return som;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstant() {
		return instant;
	}

	public void setInstant(Date instant) {
		this.instant = instant;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Adress getFinalAdress() {
		return finalAdress;
	}

	public void setFinalAdress(Adress finalAdress) {
		this.finalAdress = finalAdress;
	}
	
	public Set<ItemOrder> getItems() {
		return items;
	}

	public void setItems(Set<ItemOrder> items) {
		this.items = items;
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
		Orders other = (Orders) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		builder.append("Pedido numero: ");
		builder.append(getId());
		builder.append(", Instante: ");
		builder.append(sdf.format(getInstant()));
		builder.append(", Cliente: ");
		builder.append(getClient().getName());
		builder.append(", Situação do pagamento: ");
		builder.append(getPayment().getStatusPayment().getDesc());
		builder.append("\nDetalhes:\n");
		for(ItemOrder ip : getItems()) {
			builder.append(ip.toString());
		}
		
		builder.append("Valor total:");
		builder.append(nf.format(getTotalValue()));
		
		return builder.toString();
	}

	
	
	
}
