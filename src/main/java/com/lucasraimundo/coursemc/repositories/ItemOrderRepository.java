package com.lucasraimundo.coursemc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasraimundo.coursemc.domain.ItemOrder;

public interface ItemOrderRepository extends JpaRepository<ItemOrder, Integer>{

}
