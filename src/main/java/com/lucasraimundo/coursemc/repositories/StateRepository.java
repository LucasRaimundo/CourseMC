package com.lucasraimundo.coursemc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasraimundo.coursemc.domain.State;

public interface StateRepository extends JpaRepository<State, Integer> {

}
