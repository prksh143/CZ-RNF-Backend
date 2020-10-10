package com.roofnfloor.roofnfloorz.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="users")
public class User extends BasicModel{

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="mobile", nullable=false)
	private String mobile;
	
	@Column(name="email", nullable=false)
	private String email;
	
	@Column(name="is_email_verified", nullable=false)
	private Boolean isEmailVerified;
	
	@Column(name="is_mobile_verified", nullable=false)
	private Boolean isMobileVerified;
	
	@Column(name="address_line_1")
	private String addressLine1;
	
	@Column(name="address_line_2")
	private String addressLine2;
	
	@Column(name="pincode")
	private String pincode;
	
	@Column(name="state")
	private String state;
	
	@Column(name="country")
	private String country;
	
	@Column(name="password", nullable=false)
	private String password;
	
	@Column(name="uuid_key",nullable = true)
	private String uuidKey;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="owner")
	private Set<Property> adPropertieslist;
	
	@ManyToOne
	@JoinColumn(name = "user_type", nullable = false)
	@JsonBackReference
	private UserType userType;

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="owner")
	private Set<Payments> paymentsList;
	
	
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean isEmailVerified() {
		return isEmailVerified;
	}

	public void setEmailVerified(Boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

	public Boolean isMobileVerified() {
		return isMobileVerified;
	}

	public void setMobileVerified(Boolean isMobileVerified) {
		this.isMobileVerified = isMobileVerified;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Property> getAdPropertieslist() {
		return adPropertieslist;
	}

	public void setAdPropertieslist(Set<Property> adPropertieslist) {
		this.adPropertieslist = adPropertieslist;
	}

	public Boolean getIsEmailVerified() {
		return isEmailVerified;
	}

	public void setIsEmailVerified(Boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

	public Boolean getIsMobileVerified() {
		return isMobileVerified;
	}

	public void setIsMobileVerified(Boolean isMobileVerified) {
		this.isMobileVerified = isMobileVerified;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Set<Payments> getPaymentsList() {
		return paymentsList;
	}

	public void setPaymentsList(Set<Payments> paymentsList) {
		this.paymentsList = paymentsList;
	}

	public String getUuidKey() {
		return uuidKey;
	}

	public void setUuidKey(String uuidKey) {
		this.uuidKey = uuidKey;
	}
	
	
}
