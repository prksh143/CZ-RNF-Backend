package com.roofnfloor.roofnfloorz.request;

public class AddPackageRequest {

	private String userTypeCode;
	private String packageName;
	private String packageCode;
	private double packageAmount;
	public String getUserTypeCode() {
		return userTypeCode;
	}
	public void setUserTypeCode(String userTypeCode) {
		this.userTypeCode = userTypeCode;
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
	
	
}
