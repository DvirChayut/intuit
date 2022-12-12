package com.intuit.riskchecker.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentStatus {
	boolean isApproved;
}
