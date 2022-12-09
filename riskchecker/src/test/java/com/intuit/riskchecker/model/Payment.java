package com.intuit.riskchecker.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Payment {
	private Integer id;
	private float amount;
	private String currency;
	private String payeeId;
	private String userId;
	private String paymentMethod;
	
}
