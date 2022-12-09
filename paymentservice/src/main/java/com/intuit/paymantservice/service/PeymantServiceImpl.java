package com.intuit.paymantservice.service;

import org.springframework.stereotype.Service;

import com.intuit.paymantservice.model.PaymentRequest;
import com.intuit.paymantservice.processor.Producer;

@Service
public class PeymantServiceImpl implements PaymentService{

	private final Producer<PaymentRequest> producer;
	
	public PeymantServiceImpl(Producer<PaymentRequest> producer) {
		this.producer = producer;
	}

	@Override
	public void sendPaymentToConfirmation(PaymentRequest payment) {
		producer.sendMessage(payment);
	}

}
