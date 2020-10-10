package com.roofnfloor.roofnfloorz.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin")
public class Admin {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private Long id;

	@Column(name="password", nullable=false)
	private String password;
	

	@Column(name="email", nullable=false)
	private String email;


	@Column(name="uuid_key",nullable = true)
	private String uuidKey;
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUuidKey() {
		return uuidKey;
	}


	public void setUuidKey(String uuidKey) {
		this.uuidKey = uuidKey;
	}



}
