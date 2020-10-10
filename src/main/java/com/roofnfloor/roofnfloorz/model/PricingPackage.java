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
@Table(name="pricing_package")
public class PricingPackage {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_type", nullable = false)
	@JsonBackReference
	private UserType userType;
	
	@Column(name="package_name")
	private String packageName;
	
	@Column(name="package_code")
	private String packageCode;
	
	@Column(name="package_amount")
	private double packageAmount;

	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="pricingPackage")
	private Set<Payments> paymentsList;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getPackageCode() {
		return packageCode;
	}

	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}

	public double getPackageAmount() {
		return packageAmount;
	}

	public void setPackageAmount(double packageAmount) {
		this.packageAmount = packageAmount;
	}

	public Set<Payments> getPaymentsList() {
		return paymentsList;
	}

	public void setPaymentsList(Set<Payments> paymentsList) {
		this.paymentsList = paymentsList;
	}
	
	
	
}
