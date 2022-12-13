package com.intuit.paymantservice.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.intuit.paymantservice.dto.DetailedPaymentMethod;
import com.intuit.paymantservice.dto.PayeeDetails;
import com.intuit.paymantservice.model.PaymentRequest;
import com.intuit.paymantservice.processor.Producer;
import com.intuit.paymantservice.repository.DetailedPaymentMethodRepository;
import com.intuit.paymantservice.repository.PayeeDetailsRepository;
//import com.intuit.paymantservice.processor.Producer;

@Service
public class PeymantServiceImpl implements PaymentService{
	
	
	private final PayeeDetailsRepository payeeDetailsRepository;
	private final DetailedPaymentMethodRepository detailedPaymentMethodRepository;
	private final Producer<PaymentRequest> producer;
	

	public PeymantServiceImpl(PayeeDetailsRepository payeeDetailsRepository,
			DetailedPaymentMethodRepository detailedPaymentMethodRepository,
			Producer<PaymentRequest> producer) {
		this.producer = producer;
		this.payeeDetailsRepository = payeeDetailsRepository;
		this.detailedPaymentMethodRepository = detailedPaymentMethodRepository;
	}

	@Override
	public void sendPaymentToConfirmation(PaymentRequest payment) {
		producer.sendMessage(payment);
	}

	@Override
	public List<PayeeDetails> getPayees(String payeeName) {
		if (StringUtils.isNoneEmpty(payeeName)) {
			Optional<PayeeDetails> opt = payeeDetailsRepository.findByFullNameContaining(payeeName);
			if (opt.isPresent()) {
				return List.of(opt.get());
			}else {
				return List.of();
			}
		}
		return payeeDetailsRepository.findAll();
	}

	@Override
	public List<DetailedPaymentMethod> getCustomerPaymentMethods(String userId) {
		
		Optional<List<DetailedPaymentMethod>> opt = detailedPaymentMethodRepository.findByUserId(userId);
		
		if (opt.isEmpty()) {
			return List.of();
		}
		
		return opt.get();
	}

}
