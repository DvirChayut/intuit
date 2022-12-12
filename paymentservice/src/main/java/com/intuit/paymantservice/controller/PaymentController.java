package com.intuit.paymantservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.paymantservice.dto.DetailedPaymentMethod;
import com.intuit.paymantservice.dto.PayeeDetails;
import com.intuit.paymantservice.model.PaymentRequest;
import com.intuit.paymantservice.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/v1/payment")
public class PaymentController {
	
	private PaymentService paymentService;
		
	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@PostMapping
	public ResponseEntity<String> createPayment(@Valid @RequestBody PaymentRequest paymentRequest) {
		log.info("new payment: {}", paymentRequest);
		
		
		return ResponseEntity.status(HttpStatus.OK).body("Your apllication recived and being handled!");
	}
	
	@GetMapping("/methods")
	public ResponseEntity<List<DetailedPaymentMethod>> getCustomerPaymentMethods(@RequestParam String userId){
		
		List<DetailedPaymentMethod> paymentMethods = paymentService.getCustomerPaymentMethods(userId);
		
		if (paymentMethods.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(paymentMethods);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(paymentMethods);
	}
	
	@GetMapping("/payees")
	public ResponseEntity<List<PayeeDetails>> getPayees(@RequestParam(name = "name", required = false) String payeeName){
		List<PayeeDetails> payees = paymentService.getPayees(payeeName);
		if (payees.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(payees);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(payees);
	}
	
	@GetMapping()
	public String helloWorld(){
		
		return "hello World";
	}

}
