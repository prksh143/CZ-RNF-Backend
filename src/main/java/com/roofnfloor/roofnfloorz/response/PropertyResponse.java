package com.roofnfloor.roofnfloorz.response;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.roofnfloor.roofnfloorz.model.PropertyAddress;
import com.roofnfloor.roofnfloorz.model.PropertyImages;
import com.roofnfloor.roofnfloorz.model.ResidentialPropertyAmneties;
import com.roofnfloor.roofnfloorz.model.ResidentialPropertyDetails;
import com.roofnfloor.roofnfloorz.model.User;

public class PropertyResponse {

	private Long id;

	private String propertyCode;
	
	private int propertyType;
	
	private String propertySubtype;
	
	private String propertyTitle;
	
	private int area;
	
	private int superBuiltArea;
	
	private int carpetArea;
	
	private int pricePerSqft;

	private String description;
	
	private LocalDateTime postedOn;
	
	private UserResponseForProperty owner;
	
	private int furnishingType;
	
	private int priceAmount;	
	
	private int tokenAmount;	
	
	private Date availableFrom;
	
	private boolean isRent;
	
	private int floorNumber;
	
	private int totalFloors;
	
	private int noticePeriod;
	
	private int securityDeposit;
	
	private int propertyAge;
	
	private Boolean isProject;

	private Boolean verified;
	
	private Set<PropertyImages> imageslist;

	private PropertyAddress address;
	
	private ResidentialPropertyAmneties amneties;
	
	private ResidentialPropertyDetails details;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPropertyCode() {
		return propertyCode;
	}

	public void setPropertyCode(String propertyCode) {
		this.propertyCode = propertyCode;
	}

	public int getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(int propertyType) {
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

	public LocalDateTime getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(LocalDateTime postedOn) {
		this.postedOn = postedOn;
	}

	public UserResponseForProperty getOwner() {
		return owner;
	}

	public void setOwner(UserResponseForProperty owner) {
		this.owner = owner;
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

	public boolean isRent() {
		return isRent;
	}

	public void setRent(boolean isRent) {
		this.isRent = isRent;
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

	public Set<PropertyImages> getImageslist() {
		return imageslist;
	}

	public void setImageslist(Set<PropertyImages> imageslist) {
		this.imageslist = imageslist;
	}

	public PropertyAddress getAddress() {
		return address;
	}

	public void setAddress(PropertyAddress address) {
		this.address = address;
	}

	public ResidentialPropertyAmneties getAmneties() {
		return amneties;
	}

	public void setAmneties(ResidentialPropertyAmneties amneties) {
		this.amneties = amneties;
	}

	public ResidentialPropertyDetails getDetails() {
		return details;
	}

	public void setDetails(ResidentialPropertyDetails details) {
		this.details = details;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}
	
}
