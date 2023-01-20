package com.lucasraimundo.coursemc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandartError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();
	

	public ValidationError(Long timetamp, Integer status, String error, String msg, String path) {
		super(timetamp, status, error, msg, path);
		this.errors = errors;
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}
	
	

}
