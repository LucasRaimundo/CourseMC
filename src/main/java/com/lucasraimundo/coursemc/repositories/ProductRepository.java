package com.lucasraimundo.coursemc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasraimundo.coursemc.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
