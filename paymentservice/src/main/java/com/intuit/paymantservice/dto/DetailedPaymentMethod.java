package com.intuit.paymantservice.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DetailedPaymentMethod {
	@Id
	@SequenceGenerator(name = "payment_id_sequence",
	 		sequenceName = "detailed_payment_method_id_sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
			generator = "detailed_payment_method_id_sequence")
	private Integer id;
	private String userId;
	private String creditCompany;
	private String creditNumber;
}
