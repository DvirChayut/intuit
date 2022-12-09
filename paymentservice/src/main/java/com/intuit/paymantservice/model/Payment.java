package com.intuit.paymantservice.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Payment {
	
	@Id
	@SequenceGenerator(name = "payment_id_sequence",
	 		sequenceName = "payment_id_sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
			generator = "payment_id_sequence")
	private Integer id;
	private float amount;
	private String currency;
	private String payeeId;
	private String userId;
	private String paymentMethod;
	
}
