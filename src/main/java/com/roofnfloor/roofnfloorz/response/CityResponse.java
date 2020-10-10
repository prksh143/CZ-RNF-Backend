package com.roofnfloor.roofnfloorz.response;

import java.util.List;
import com.roofnfloor.roofnfloorz.model.Property;

public class CityResponse {

	private List<PropertyResponse> featuredPropertyList;

	private List<PropertyResponse> allPropertyList;

	public List<PropertyResponse> getFeaturedPropertyList() {
		return featuredPropertyList;
	}

	public void setFeaturedPropertyList(List<PropertyResponse> featuredPropertyList) {
		this.featuredPropertyList = featuredPropertyList;
	}

	public List<PropertyResponse> getAllPropertyList() {
		return allPropertyList;
	}

	public void setAllPropertyList(List<PropertyResponse> allPropertyList) {
		this.allPropertyList = allPropertyList;
	}
}
