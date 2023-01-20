package com.lucasraimundo.coursemc.dto;

import java.io.Serializable;

import com.lucasraimundo.coursemc.domain.State;

public class StateDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;

	public StateDTO() {
	}

	public StateDTO(State obj) {
		id = obj.getId();
		nome = obj.getName();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}