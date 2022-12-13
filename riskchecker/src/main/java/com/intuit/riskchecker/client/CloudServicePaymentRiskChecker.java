package com.intuit.riskchecker.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.intuit.riskchecker.model.PaymentRequest;
import com.intuit.riskchecker.model.PaymentStatus;



@Component
public class CloudServicePaymentRiskChecker implements PaymentRiskChecker{

	private final WebClient webClient;
	
	private final String serviceUrl;
	
	
	public CloudServicePaymentRiskChecker(WebClient webClient, 
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
}
