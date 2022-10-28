package com.lucasraimundo.coursemc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasraimundo.coursemc.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
