package com.roofnfloor.roofnfloorz.serviceimpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.roofnfloor.roofnfloorz.helper.PropertyHelper;
import com.roofnfloor.roofnfloorz.model.Admin;
import com.roofnfloor.roofnfloorz.model.City;
import com.roofnfloor.roofnfloorz.model.Notification;
import com.roofnfloor.roofnfloorz.model.Property;
import com.roofnfloor.roofnfloorz.model.PropertyAddress;
import com.roofnfloor.roofnfloorz.model.PropertyImages;
import com.roofnfloor.roofnfloorz.model.PropertyType;
import com.roofnfloor.roofnfloorz.model.ResidentialPropertyAmneties;
import com.roofnfloor.roofnfloorz.model.ResidentialPropertyDetails;
import com.roofnfloor.roofnfloorz.model.User;
import com.roofnfloor.roofnfloorz.repository.AdminRepository;
import com.roofnfloor.roofnfloorz.repository.CityRepository;
import com.roofnfloor.roofnfloorz.repository.PropertyAddressRepository;
import com.roofnfloor.roofnfloorz.repository.PropertyImagesRepository;
import com.roofnfloor.roofnfloorz.repository.PropertyRepository;
import com.roofnfloor.roofnfloorz.repository.PropertyTypeRepository;
import com.roofnfloor.roofnfloorz.repository.ResidentialPropertyAmnetiesRepository;
import com.roofnfloor.roofnfloorz.repository.ResidentialPropertyDetailsRepository;
import com.roofnfloor.roofnfloorz.repository.UserRepository;
import com.roofnfloor.roofnfloorz.request.DeleteUserPropertyRequest;
import com.roofnfloor.roofnfloorz.request.PropertyRequest;
import com.roofnfloor.roofnfloorz.response.PropertyResponse;
import com.roofnfloor.roofnfloorz.response.Response;
import com.roofnfloor.roofnfloorz.service.PropertyService;
import com.roofnfloor.roofnfloorz.utility.DateUtils;

@Service
public class PropertyServiceImpl implements PropertyService {

	@Autowired
	DateUtils dateUtils;

	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	PropertyRepository propertyRepository;
	
	@Autowired
	ResidentialPropertyAmnetiesRepository residentialPropertyAmnetiesRepository;

	@Autowired
	ResidentialPropertyDetailsRepository residentialPropertyDetailsRepository;
	
	@Autowired
	PropertyImagesRepository propertyImagesRepository;
	
	@Autowired
	PropertyAddressRepository propertyAddresseRepository;
	
	@Autowired
	PropertyTypeRepository propertyTypeRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PropertyHelper propertyHelper;
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@Override
	public Response addProperty(PropertyRequest request) {
		
		Response response = new Response();
		
		User us = userRepository.findById(Long.parseLong(request.getUserid())).get();
		if(!us.getUuidKey().equals(request.getSalt())) {
			response.setError(true);
			response.setMessage("Unauthenticated User");
			response.setResponse(null);
			return response;
		}
		
		Property property = new Property();
		
		// Generate Unique Code
		property.setPropertyCode("PRP01");
		
		property.setPropertyType(propertyTypeRepository.fetchPropertyTypeByCode(request.getPropertyType()));
		property.setPropertySubtype(request.getPropertySubtype());
		property.setPropertyTitle(request.getPropertyTitle());

		property.setArea(request.getArea());
		property.setCarpetArea(request.getCarpetArea());
		property.setSuperBuiltArea(request.getSuperBuiltArea());
		property.setPricePerSqft(request.getPricePerSqft());
		property.setDescription(request.getDescription());
		property.setPostedOn(dateUtils.getCurrentDate());
		System.out.println("-Owner id-"+ request.getUserid());
		Optional<User> owner = userRepository.findById(Long.parseLong(request.getUserid()));
		property.setOwner(owner.get());
		property.setFurnishingType(request.getFurnishingType());
		property.setPriceAmount(request.getPriceAmount());
		if(!request.isOnRent()) {
			property.setTokenAmount(request.getTokenAmount());
		}
		property.setAvailableFrom(request.getAvailableFrom());
		property.setRent(request.isOnRent());
		property.setFloorNumber(request.getFloorNumber());
		property.setTotalFloors(request.getTotalFloors());
		System.out.println(request.isOnRent());
		if(request.isOnRent()) {
			property.setNoticePeriod(request.getNoticePeriod());
			property.setSecurityDeposit(request.getSecurityDeposit());
		}	
		property.setPropertyAge(request.getPropertyAge());
		property.setIsProject(request.getIsProject());

		Property saved = propertyRepository.save(property);

		if (saved == null) {
			response.setError(true);
			response.setMessage("Unable to add Property");
			response.setResponse(null);
			return response;
		} 

		String message = "";
		
		// saving address of property
		PropertyAddress pa = new PropertyAddress();
		pa.setPid(saved.getId());
		pa.setAddressLine(request.getAddressLine());
		pa.setCity(cityRepository.fetchCityByCityCode(request.getCity()));
		pa.setLandmark(request.getLandmark());
		pa.setPincode(request.getPincode());
		pa.setState(cityRepository.fetchCityByCityCode(request.getCity()).getCityState());
		pa.setCountry(request.getCountry());

		PropertyAddress savedPa = propertyAddresseRepository.save(pa);
		if (savedPa == null) {
			message += "Unable to add Property address.";
		} 
		
		// if property is residential
		if(request.getPropertyType().contentEquals("1") ){
			// adding amneties
			ResidentialPropertyAmneties rpa = new ResidentialPropertyAmneties();
			rpa.setPid(saved.getId());
			rpa.setGym(request.isGym());
			rpa.setSwimmingPool(request.isSwimmingPool());
			rpa.setLift(request.isLift());
			rpa.setGated(request.isGated());
			rpa.setSecurity(request.isSecurity());
			rpa.setSalon(request.isSalon());
			rpa.setSupermarket(request.isSupermarket());
			rpa.setStationary(request.isStationary());
			rpa.setIndoorGames(request.isIndoorGames());
			rpa.setOutdoorGames(request.isOutdoorGames());
			
			ResidentialPropertyAmneties savedRpa = residentialPropertyAmnetiesRepository.save(rpa);

			if (savedRpa == null) {
				response.setError(true);
				response.setMessage("Unable to add Residential Property amneties");
				response.setResponse(null);
				return response;
			} 
			
			// adding details
			ResidentialPropertyDetails rpd = new ResidentialPropertyDetails();
			rpd.setPid(saved.getId());
			rpd.setFacing(request.getFacing());
			rpd.setParking(request.getParking());
			rpd.setBalcony(request.getBalcony());
			rpd.setBedroom(request.getBedroom());
			rpd.setBathroom(request.getBathroom());
			if(request.isOnRent()) {
				rpd.setAvailableFor(request.getAvailableFor());
				rpd.setMaintenanceCharges(request.getMaintenanceCharges());
			}
			
			ResidentialPropertyDetails savedRpd = residentialPropertyDetailsRepository.save(rpd);
			
			if (savedRpd == null) {
				response.setError(true);
				response.setMessage("Unable to add Residential Property details");
				response.setResponse(null);
				return response;
			} 
		}
		// saving property images
		//TODO// To save Property Images
//		for(int i =0;i<request.getImageList().size();i++) {
//			PropertyImages pi = new PropertyImages();
//			pi.setPid(saved);
//			PropertyImages savedPi = propertyImagesRepository.save(pi);
//			
//			if (savedPi == null) {
//				message += "Oops! Few Property images upload failed.";
//			}
//		}
		
		
		Notification notification = new Notification("New Leads Added");
		notification.setResponse(request);
		this.messagingTemplate.convertAndSend("/topic/newProperty",notification);
		
		
		response.setError(false);
		response.setMessage("Added Property!" + message);
		response.setResponse(saved);

		return response;
	}

	@Override
	public Response fetchAllProperty() {
		Response response = new Response();

		List<Property> props = propertyRepository.findAll();
		List<PropertyResponse> propertyResponseList = new ArrayList<PropertyResponse>();

		if (props.size() == 0) {
			response.setError(true);
			response.setMessage("No Property Found");
			response.setResponse(null);
		} else {
			response.setError(false);
			response.setMessage("Retrieved Property!");
			response.setResponse(props);
		}

		return response;
	}

	@Override
	public Response fetchAllBuyProperty(String location) {
		Response response = new Response();
		Set<Property> propertyList = new HashSet<Property>();
		
		List<Property> props = propertyRepository.fetchAllBuyProperty(location.toLowerCase());
		System.out.println( "First : " +  props.size());
		propertyList.addAll(props);
		
		List<City> similarCity = cityRepository.fetchAllSimilarCities(location.toLowerCase());
		System.out.println("Second : " + props.size());
		for(int i=0;i<similarCity.size();i++) {
			Set<PropertyAddress> addrlist = similarCity.get(i).getPropertyList(); 
			for(PropertyAddress p : addrlist) {
				if(propertyRepository.findById(p.getPid()).get().isRent() == false) {
					propertyList.add(propertyRepository.findById(p.getPid()).get());
				}
				
			}
		}
		
		List<PropertyAddress> addressList = propertyAddresseRepository.fetchAllSimilarAddress(location.toLowerCase());
		for(PropertyAddress p : addressList) {
			if(propertyRepository.findById(p.getPid()).get().isRent() == false) {
				propertyList.add(propertyRepository.findById(p.getPid()).get());
			}
		}
		
		List<PropertyResponse> list = new ArrayList<PropertyResponse>();

		if (propertyList.size() == 0) {
			response.setError(true);
			response.setMessage("No Property Found");
			response.setResponse(null);
		} else {
			response.setError(false);
			
			for(Property p:propertyList) {
				list.add(propertyHelper.mapPropertyDetails(p));
			}
			
			response.setMessage("Retrieved Property!");
			response.setResponse(list);
		}

		return response;
	}
	
	@Override
	public Response fetchAllRentProperty(String location) {
		Response response = new Response();

		Set<Property> propertyList = new HashSet<Property>();
		
		List<Property> props = propertyRepository.fetchAllRentProperty(location.toLowerCase());
		System.out.println( "First : " +  props.size());
		propertyList.addAll(props);
		
		List<City> similarCity = cityRepository.fetchAllSimilarCities(location.toLowerCase());
		System.out.println("Second : " + props.size());
		for(int i=0;i<similarCity.size();i++) {
			Set<PropertyAddress> addrlist = similarCity.get(i).getPropertyList(); 
			for(PropertyAddress p : addrlist) {
				if(propertyRepository.findById(p.getPid()).get().isRent() == true) {
					propertyList.add(propertyRepository.findById(p.getPid()).get());
				}
				
			}
		}
		
		List<PropertyAddress> addressList = propertyAddresseRepository.fetchAllSimilarAddress(location.toLowerCase());
		for(PropertyAddress p : addressList) {
			if(propertyRepository.findById(p.getPid()).get().isRent() == true) {
				propertyList.add(propertyRepository.findById(p.getPid()).get());
			}
		}
		
		List<PropertyResponse> list = new ArrayList<PropertyResponse>();

		if (propertyList.size() == 0) {
			response.setError(true);
			response.setMessage("No Property Found");
			response.setResponse(null);
		} else {
			response.setError(false);
			
			for(Property p:propertyList) {
				list.add(propertyHelper.mapPropertyDetails(p));
			}
			
			response.setMessage("Retrieved Property!");
			response.setResponse(list);
		}

		return response;
	}

	@Override
	public Response fetchAllCommercialProperty(String location) {
		
		Response response = new Response();

		Set<Property> propertyList = new HashSet<Property>();
		
		List<Property> props = propertyRepository.findAll();
		for(int i=0;i<props.size();i++) {
			if(props.get(i).getPropertyType().getTypeCode() != "2") {
				if(!props.get(i).getPropertyTitle().toLowerCase().contains(location.toLowerCase())) {
					props.remove(props.get(i));
				}
			}
		}
		System.out.println( "First : " +  props.size());
		propertyList.addAll(props);
		
		List<City> similarCity = cityRepository.fetchAllSimilarCities(location.toLowerCase());
		System.out.println("Second : " + props.size());
		for(int i=0;i<similarCity.size();i++) {
			Set<PropertyAddress> addrlist = similarCity.get(i).getPropertyList(); 
			for(PropertyAddress p : addrlist) {
				if(propertyRepository.findById(p.getPid()).get().getPropertyType().getTypeCode() == String.valueOf(2)) {
					propertyList.add(propertyRepository.findById(p.getPid()).get());
				}
				
			}
		}
		
		List<PropertyAddress> addressList = propertyAddresseRepository.fetchAllSimilarAddress(location.toLowerCase());
		for(PropertyAddress p : addressList) {
			if(propertyRepository.findById(p.getPid()).get().getPropertyType().getTypeCode() == String.valueOf(2)) {
				propertyList.add(propertyRepository.findById(p.getPid()).get());
			}
		}
		
		List<PropertyResponse> list = new ArrayList<PropertyResponse>();

		if (propertyList.size() == 0) {
			response.setError(true);
			response.setMessage("No Property Found");
			response.setResponse(null);
		} else {
			response.setError(false);
			
			for(Property p:propertyList) {
				list.add(propertyHelper.mapPropertyDetails(p));
			}
			
			response.setMessage("Retrieved Property!");
			response.setResponse(list);
		}

		return response;
	}

	@Override
	public Response fetchAllDealerProperty(String location) {
		Response response = new Response();

		Set<Property> propertyList = new HashSet<Property>();
		
		List<Property> allprops = propertyRepository.findAll();


		List<Property> props = new ArrayList<Property>();
		
		for(int i=0; i<allprops.size();i++) {
			if(allprops.get(i).getOwner().getUserType().getTypeCode().equals("002")) {
				if(allprops.get(i).getPropertyTitle().toLowerCase().contains(location.toLowerCase())) {
					props.add(allprops.get(i));
				}
			}
		}
		System.out.println( "First : " +  props.size());
		propertyList.addAll(props);
		
		List<City> similarCity = cityRepository.fetchAllSimilarCities(location.toLowerCase());
		System.out.println("Second : " + props.size());
		for(int i=0;i<similarCity.size();i++) {
			Set<PropertyAddress> addrlist = similarCity.get(i).getPropertyList(); 
			for(PropertyAddress p : addrlist) {
				if(allprops.get(i).getOwner().getUserType().getTypeCode().equals("002")) {
					propertyList.add(propertyRepository.findById(p.getPid()).get());
				}
				
			}
		}
		
		List<PropertyAddress> addressList = propertyAddresseRepository.fetchAllSimilarAddress(location.toLowerCase());
		for(PropertyAddress p : addressList) {
			if(propertyRepository.findById(p.getPid()).get().getOwner().getUserType().getTypeCode().equals("002")) {
				propertyList.add(propertyRepository.findById(p.getPid()).get());
			}
		}
		
		List<PropertyResponse> list = new ArrayList<PropertyResponse>();

		if (propertyList.size() == 0) {
			response.setError(true);
			response.setMessage("No Property Found");
			response.setResponse(null);
		} else {
			response.setError(false);
			
			for(Property p:propertyList) {
				list.add(propertyHelper.mapPropertyDetails(p));
			}
			
			response.setMessage("Retrieved Property!");
			response.setResponse(list);
		}

		return response;
	}

	@Override
	public Response fetchAllProjectProperty(String location) {
		Response response = new Response();

		List<Property> props = propertyRepository.fetchAllProjectProperty(location.toLowerCase());
		List<PropertyResponse> list = new ArrayList<PropertyResponse>();

		if (props.size() == 0) {
			response.setError(true);
			response.setMessage("No Property Found");
			response.setResponse(null);
		} else {
			response.setError(false);
			response.setMessage("Retrieved Property!");
			
			for(int i=0;i<props.size();i++) {
				list.add(propertyHelper.mapPropertyDetails(props.get(i)));
			}
			
			response.setResponse(list);
		}

		return response;
	}

	@Override
	public Response verifyProperty(DeleteUserPropertyRequest request) {
		Response response = new Response();
		
		String adminEmail = "admin@admin.com";
		Admin ad = adminRepository.fetchAdminByEmail(adminEmail);
		if (request.getSalt().equals(ad.getUuidKey())) {

			Optional<Property> opt = propertyRepository.findById(request.getPropertyId());
			if(opt == null) {
				response.setError(true);
				response.setMessage("No such property!");
				response.setResponse(null);
				return response;
			}
			Property prop = opt.get();
			prop.setVerified(true);
			
			Property saved = propertyRepository.save(prop);
			if(saved == null) {
				response.setError(true);
				response.setMessage("Unable to verify property!");
				response.setResponse(null);			
			}else {
				response.setError(false);
				response.setMessage("Property Verified!");
				response.setResponse(saved);		
			}
		}else {
			response.setError(true);
			response.setMessage("Unauthenticated User");
			response.setResponse(null);
		}
		return response;
	}
}
