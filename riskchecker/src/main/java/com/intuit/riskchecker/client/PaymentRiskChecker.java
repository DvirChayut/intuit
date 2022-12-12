package com.intuit.riskchecker.client;

import com.intuit.riskchecker.model.PaymentRequest;
import com.intuit.riskchecker.model.PaymentStatus;

public interface PaymentRiskChecker {

	PaymentStatus isPaymentApproved(PaymentRequest payment);

}
