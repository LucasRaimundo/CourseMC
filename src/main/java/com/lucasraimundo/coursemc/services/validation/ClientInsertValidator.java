package com.lucasraimundo.coursemc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.lucasraimundo.coursemc.domain.Client;
import com.lucasraimundo.coursemc.domain.enums.TypeClient;
import com.lucasraimundo.coursemc.dto.ClientNewDTO;
import com.lucasraimundo.coursemc.repositories.ClientRepository;
import com.lucasraimundo.coursemc.resources.exceptions.FieldMessage;
import com.lucasraimundo.coursemc.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {
	
	@Autowired
	private ClientRepository repo;
	
	@Override
	public void initialize(ClientInsert ann) {
	}

	@Override
	public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getType().equals(TypeClient.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpf())) {
			list.add(new FieldMessage("Cpf", "CPF Inválido"));
		}
		
		if (objDto.getType().equals(TypeClient.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpf())) {
			list.add(new FieldMessage("Cpf", "CNPJ Inválido"));
		}
		
		Client aux = repo.findByEmail(objDto.getEmail());
		
		if (aux != null) {
			list.add(new FieldMessage("email", "E-mail já cadastrado"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}