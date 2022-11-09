package com.lucasraimundo.coursemc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.lucasraimundo.coursemc.domain.enums.TypeClient;
import com.lucasraimundo.coursemc.dto.ClientNewDTO;
import com.lucasraimundo.coursemc.resources.exceptions.FieldMessage;
import com.lucasraimundo.coursemc.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {
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

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}