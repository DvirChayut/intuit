package com.intuit.paymantservice.dto;


import javax.persistence.Column;
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
	@Column(name = "id", updatable = false)
	private Integer id;
	
	@Column(name = "amount", nullable = false)
	private float amount;

	@Column(name = "currency", nullable = false, columnDefinition = "Text")
	private String currency;
	
	private String payeeId;
	
	private String userId;
	
	private String paymentMethod;
	
}
