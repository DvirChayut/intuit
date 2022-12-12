package com.intuit.paymantservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetailedPaymentMethodRequest {
	private String creditCompany;
	private String creditNumber;
}
