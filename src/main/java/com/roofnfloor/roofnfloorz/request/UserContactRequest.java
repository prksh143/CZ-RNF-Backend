package com.roofnfloor.roofnfloorz.request;

public class UserContactRequest {

	private String name;
	
	private String message;
	
	private String email;
	
	private String mobile;
	
	private int isQuickContact;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
