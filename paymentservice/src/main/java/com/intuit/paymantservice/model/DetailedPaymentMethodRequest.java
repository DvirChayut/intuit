package com.intuit.paymantservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailedPaymentMethodRequest {
	private String creditCompany;
	private String creditNumber;
}
