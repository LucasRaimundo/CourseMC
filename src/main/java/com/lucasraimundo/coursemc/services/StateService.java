package com.lucasraimundo.coursemc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasraimundo.coursemc.domain.State;
import com.lucasraimundo.coursemc.repositories.StateRepository;

@Service
public class StateService {

	@Autowired
	private StateRepository repo;

	public List<State> findAll() {
		return repo.findAllByOrderByName();
	}
}
