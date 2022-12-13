package com.intuit.paymantservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intuit.paymantservice.dto.Currency;


public interface CurrencyRepository extends JpaRepository<Currency, Integer>{

	boolean existsByLetters(String value);

}
