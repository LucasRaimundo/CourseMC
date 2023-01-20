package com.lucasraimundo.coursemc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.lucasraimundo.coursemc.domain.State;

public interface StateRepository extends JpaRepository<State, Integer> {
	
	@Transactional(readOnly=true)
	public List<State> findAllByOrderByName();

}
