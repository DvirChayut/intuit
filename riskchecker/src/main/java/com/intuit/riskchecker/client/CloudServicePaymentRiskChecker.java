package com.intuit.riskchecker.client;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.intuit.riskchecker.exception.client.RiskCheckServiceClientException;
import com.intuit.riskchecker.exception.client.RiskCheckServiceServerException;
import com.intuit.riskchecker.model.PaymentRequest;
import com.intuit.riskchecker.model.PaymentStatus;

import reactor.core.Exceptions;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;



@Component
public class CloudServicePaymentRiskChecker implements PaymentRiskChecker{

	private static final ParameterizedTypeReference<ResponseEntity<PaymentStatus>> IS_PAYMENT_APPROVED_RESPONSE_TIPE = new ParameterizedTypeReference<>() {};
	
	private final WebClient webClient;
	
	private final String serviceUrl;
	
	
	public CloudServicePaymentRiskChecker(WebClient webClient, 
			@Value("risk.engiene.service.url") String serviceUrl) {
		this.serviceUrl = serviceUrl;
		this.webClient = webClient;
	}


	@Override
	public Mono<ResponseEntity<PaymentStatus>> isPaymentApproved(PaymentRequest payment) {

		return webClient
				.post()
				.uri(serviceUrl)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError, clientResponse -> {
					
					return clientResponse.bodyToMono(String.class).flatMap(errorMessage -> 
						Mono.error(new RiskCheckServiceClientException(errorMessage, clientResponse.statusCode().value())));
				})
				.onStatus(HttpStatus::is5xxServerError, clientResponse -> {
					
					return clientResponse.bodyToMono(String.class).flatMap(errorMessage -> 
						Mono.error(new RiskCheckServiceServerException("Server exception in RiskChecek service: " + errorMessage, clientResponse.statusCode().value())));									
				})
				.bodyToMono(IS_PAYMENT_APPROVED_RESPONSE_TIPE)
				.retryWhen(retrySpec());
		
	}	
	
	private static Retry retrySpec() {
		return Retry.fixedDelay(3, Duration.ofSeconds(1))
			    .filter(ex -> ex instanceof RiskCheckServiceServerException )
				.onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> Exceptions.propagate(retrySignal.failure())); 
	}
}
