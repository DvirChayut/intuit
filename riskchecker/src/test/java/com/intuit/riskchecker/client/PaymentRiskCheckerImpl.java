package com.intuit.riskchecker.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.intuit.riskchecker.model.Payment;
import com.intuit.riskchecker.model.PaymentRequest;
import com.intuit.riskchecker.model.PaymentStatus;



@Component
public class PaymentRiskCheckerImpl implements PaymentRIskChecker{

	private final WebClient webClient;
	
	private final String serviceUrl;
	
	
	public PaymentRiskCheckerImpl(WebClient webClient, 
			@Value("risk.engiene.service.url") String serviceUrl) {
		this.serviceUrl = serviceUrl;
		this.webClient = webClient;
	}


	@Override
	public PaymentStatus isPaymentApproved(PaymentRequest payment) {

		return webClient
				.post()
				.uri(serviceUrl)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(PaymentStatus.class)
				.block();
	}
	
	@KafkaListener(topics = "${payment.topic.name}", groupId = "${payment.group.id}")
	public void consumePayment(PaymentRequest paymentRequest) {
		PaymentStatus status = isPaymentApproved(paymentRequest);
		
		if (status!= null && status.isApproved()) {
			 //save to approved table
		}else {
			//save to unproved table
		}
	}
	
}
