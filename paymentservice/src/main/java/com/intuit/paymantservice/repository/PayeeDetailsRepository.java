package com.intuit.paymantservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intuit.paymantservice.dto.PayeeDetails;


public interface PayeeDetailsRepository extends JpaRepository<PayeeDetails, Integer>{
	Optional<PayeeDetails> findByFullNameContaining(String name);
}
