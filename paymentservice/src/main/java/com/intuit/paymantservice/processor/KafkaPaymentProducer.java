package com.intuit.paymantservice.processor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.intuit.paymantservice.model.PaymentRequest;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaPaymentProducer implements Producer<PaymentRequest>{

	private final KafkaTemplate<String, PaymentRequest> kafkaTemplate;
	private final String paymentTopic;
	
	public KafkaPaymentProducer(KafkaTemplate<String, PaymentRequest> kafkaTemplate,
			@Value("${spring.kafka.producer.topic}") String paymentTopic) {
		this.paymentTopic = paymentTopic;
		this.kafkaTemplate = kafkaTemplate;
	}


	@Override
	public void sendMessage(PaymentRequest paymentRequest) {
		log.info("sending data='{}' to topic='{}'", paymentRequest, paymentTopic);

        Message<PaymentRequest> message = MessageBuilder
                .withPayload(paymentRequest)
                .setHeader(KafkaHeaders.TOPIC, paymentTopic)
                .build();
        
        kafkaTemplate.send(message);
	}

}
