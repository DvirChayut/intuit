package com.intuit.paymantservice.service;

import com.intuit.paymantservice.model.PaymentRequest;

public interface PaymentService {
	
	public void sendPaymentToConfirmation(PaymentRequest payment);
}
