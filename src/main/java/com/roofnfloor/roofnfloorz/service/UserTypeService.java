package com.roofnfloor.roofnfloorz.service;

import org.springframework.stereotype.Service;

import com.roofnfloor.roofnfloorz.response.Response;

@Service
public interface UserTypeService {

	Response fetchUserTypeByCode(String code);
}
