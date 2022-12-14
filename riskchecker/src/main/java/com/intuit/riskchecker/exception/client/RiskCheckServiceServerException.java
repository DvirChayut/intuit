package com.intuit.riskchecker.exception.client;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RiskCheckServiceServerException extends Throwable {
	
	private String message;
	private Integer statusCode;
}
