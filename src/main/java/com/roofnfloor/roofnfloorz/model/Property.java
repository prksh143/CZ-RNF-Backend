package com.roofnfloor.roofnfloorz.model;

import java.time.LocalDateTime;
import java.util.Date;
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
@Table(name="property")
public class Property extends BasicModel{

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private Long id;

	@Column(name="property_code")
	private String propertyCode;
	
	@ManyToOne
	@JoinColumn(name = "property_type", nullable = false)
	@JsonBackReference
	private PropertyType propertyType;
	
	@Column(name = "property_subtype", nullable = false)
	private String propertySubtype;
	
	@Column(name="property_title", nullable=false)
	private String propertyTitle;
	
	@Column(name="area", nullable=false)
	private int area;
	
	@Column(name="super_built_area", nullable=false)
	private int superBuiltArea;
	
	@Column(name="carpet_area", nullable=false)
	private int carpetArea;
	
	@Column(name="price_per_sqft")
	private int pricePerSqft;

	@Column(columnDefinition="TEXT",name="description")
	private String description;
	
	@Column(name="posted_on", nullable=false)
	private LocalDateTime postedOn;
	
	@ManyToOne
	@JoinColumn(name = "owner", nullable = false)
	@JsonBackReference
	private User owner;
	
	@Column(name="furnishing_type")
	private int furnishingType;
	
	@Column(name="price_amount", nullable=false)
	private int priceAmount;	
	
	@Column(name="token_amount")
	private int tokenAmount;	
	
	@Column(name="available_from")
	private Date availableFrom;
	
	@Column(name="is_rent", nullable=false)
	private boolean isRent;
	
	@Column(name="verified")
	private boolean verified;
	
	@Column(name="floor_no")
	private int floorNumber;
	
	@Column(name="total_floors")
	private int totalFloors;
	
	@Column(name="notice_period", nullable=false)
	private int noticePeriod;
	
	@Column(name="security_deposit", nullable=false)
	private int securityDeposit;
	
	@Column(name="property_age")
	private int propertyAge;
	
	@Column(name="is_project")
	private Boolean isProject;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="pid")
	private Set<PropertyImages> imageslist;

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


	public PropertyType getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(PropertyType propertyType) {
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

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
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

	public int getSecurityDeposit() {
		return securityDeposit;
	}

	public void setSecurityDeposit(int securityDeposit) {
		this.securityDeposit = securityDeposit;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}
}
