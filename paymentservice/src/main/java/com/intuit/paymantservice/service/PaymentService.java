package com.intuit.paymantservice.service;

import java.util.List;

import com.intuit.paymantservice.dto.DetailedPaymentMethod;
import com.intuit.paymantservice.dto.PayeeDetails;
import com.intuit.paymantservice.model.PaymentRequest;

public interface PaymentService {
	
	public void sendPaymentToConfirmation(PaymentRequest payment);

	public List<PayeeDetails> getPayees(String payeeName);

	public List<DetailedPaymentMethod> getCustomerPaymentMethods(String userId);

}
