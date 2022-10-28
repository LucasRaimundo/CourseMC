package com.lucasraimundo.coursemc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasraimundo.coursemc.domain.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer>{

}
