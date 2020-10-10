package com.roofnfloor.roofnfloorz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="payments")
public class Payments {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "owner", nullable = false)
	@JsonBackReference
	private User owner;
	
	@Column(name="package_code")
	private String packageCode;
	
	@Column(name="stripe_token")
	private String stripeToken;
	
	@ManyToOne
	@JoinColumn(name = "pricing_package", nullable = false)
	@JsonBackReference
	private PricingPackage pricingPackage;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getPackageCode() {
		return packageCode;
	}

	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}

	public String getStripeToken() {
		return stripeToken;
	}

	public void setStripeToken(String stripeToken) {
		this.stripeToken = stripeToken;
	}

	public PricingPackage getPricingPackage() {
		return pricingPackage;
	}

	public void setPricingPackage(PricingPackage pricingPackage) {
		this.pricingPackage = pricingPackage;
	}
	
	
}
