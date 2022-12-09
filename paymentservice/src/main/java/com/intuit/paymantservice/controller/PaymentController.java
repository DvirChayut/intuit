package com.intuit.paymantservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.paymantservice.model.DetailedPaymentMethod;
import com.intuit.paymantservice.model.PaymentRequest;
import com.intuit.paymantservice.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/v1/payment")
public class PaymentController {
	
	private PaymentService paymentService;
		
	public PaymentController(PaymentService paymentService) {
		super();
		this.paymentService = paymentService;
	}

	@PostMapping
	public ResponseEntity<String> createPayment(@RequestBody PaymentRequest paymentRequest) {
		log.info("new payment: {}", paymentRequest);
		
		
		return new ResponseEntity<>("Your apllication recived and being handled!", HttpStatus.OK);
	}
	
	@GetMapping("/methods")
	public List<DetailedPaymentMethod> getCustomerPaymentMethods(@RequestPart String payeeId){
		
		return null;
	}

}
