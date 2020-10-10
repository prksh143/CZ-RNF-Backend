package com.roofnfloor.roofnfloorz.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roofnfloor.roofnfloorz.model.UserType;
import com.roofnfloor.roofnfloorz.repository.UserTypeRepository;
import com.roofnfloor.roofnfloorz.response.Response;
import com.roofnfloor.roofnfloorz.service.UserTypeService;

@Service
public class UserTypeServiceImpl implements UserTypeService{

	@Autowired
	UserTypeRepository userTypeRepository;
	
	@Override
	public Response fetchUserTypeByCode(String code) {
		Response response = new Response();
		UserType x = userTypeRepository.fetchUserTypeByCode(code);
		if(x == null) {
			response.setError(true);
			response.setMessage("User type not found");
			response.setResponse(null);
		}else {
			response.setError(false);
			response.setMessage("User type found");
			response.setResponse(x);	
		}
		return response;
	}

}
