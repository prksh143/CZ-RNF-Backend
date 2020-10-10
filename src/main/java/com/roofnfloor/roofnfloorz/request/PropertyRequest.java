package com.roofnfloor.roofnfloorz.request;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class PropertyRequest {

	private String salt;
	
	private String propertyType;

	private String propertySubtype;

	private String propertyTitle;

	private int area;

	private int superBuiltArea;

	private int carpetArea;

	private int pricePerSqft;

	private String description;

	private String userid;

	private int furnishingType;

	private int priceAmount;

	private int tokenAmount;

	private Date availableFrom;

	private boolean isOnRent;

	private int floorNumber;

	private int totalFloors;

	private int noticePeriod;

	private int securityDeposit;

	private int propertyAge;

	private Boolean isProject;

	private String addressLine;

	private String city;

	private String landmark;

	private String pincode;

	private String country;

	private boolean gym;

	private boolean swimmingPool;

	private boolean lift;

	private boolean gated;

	private boolean security;

	private boolean salon;

	private boolean supermarket;

	private boolean stationary;

	private boolean indoorGames;

	private boolean outdoorGames;

	private String facing;

	private int parking;

	private int balcony;

	private int bedroom;

	private int bathroom;

	private String availableFor;

	private int maintenanceCharges;
	
	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getPropertySubtype() {
		return propertySubtype;
	}

	public void setPropertySubtype(String propertySubtype) {
		this.propertySubtype = propertySubtype;
	}

	public String getPropertyTitle() {
		return propertyTitle;
	}

	public void setPropertyTitle(String propertyTitle) {
		this.propertyTitle = propertyTitle;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getSuperBuiltArea() {
		return superBuiltArea;
	}

	public void setSuperBuiltArea(int superBuiltArea) {
		this.superBuiltArea = superBuiltArea;
	}

	public int getCarpetArea() {
		return carpetArea;
	}

	public void setCarpetArea(int carpetArea) {
		this.carpetArea = carpetArea;
	}

	public int getPricePerSqft() {
		return pricePerSqft;
	}

	public void setPricePerSqft(int pricePerSqft) {
		this.pricePerSqft = pricePerSqft;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getFurnishingType() {
		return furnishingType;
	}

	public void setFurnishingType(int furnishingType) {
		this.furnishingType = furnishingType;
	}

	public int getPriceAmount() {
		return priceAmount;
	}

	public void setPriceAmount(int priceAmount) {
		this.priceAmount = priceAmount;
	}

	public int getTokenAmount() {
		return tokenAmount;
	}

	public void setTokenAmount(int tokenAmount) {
		this.tokenAmount = tokenAmount;
	}

	public Date getAvailableFrom() {
		return availableFrom;
	}

	public void setAvailableFrom(Date availableFrom) {
		this.availableFrom = availableFrom;
	}

	public boolean isOnRent() {
		return isOnRent;
	}

	public void setOnRent(boolean isOnRent) {
		this.isOnRent = isOnRent;
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}

	public int getTotalFloors() {
		return totalFloors;
	}

	public void setTotalFloors(int totalFloors) {
		this.totalFloors = totalFloors;
	}

	public int getNoticePeriod() {
		return noticePeriod;
	}

	public void setNoticePeriod(int noticePeriod) {
		this.noticePeriod = noticePeriod;
	}

	public int getSecurityDeposit() {
		return securityDeposit;
	}

	public void setSecurityDeposit(int securityDeposit) {
		this.securityDeposit = securityDeposit;
	}

	public int getPropertyAge() {
		return propertyAge;
	}

	public void setPropertyAge(int propertyAge) {
		this.propertyAge = propertyAge;
	}

	public Boolean getIsProject() {
		return isProject;
	}

	public void setIsProject(Boolean isProject) {
		this.isProject = isProject;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public boolean isGym() {
		return gym;
	}

	public void setGym(boolean gym) {
		this.gym = gym;
	}

	public boolean isSwimmingPool() {
		return swimmingPool;
	}

	public void setSwimmingPool(boolean swimmingPool) {
		this.swimmingPool = swimmingPool;
	}

	public boolean isLift() {
		return lift;
	}

	public void setLift(boolean lift) {
		this.lift = lift;
	}

	public boolean isGated() {
		return gated;
	}

	public void setGated(boolean gated) {
		this.gated = gated;
	}

	public boolean isSecurity() {
		return security;
	}

	public void setSecurity(boolean security) {
		this.security = security;
	}

	public boolean isSalon() {
		return salon;
	}

	public void setSalon(boolean salon) {
		this.salon = salon;
	}

	public boolean isSupermarket() {
		return supermarket;
	}

	public void setSupermarket(boolean supermarket) {
		this.supermarket = supermarket;
	}

	public boolean isStationary() {
		return stationary;
	}

	public void setStationary(boolean stationary) {
		this.stationary = stationary;
	}

	public boolean isIndoorGames() {
		return indoorGames;
	}

	public void setIndoorGames(boolean indoorGames) {
		this.indoorGames = indoorGames;
	}

	public boolean isOutdoorGames() {
		return outdoorGames;
	}

	public void setOutdoorGames(boolean outdoorGames) {
		this.outdoorGames = outdoorGames;
	}

	public String getFacing() {
		return facing;
	}

	public void setFacing(String facing) {
		this.facing = facing;
	}

	public int getParking() {
		return parking;
	}

	public void setParking(int parking) {
		this.parking = parking;
	}

	public int getBalcony() {
		return balcony;
	}

	public void setBalcony(int balcony) {
		this.balcony = balcony;
	}

	public int getBedroom() {
		return bedroom;
	}

	public void setBedroom(int bedroom) {
		this.bedroom = bedroom;
	}

	public int getBathroom() {
		return bathroom;
	}

	public void setBathroom(int bathroom) {
		this.bathroom = bathroom;
	}

	public String getAvailableFor() {
		return availableFor;
	}

	public void setAvailableFor(String availableFor) {
		this.availableFor = availableFor;
	}

	public int getMaintenanceCharges() {
		return maintenanceCharges;
	}

	public void setMaintenanceCharges(int maintenanceCharges) {
		this.maintenanceCharges = maintenanceCharges;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return "PropertyRequest [propertyType=" + propertyType + ", propertySubtype=" + propertySubtype
				+ ", propertyTitle=" + propertyTitle + ", area=" + area + ", superBuiltArea=" + superBuiltArea
				+ ", carpetArea=" + carpetArea + ", pricePerSqft=" + pricePerSqft + ", description=" + description
				+ ", userid=" + userid + ", furnishingType=" + furnishingType + ", priceAmount=" + priceAmount
				+ ", tokenAmount=" + tokenAmount + ", availableFrom=" + availableFrom + ", isOnRent=" + isOnRent
				+ ", floorNumber=" + floorNumber + ", totalFloors=" + totalFloors + ", noticePeriod=" + noticePeriod
				+ ", securityDeposit=" + securityDeposit + ", propertyAge=" + propertyAge + ", isProject=" + isProject
				+ ", addressLine=" + addressLine + ", city=" + city +  ", landmark=" + landmark
				+ ", pincode=" + pincode + ", country=" + country + ", gym=" + gym + ", swimmingPool=" + swimmingPool
				+ ", lift=" + lift + ", gated=" + gated + ", security=" + security + ", salon=" + salon
				+ ", supermarket=" + supermarket + ", stationary=" + stationary + ", indoorGames=" + indoorGames
				+ ", outdoorGames=" + outdoorGames + ", facing=" + facing + ", parking=" + parking + ", balcony="
				+ balcony + ", bedroom=" + bedroom + ", bathroom=" + bathroom + ", availableFor=" + availableFor
				+ ", maintenanceCharges=" + maintenanceCharges + "]";
	}
}
