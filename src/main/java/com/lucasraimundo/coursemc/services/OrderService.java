package com.lucasraimundo.coursemc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucasraimundo.coursemc.domain.ItemOrder;
import com.lucasraimundo.coursemc.domain.Orders;
import com.lucasraimundo.coursemc.domain.PaymentWithTicket;
import com.lucasraimundo.coursemc.domain.enums.StatusPayment;
import com.lucasraimundo.coursemc.repositories.ItemOrderRepository;
import com.lucasraimundo.coursemc.repositories.OrdersRepository;
import com.lucasraimundo.coursemc.repositories.PaymentRepository;
import com.lucasraimundo.coursemc.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrdersRepository repo;
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ItemOrderRepository itemOrderRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ClientService clientService;

	public Orders find(Integer id) {
		Optional<Orders> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Orders.class.getName()));
	}
	
	@Transactional
	public Orders insert(Orders obj) {
		obj.setId(null);
		obj.setInstant(new Date());
		obj.setClient(clientService.find(obj.getClient().getId()));
		obj.getPayment().setStatusPayment(StatusPayment.PENDENTE);
		obj.getPayment().setOrder(obj);
		if(obj.getPayment() instanceof PaymentWithTicket) {
			PaymentWithTicket pagto = (PaymentWithTicket) obj.getPayment();
			ticketService.fillInPaymentWithTicket(pagto, obj.getInstant());
		}
		obj = repo.save(obj);
		paymentRepository.save(obj.getPayment());
		for(ItemOrder ip : obj.getItems()) {
			ip.setDiscount(0.0);
			ip.setProduct(productService.find(ip.getProduct().getId()));
			ip.setPrice(ip.getProduct().getPrice());
			ip.setPrice(productService.find(ip.getProduct().getId()).getPrice());
			ip.setOrders(obj);
		}
		itemOrderRepository.saveAll(obj.getItems());
		
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
	}
}
