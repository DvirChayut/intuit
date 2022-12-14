package com.intuit.riskchecker.client;


import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.intuit.riskchecker.model.PaymentRequest;
import com.intuit.riskchecker.model.PaymentStatus;

import reactor.core.publisher.Mono;

@Primary
@Component
public class PaymentRiskCheckerMockImpl implements PaymentRiskChecker{

	@Override
	public Mono<ResponseEntity<PaymentStatus>> isPaymentApproved(PaymentRequest payment) {
		double random = Math.random();
		
		if (random > 0.3) {
			 return Mono.just(ResponseEntity.ok().body(PaymentStatus.builder().isApproved(true).build()));
		}
		
		return  Mono.just(ResponseEntity.ok().body(PaymentStatus.builder().isApproved(false).build()));
	}

}
