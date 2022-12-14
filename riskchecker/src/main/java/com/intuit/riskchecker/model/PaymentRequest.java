package com.intuit.riskchecker.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentRequest {
	
	private float amount;
	private String currency;
	private String payeeId;
	private String userId;
	private DetailedPaymentMethodRequest paymentMethod;
}
