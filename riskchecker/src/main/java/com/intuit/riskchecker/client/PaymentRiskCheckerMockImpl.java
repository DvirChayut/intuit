package com.intuit.riskchecker.client;


import org.springframework.stereotype.Component;

import com.intuit.riskchecker.model.PaymentRequest;
import com.intuit.riskchecker.model.PaymentStatus;

@Component
public class PaymentRiskCheckerMockImpl implements PaymentRiskChecker{

	@Override
	public PaymentStatus isPaymentApproved(PaymentRequest payment) {
		double random = Math.random();
		
		if (random > 0.3) {
			 return PaymentStatus.builder().isApproved(true).build();
		}
		
		return  PaymentStatus.builder().isApproved(false).build();
	}

}
