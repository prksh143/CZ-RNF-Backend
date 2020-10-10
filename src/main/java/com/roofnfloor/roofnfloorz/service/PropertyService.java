package com.roofnfloor.roofnfloorz.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.roofnfloor.roofnfloorz.model.Property;
import com.roofnfloor.roofnfloorz.request.DeleteUserPropertyRequest;
import com.roofnfloor.roofnfloorz.request.PropertyRequest;
import com.roofnfloor.roofnfloorz.response.Response;

@Service
public interface PropertyService {

	Response addProperty(PropertyRequest request);

	Response fetchAllProperty();

	Response fetchAllBuyProperty(String location);

	Response fetchAllRentProperty(String location);

	Response fetchAllCommercialProperty(String location);

	Response fetchAllDealerProperty(String location);

	Response fetchAllProjectProperty(String location);

	Response verifyProperty(DeleteUserPropertyRequest request);
}
