package com.intuit.riskchecker.model;

import lombok.Data;

@Data
public class PaymentRequest {
	
	private float amount;
	private String currency;
	private String payeeId;
	private String userId;
	private String paymentMethod;
}
