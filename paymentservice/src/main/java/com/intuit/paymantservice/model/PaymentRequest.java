package com.intuit.paymantservice.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.intuit.paymantservice.validator.Currency;

import lombok.Data;

@Data
public class PaymentRequest {
	
	@Min(value = 1, message = "Invalid Amount: Equals to zero or Less than zero")
	private float amount;
	@NotBlank(message = "Invalid Currency: Empty Currency")
	@NotNull(message = "Invalid Currency: Currency is NULL")
	@Currency()
	private String currency;
	@NotBlank(message = "Invalid PayeeId: Empty PayeeId")
	@NotNull(message = "Invalid PayeeId: PayeeId is NULL")
	private String payeeId;
	@NotBlank(message = "Invalid UserId: Empty UserId")
	@NotNull(message = "Invalid UserId: UserId is NULL")
	private String userId;
	private DetailedPaymentMethodRequest paymentMethod;
}
