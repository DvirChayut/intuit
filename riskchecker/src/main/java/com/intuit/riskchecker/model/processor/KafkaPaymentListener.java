package com.intuit.riskchecker.model.processor;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.intuit.riskchecker.client.PaymentRiskChecker;
import com.intuit.riskchecker.model.PaymentRequest;
import com.intuit.riskchecker.model.PaymentStatus;

@Component
public class KafkaPaymentListener {
	
	private final PaymentRiskChecker paymentRiskChecker;
	
	public KafkaPaymentListener(PaymentRiskChecker paymentRiskChecker) {
		this.paymentRiskChecker = paymentRiskChecker;
	}



	@KafkaListener(topics = "${payment.topic.name}", groupId = "${payment.group.id}")
	public void consumePayment(PaymentRequest paymentRequest) {
		PaymentStatus status = paymentRiskChecker.isPaymentApproved(paymentRequest);
		
		if (status!= null && status.isApproved()) {
			 //save to approved table
		}else {
			//save to unproved table
		}
	}
}
