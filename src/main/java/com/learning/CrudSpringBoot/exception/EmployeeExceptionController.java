package com.learning.CrudSpringBoot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionController {

	@ExceptionHandler(value = EmployeeNotfoundException.class)
	public ResponseEntity<Object> exception(EmployeeNotfoundException employeeNotfoundException) {

		return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
	}

}
