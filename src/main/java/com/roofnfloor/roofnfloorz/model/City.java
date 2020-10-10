package com.roofnfloor.roofnfloorz.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="city")
public class City {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="city_code", nullable=false)
	private String cityCode;
	
	@Column(name="city_name", nullable=false)
	private String cityName;

	@Column(name="city_state")
	private String cityState;
	
	@Column(name="city_image")
	private String cityImage;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="city")
	private Set<PropertyAddress> propertyList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityState() {
		return cityState;
	}

	public void setCityState(String cityState) {
		this.cityState = cityState;
	}

	public String getCityImage() {
		return cityImage;
	}

	public void setCityImage(String cityImage) {
		this.cityImage = cityImage;
	}

	public Set<PropertyAddress> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(Set<PropertyAddress> propertyList) {
		this.propertyList = propertyList;
	}


	
}
