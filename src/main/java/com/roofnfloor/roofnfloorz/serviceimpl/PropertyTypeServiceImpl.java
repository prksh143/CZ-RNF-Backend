package com.roofnfloor.roofnfloorz.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roofnfloor.roofnfloorz.model.PropertyType;
import com.roofnfloor.roofnfloorz.repository.PropertyTypeRepository;
import com.roofnfloor.roofnfloorz.request.PropertyTypeRequest;
import com.roofnfloor.roofnfloorz.response.Response;
import com.roofnfloor.roofnfloorz.service.PropertyTypeService;
import com.roofnfloor.roofnfloorz.utility.DateUtils;

@Service
public class PropertyTypeServiceImpl implements PropertyTypeService{

	@Autowired
	DateUtils dateUtils;
	
	@Autowired
	PropertyTypeRepository propertyTypeRepository;
	
	@Override
	public Response addPropertyType(PropertyTypeRequest request) {
		Response response = new Response();
		
		PropertyType pt = new PropertyType();
		pt.setIsCommercial(request.getIsCommercial());
		pt.setCreatedAt(dateUtils.getCurrentDate());
		pt.setModifiedAt(null);
		pt.setIsProject(request.getIsProject());
		pt.setIsResidential(request.getIsResidential());
		pt.setTitle(request.getTitle());
		pt.setTypeCode(request.getTypeCode());
		
		PropertyType saved = propertyTypeRepository.save(pt);
		if(saved == null) {
			response.setError(true);
			response.setMessage("Unable to add Property Type");
			response.setResponse(null);			
		}else {
			response.setError(false);
			response.setMessage("Added Property Type");
			response.setResponse(saved);
		}
		
		return response;
	}

	@Override
	public Response addPropertyTypes() {
		Response response = new Response();
		
		PropertyType pt = new PropertyType();
		pt.setTitle("Residential");
		pt.setTypeCode("1");
		pt.setIsCommercial(false);
		pt.setCreatedAt(dateUtils.getCurrentDate());
		pt.setIsProject(false);
		pt.setIsResidential(true);

		propertyTypeRepository.save(pt);
		PropertyType pt1 = new PropertyType();
		pt1.setTitle("Commercial");
		pt1.setTypeCode("2");
		pt1.setIsCommercial(true);
		pt1.setCreatedAt(dateUtils.getCurrentDate());
		pt1.setIsProject(false);
		pt1.setIsResidential(false);
		
		propertyTypeRepository.save(pt1);
		
		return response;
	}

}
