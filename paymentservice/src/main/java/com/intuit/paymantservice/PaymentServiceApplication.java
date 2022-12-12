package com.intuit.paymantservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.intuit.paymantservice.dto.DetailedPaymentMethod;
import com.intuit.paymantservice.dto.PayeeDetails;
import com.intuit.paymantservice.repository.DetailedPaymentMethodRepository;
import com.intuit.paymantservice.repository.PayeeDetailsRepository;

@SpringBootApplication
public class PaymentServiceApplication 
{
    public static void main( String[] args )
    {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }
    
    @Bean
    CommandLineRunner commandLineRunner(PayeeDetailsRepository payeeDetailsRepository, 
    									DetailedPaymentMethodRepository detailedPaymentMethodRepository) {
    	return args -> {
    		PayeeDetails payee = new PayeeDetails(null, null, "Dvir", "Chayut", null, "dvir@com");
    		
    		payeeDetailsRepository.save(payee);
    		
    		DetailedPaymentMethod detailedPaymentMethod = new DetailedPaymentMethod();
    	};
    }
}
