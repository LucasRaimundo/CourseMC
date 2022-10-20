package com.lucasraimundo.coursemc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasraimundo.coursemc.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{

}
