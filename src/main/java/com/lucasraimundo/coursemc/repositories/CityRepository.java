package com.lucasraimundo.coursemc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.lucasraimundo.coursemc.domain.City;

public interface CityRepository extends JpaRepository<City, Integer> {

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM City obj WHERE obj.state.id = :stateId ORDER BY obj.name")
	public List<City> findCities(@Param("stateId")Integer state_id);
	
}
