package com.roofnfloor.roofnfloorz.request;

public class EditPackagePricingRequest {

	private String packageCode;
	private double packageAmount;
	private String salt;
	
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
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
}
