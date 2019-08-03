package com.learning.CrudSpringBoot.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class EmployeeExceptionController extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = EmployeeNotfoundException.class)
	public ResponseEntity<Object> exception(EmployeeNotfoundException employeeNotfoundException) {

		return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
	}

	// both are different ways
	// customized message exception format to return
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> exception(Exception exception, WebRequest webRequest) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Field",
				ex.getBindingResult()
						.toString());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

}
