package com.intuit.paymantservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intuit.paymantservice.dto.DetailedPaymentMethod;


public interface DetailedPaymentMethodRepository extends JpaRepository<DetailedPaymentMethod, Integer> {

	Optional<List<DetailedPaymentMethod>> findByUserId(String userId);
	
}
	