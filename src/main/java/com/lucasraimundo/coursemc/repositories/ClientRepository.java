package com.lucasraimundo.coursemc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.lucasraimundo.coursemc.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{

	@Transactional(readOnly=true)
	Client findByEmail(String email);
}
