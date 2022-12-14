package com.intuit.riskchecker.client;

import org.springframework.http.ResponseEntity;

import com.intuit.riskchecker.model.PaymentRequest;
import com.intuit.riskchecker.model.PaymentStatus;

import reactor.core.publisher.Mono;

public interface PaymentRiskChecker {

	Mono<ResponseEntity<PaymentStatus>> isPaymentApproved(PaymentRequest payment);

}
