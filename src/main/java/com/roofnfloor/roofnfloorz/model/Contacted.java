package com.roofnfloor.roofnfloorz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contacted")
public class Contacted extends BasicModel {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="email", nullable=false)
	private String email;

	@Column(name="mobile")
	private String mobile;
	
	@Column(name="message")
	private String message;
	
	@Column(name="is_quick_contact", nullable=false)
	private int isQuickContact;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getIsQuickContact() {
		return isQuickContact;
	}

	public void setIsQuickContact(int isQuickContact) {
		this.isQuickContact = isQuickContact;
	}
}
