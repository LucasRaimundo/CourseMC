package com.lucasraimundo.coursemc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.lucasraimundo.coursemc.domain.Client;
import com.lucasraimundo.coursemc.domain.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer>{

	@Transactional(readOnly=true)
	Page<Orders> findByClient(Client client, Pageable pageRequest);
}
