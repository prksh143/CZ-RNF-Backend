package com.roofnfloor.roofnfloorz.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.roofnfloor.roofnfloorz.utility.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roofnfloor.roofnfloorz.helper.PropertyHelper;
import com.roofnfloor.roofnfloorz.model.City;
import com.roofnfloor.roofnfloorz.model.Property;
import com.roofnfloor.roofnfloorz.model.PropertyAddress;
import com.roofnfloor.roofnfloorz.repository.CityRepository;
import com.roofnfloor.roofnfloorz.repository.PropertyAddressRepository;
import com.roofnfloor.roofnfloorz.repository.PropertyRepository;
import com.roofnfloor.roofnfloorz.request.CityRequest;
import com.roofnfloor.roofnfloorz.request.DeleteCityRequest;
import com.roofnfloor.roofnfloorz.response.CityResponse;
import com.roofnfloor.roofnfloorz.response.PropertyResponse;
import com.roofnfloor.roofnfloorz.response.Response;
import com.roofnfloor.roofnfloorz.service.CityService;

@Service
public class CityServiceImpl implements CityService{

	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	PropertyRepository propertyRespository;
	
	@Autowired
	PropertyAddressRepository propertyAddressRespository;
	
	@Autowired
	PropertyHelper propertyHelper;
	
	@Override
	public Response addCity(CityRequest request) {
		Response response = new Response();
		City city = new City();
		city.setCityCode(request.getCityCode());
		city.setCityName(request.getCityName());
		city.setCityState(request.getCityState());
		city.setCityImage(Defaults.DEFAULT_CITY_IMAGE);
		
		City savedCity = cityRepository.save(city);
		if(savedCity == null) {
			response.setError(true);
			response.setMessage("Unable to save City");
			response.setResponse(null);
		}else {
			response.setError(false);
			response.setMessage("City saved");
			response.setResponse(savedCity);
		}
		return response;
	}

	@Override
	public Response fetchCityInformation(String cityCode) {
		Response response = new Response();
		CityResponse cityResponse = new CityResponse();
		List<PropertyResponse> list = new ArrayList<PropertyResponse>();
		List<PropertyResponse> allProps = new ArrayList<PropertyResponse>();
		
		if(cityCode.equals("all")) {
			List<Property> plist = propertyRespository.fetchFeaturedPropertyList();
			List<Property> alist = propertyRespository.findAll();
			cityResponse.setFeaturedPropertyList(list);
			cityResponse.setAllPropertyList(allProps);
			if(plist.size() == 0) {
				response.setError(false);
				response.setMessage("No properties found for ALL");
				response.setResponse(cityResponse);				
			}else {
				for(int i=0;i<plist.size();i++) {
					list.add(propertyHelper.mapPropertyDetails(plist.get(i)));
				}

				for(int i=0;i<alist.size();i++) {
					allProps.add(propertyHelper.mapPropertyDetails(alist.get(i)));
				}
				cityResponse.setFeaturedPropertyList(list);
				cityResponse.setAllPropertyList(allProps);
				response.setError(false);
				response.setMessage("Properties found for ALL");
				response.setResponse(cityResponse);				
			}
		}else {
			List<PropertyAddress> addresslist =propertyAddressRespository.fetchFeaturedPropertyList(cityRepository.fetchCityByCityCode(cityCode).getId());
			List<PropertyAddress> allAddresslist =propertyAddressRespository.fetchAllPropertyList(cityRepository.fetchCityByCityCode(cityCode).getId());

			System.out.println("size"+ addresslist.size());
			if(allAddresslist.size() == 0) {
				cityResponse.setFeaturedPropertyList(list);
				cityResponse.setAllPropertyList(allProps);
				response.setError(false);
				response.setMessage("No properties found for the city");
				response.setResponse(cityResponse);
			}else {
				if(addresslist.size()<=5) {
					for(int i =0; i<addresslist.size();i++) {
						Optional<Property> prop = (propertyRespository.findById(addresslist.get(i).getPid()));
						System.out.println(addresslist.get(i).getPid());
						list.add(propertyHelper.mapPropertyDetails(prop.get()));
					}
					
				}else {
					for(int i =0; i<5;i++) {
						Optional<Property> prop = (propertyRespository.findById(addresslist.get(i).getPid()));
						list.add(propertyHelper.mapPropertyDetails(prop.get()));
					}
					
				}
				
				for(int i =0; i<allAddresslist.size();i++) {
					Optional<Property> prop = (propertyRespository.findById(allAddresslist.get(i).getPid()));
					System.out.println(allAddresslist.get(i).getPid());
					allProps.add(propertyHelper.mapPropertyDetails(prop.get()));
				}
				
				cityResponse.setFeaturedPropertyList(list);
				cityResponse.setAllPropertyList(allProps);
				response.setError(false);
				response.setMessage("Properties found for the city");
				response.setResponse(cityResponse);
			}			
		}
		
		return response;
	}

	@Override
	public Response fetchAllCities() {
		Response response = new Response();
		response.setError(false);
		response.setMessage("All Cities");
		response.setResponse(cityRepository.findAll());
		return response;
	}

	@Override
	public Response updateCity(CityRequest request) {
		Response response = new Response();
		City city = cityRepository.fetchCityByCityCode(request.getCityCode());
		if(city== null) {
			response.setError(true);
			response.setMessage("No such City found");
			response.setResponse(null);
			return response;
		}
		city.setCityName(request.getCityName());
		city.setCityState(request.getCityState());
		city.setCityImage(Defaults.DEFAULT_CITY_IMAGE);
		
		City savedCity = cityRepository.save(city);
		if(savedCity == null) {
			response.setError(true);
			response.setMessage("Unable to update City");
			response.setResponse(null);
		}else {
			response.setError(false);
			response.setMessage("City updated");
			response.setResponse(savedCity);
		}
		return response;
	}

	@Override
	public Response deleteCity(DeleteCityRequest request) {
		Response response = new Response();
		cityRepository.deleteCityByCityCode(request.getCityCode());
		response.setError(false);
		response.setMessage("City deleted");
		response.setResponse(null);
		return response;
	}

	@Override
	public Response addCities() {
		Response response = new Response();

		for(int i=0; i<Defaults.CITY_NAME.length; i++) {
			City city = new City();
			city.setCityCode(Defaults.CITY_CODE[i]);
			city.setCityName(Defaults.CITY_NAME[i]);
			city.setCityState(Defaults.CITY_STATE[i]);
			city.setCityImage(Defaults.SERVER_URL+"roofnfloor/city/images/"+Defaults.CITY_IMAGE[i]);
			cityRepository.save(city);
		}
		
		return response;
	}

}
