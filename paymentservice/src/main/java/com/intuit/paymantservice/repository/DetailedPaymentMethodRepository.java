package com.intuit.paymantservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intuit.paymantservice.model.DetailedPaymentMethod;

public interface DetailedPaymentMethodRepository extends JpaRepository<DetailedPaymentMethod, Integer> {

}
