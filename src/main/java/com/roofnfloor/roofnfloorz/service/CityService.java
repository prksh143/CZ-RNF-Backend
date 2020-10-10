package com.roofnfloor.roofnfloorz.service;

import org.springframework.stereotype.Service;

import com.roofnfloor.roofnfloorz.request.CityRequest;
import com.roofnfloor.roofnfloorz.request.DeleteCityRequest;
import com.roofnfloor.roofnfloorz.response.Response;

@Service
public interface CityService {

	Response addCity(CityRequest request);

	Response addCities();

	Response fetchCityInformation(String cityCode);

	Response fetchAllCities();

	Response updateCity(CityRequest request);

	Response deleteCity(DeleteCityRequest request);

}
