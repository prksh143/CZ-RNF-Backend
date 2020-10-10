package com.roofnfloor.roofnfloorz.service;

import org.springframework.stereotype.Service;

import com.roofnfloor.roofnfloorz.request.AddAdminRequest;
import com.roofnfloor.roofnfloorz.request.EditPackagePricingRequest;
import com.roofnfloor.roofnfloorz.request.LoginUserRequest;
import com.roofnfloor.roofnfloorz.request.UpdateAdminPasswordRequest;
import com.roofnfloor.roofnfloorz.response.Response;
@Service
public interface AdminService {

	Response fetchData();

	Response doInitialSetup();

	Response clearAllData();

	Response updatePassword(UpdateAdminPasswordRequest request) throws Exception;

	Response addAdmin(AddAdminRequest request);

	Response loginUser(LoginUserRequest request) throws Exception;

	Response editPricingPackage(EditPackagePricingRequest request);

	Response clearAllProperties();

}
