package com.lucasraimundo.coursemc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasraimundo.coursemc.domain.City;

public interface CityRepository extends JpaRepository<City, Integer> {

}
