package com.intuit.riskchecker.client;

import com.intuit.riskchecker.model.PaymentRequest;
import com.intuit.riskchecker.model.PaymentStatus;

import reactor.core.publisher.Mono;

public interface PaymentRiskChecker {

	Mono<PaymentStatus> isPaymentApproved(PaymentRequest payment);

}
