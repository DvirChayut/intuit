package com.intuit.paymantservice.processor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.intuit.paymantservice.model.PaymentRequest;

@Component
public class PaymentKafkaProducer implements Producer<PaymentRequest>{

	private final KafkaTemplate<String, PaymentRequest> kafkaTemplate;
	private final String paymentTopic;
	
	public PaymentKafkaProducer(KafkaTemplate<String, PaymentRequest> kafkaTemplate,
			@Value("${payment.topic.name}") String paymentTopic) {
		this.paymentTopic = paymentTopic;
		this.kafkaTemplate = kafkaTemplate;
	}


	@Override
	public void sendMessage(PaymentRequest message) {
		kafkaTemplate.send(paymentTopic, message);
	}

}
