package com.lucasraimundo.coursemc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.lucasraimundo.coursemc.domain.Client;
import com.lucasraimundo.coursemc.services.validation.ClientUpdate;

@ClientUpdate
public class ClientDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotEmpty(message="Preenchimento obrigatorio")
	@Length(min=5, message="O tamanho deve ser entre 5 e 80 caracteres")
	private String name;
	
	@NotEmpty(message="Preenchimento obrigatorio")
	@Email(message="E-mail inválido")
	private String email;
	
	public ClientDTO() {
		
	}
	
	public ClientDTO(Client obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
