package com.intuit.paymantservice.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.intuit.paymantservice.repository.CurrencyRepository;

public class CurrencyExistsValidator implements ConstraintValidator<Currency, String>{

	private CurrencyRepository currencyRepository;
	
	
	public CurrencyExistsValidator(CurrencyRepository currencyRepository) {
		this.currencyRepository = currencyRepository;
	}



	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		return currencyRepository.existsByLetters(value);
	}

}
