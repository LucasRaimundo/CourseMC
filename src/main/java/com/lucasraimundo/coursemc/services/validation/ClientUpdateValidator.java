package com.lucasraimundo.coursemc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.lucasraimundo.coursemc.domain.Client;
import com.lucasraimundo.coursemc.dto.ClientDTO;
import com.lucasraimundo.coursemc.repositories.ClientRepository;
import com.lucasraimundo.coursemc.resources.exceptions.FieldMessage;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClientRepository repo;
	
	@Override
	public void initialize(ClientUpdate ann) {
	}

	@Override
	public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId =Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		Client aux = repo.findByEmail(objDto.getEmail());
		
		if (aux != null && !aux.getId().equals(uriId)) {
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