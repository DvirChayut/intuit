package com.intuit.paymantservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intuit.paymantservice.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
