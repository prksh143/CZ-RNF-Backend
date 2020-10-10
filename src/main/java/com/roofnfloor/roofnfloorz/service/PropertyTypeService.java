package com.roofnfloor.roofnfloorz.service;

import org.springframework.stereotype.Service;

import com.roofnfloor.roofnfloorz.request.PropertyTypeRequest;
import com.roofnfloor.roofnfloorz.response.Response;

@Service
public interface PropertyTypeService {

	Response addPropertyType(PropertyTypeRequest request);
	
	Response addPropertyTypes();

}
