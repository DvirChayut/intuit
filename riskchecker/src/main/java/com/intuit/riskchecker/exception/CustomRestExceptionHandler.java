package com.intuit.riskchecker.exception;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.intuit.riskchecker.exception.client.RiskCheckServiceClientException;
import com.intuit.riskchecker.exception.client.RiskCheckServiceServerException;


@ControllerAdvice
public class CustomRestExceptionHandler {

	
	@ExceptionHandler({ RiskCheckServiceClientException.class })
	public ResponseEntity<Object> handleRiskCheckServiceClientException(
			RiskCheckServiceClientException ex) {
	   
	    ErrorData errorData = new ErrorData(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), List.of());
	    
	    return new ResponseEntity<Object>(
	    		errorData, new HttpHeaders(), errorData.getStatus());
	}
	
	
	@ExceptionHandler({ RiskCheckServiceServerException.class })
	public ResponseEntity<Object> handleRiskCheckServiceServerException(
			RiskCheckServiceServerException ex) {
	   
	    ErrorData errorData = new ErrorData(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), List.of());
	    
	    return new ResponseEntity<Object>(
	    		errorData, new HttpHeaders(), errorData.getStatus());
	}
	
	
	
}