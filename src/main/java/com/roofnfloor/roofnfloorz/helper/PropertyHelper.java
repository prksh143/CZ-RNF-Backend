package com.roofnfloor.roofnfloorz.helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roofnfloor.roofnfloorz.helper.UserHelper;
import com.roofnfloor.roofnfloorz.model.Property;
import com.roofnfloor.roofnfloorz.repository.PropertyAddressRepository;
import com.roofnfloor.roofnfloorz.repository.ResidentialPropertyAmnetiesRepository;
import com.roofnfloor.roofnfloorz.repository.ResidentialPropertyDetailsRepository;
import com.roofnfloor.roofnfloorz.response.PropertyResponse;

@Component
public class PropertyHelper {

	@Autowired
	UserHelper userHelper;
	
	@Autowired
	PropertyAddressRepository propertyAddressRepository;
	
	@Autowired
	ResidentialPropertyAmnetiesRepository residentialPropertyAmnetiesRepository;
	
	@Autowired
	ResidentialPropertyDetailsRepository residentialPropertyDetailsRepository;
	
	public PropertyResponse mapPropertyDetails(Property property) {
		PropertyResponse propResponse = new PropertyResponse();
		
		propResponse.setId(property.getId());
		propResponse.setPropertyCode(property.getPropertyCode());
		propResponse.setPropertyType(Integer.parseInt(property.getPropertyType().getTypeCode()));
		propResponse.setPropertySubtype(property.getPropertySubtype());
		propResponse.setPropertyTitle(property.getPropertyTitle());
		propResponse.setArea(property.getArea());
		propResponse.setSuperBuiltArea(property.getSuperBuiltArea());
		propResponse.setCarpetArea(property.getCarpetArea());
		propResponse.setPricePerSqft(property.getPricePerSqft());
		propResponse.setDescription(property.getDescription());
		propResponse.setPostedOn(property.getPostedOn());
		propResponse.setVerified(property.isVerified());
		propResponse.setOwner(userHelper.mapUserDetailsForProperty(property.getOwner()));
		propResponse.setFurnishingType(property.getFurnishingType());
		propResponse.setPriceAmount(property.getPriceAmount());
		propResponse.setTokenAmount(property.getTokenAmount());
		propResponse.setAvailableFrom(property.getAvailableFrom());
		propResponse.setRent(property.isRent());
		propResponse.setFloorNumber(property.getFloorNumber());
		propResponse.setTotalFloors(property.getTotalFloors());
		propResponse.setNoticePeriod(property.getNoticePeriod());
		propResponse.setSecurityDeposit(property.getSecurityDeposit());
		propResponse.setPropertyAge(property.getPropertyAge());
		propResponse.setIsProject(property.getIsProject());
		propResponse.setImageslist(property.getImageslist());
		propResponse.setAddress(propertyAddressRepository.fetchAddressByPropertyId(property.getId()).get(0));
		
		if(Integer.parseInt(property.getPropertyType().getTypeCode()) == 0) {
			propResponse.setAmneties(residentialPropertyAmnetiesRepository.fetchAmnetiesByPropertyId(property.getId()).get(0));
			propResponse.setDetails(residentialPropertyDetailsRepository.fetchDetailsByPropertyId(property.getId()).get(0));
		}else {
			propResponse.setAmneties(null);
			propResponse.setDetails(null);
		}
		
		return propResponse;
	}
}
