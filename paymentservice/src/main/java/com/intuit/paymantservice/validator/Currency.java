package com.intuit.paymantservice.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;

import org.springframework.messaging.handler.annotation.Payload;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = CurrencyExistsValidator.class)
public @interface Currency {
	 String message() default "Currency isn't supported";
	 Class<?>[] groups() default {};
	 Class<? extends Payload>[] payload() default {};
}
