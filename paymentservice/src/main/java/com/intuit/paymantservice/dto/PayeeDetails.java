package com.intuit.paymantservice.dto;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


@Entity
public class PayeeDetails {
	
	
	private Integer id;
	private String payeeId;
	private String firstName;
	private String lastName;
	private String fullName;
	private String email;
	
	
	public PayeeDetails() {

	}
	
	public PayeeDetails(Integer id, String payeeId, String firstName, String lastName, String fullName, String email) {
		this.id = id;
		this.payeeId = payeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.fullName = fullName;
		this.email = email;
	}
	
	@Id
	@SequenceGenerator(name = "payment_id_sequence",
	 		sequenceName = "payee_id_sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
			generator = "payee_id_sequence")
	@Column(name = "id", updatable = false)
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "payeeId", updatable = false)
	public String getPayeeId() {
		if (Objects.isNull(this.payeeId)) {
			this.payeeId = UUID.randomUUID().toString();
		}
		
		return payeeId;
	}


	public void setPayeeId(String payeeId) {
		
	}

	@Column(name = "firstName", updatable = false)
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "lastName", updatable = false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "fullName")
	public String getFullName() {
		this.fullName = this.firstName.concat(" ").concat(this.lastName);
		
		return fullName;
	}


	public void setFullName(String fullName) {
		
	}

	@Column(name = "email", nullable = false, columnDefinition = "Text", unique = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
