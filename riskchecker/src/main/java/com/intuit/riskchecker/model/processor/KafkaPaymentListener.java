package com.intuit.riskchecker.model.processor;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.intuit.riskchecker.client.PaymentRiskChecker;
import com.intuit.riskchecker.model.PaymentRequest;
import com.intuit.riskchecker.model.PaymentStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaPaymentListener {

	private final PaymentRiskChecker paymentRiskChecker;

	public KafkaPaymentListener(PaymentRiskChecker paymentRiskChecker) {
		this.paymentRiskChecker = paymentRiskChecker;
	}

	@KafkaListener(topics = "${spring.kafka.consumer.topic}")
	public void receivePayment(@Payload PaymentRequest paymentRequest, 
			@Headers MessageHeaders headers,
			Acknowledgment acknowledgment) {
		log.info("received data='{}'", paymentRequest);

		headers.keySet().forEach(key -> {
			log.info("{}: {}", key, headers.get(key));
		});
		
		PaymentStatus status = paymentRiskChecker.isPaymentApproved(paymentRequest).block().getBody();

		if (status != null && status.isApproved()) {
			// save to approved table
			acknowledgment.acknowledge();
		} else {
			// save to unproved table
			acknowledgment.acknowledge();
		}
		
		
	}

	
}
