package com.intuit.riskchecker.exception.client;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RiskCheckServiceClientException extends RuntimeException{

	private String message;
	private Integer statusCode;
	
}
