package com.intuit.paymantservice.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Currency {
	@Id
	@SequenceGenerator(name = "currency_id_sequence",
	 		sequenceName = "currency_id_sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
			generator = "currency_id_sequence")
	@Column(name = "id", updatable = false)
	private Integer id;
	private String name;
	private String letters; 
	
}
