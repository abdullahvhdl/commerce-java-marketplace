package com.respodo.commerce.web.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.respodo.commerce.common.exception.EmailAlreadyExistsException;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<?> EmailAlreadyExistsHandler(){
		
		return new ResponseEntity<>("e-mail address already in use",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> RuntimeExceptionHandler(Exception e){
		
		return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
	}
}
