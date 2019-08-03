package com.learning.CrudSpringBoot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotfoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EmployeeNotfoundException(String message) {
		super(message);
	}

}
