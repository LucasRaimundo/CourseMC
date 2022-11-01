package com.lucasraimundo.coursemc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasraimundo.coursemc.domain.Orders;
import com.lucasraimundo.coursemc.repositories.OrdersRepository;
import com.lucasraimundo.coursemc.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrdersRepository repo;

	public Orders find(Integer id) {
		Optional<Orders> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Orders.class.getName()));
	}
}
