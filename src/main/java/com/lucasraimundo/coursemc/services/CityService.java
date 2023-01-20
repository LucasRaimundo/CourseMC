package com.lucasraimundo.coursemc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasraimundo.coursemc.domain.City;
import com.lucasraimundo.coursemc.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository repo;

	public List<City> findByState(Integer stateId) {
		return repo.findCities(stateId);
	}
}
