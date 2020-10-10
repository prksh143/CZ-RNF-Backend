package com.roofnfloor.roofnfloorz.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roofnfloor.roofnfloorz.request.AddAdminRequest;
import com.roofnfloor.roofnfloorz.request.ContactOwnerRequest;
import com.roofnfloor.roofnfloorz.request.EditPackagePricingRequest;
import com.roofnfloor.roofnfloorz.request.LoginUserRequest;
import com.roofnfloor.roofnfloorz.request.UpdateAdminPasswordRequest;
import com.roofnfloor.roofnfloorz.request.UserContactRequest;
import com.roofnfloor.roofnfloorz.response.Response;
import com.roofnfloor.roofnfloorz.service.AdminService;
import com.roofnfloor.roofnfloorz.service.ContactedService;

@RestController
@RequestMapping(path="/roofnfloor/admin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {
	
	@Autowired
	ContactedService contactService;
	
	@Autowired
	AdminService adminService;

	@PostMapping(path="/addAdmin")
	public Response addAdmin(@RequestBody AddAdminRequest request) {
		return adminService.addAdmin(request);
	}
	
	@PostMapping(path="/userContact")
	public Response userContact(@RequestBody UserContactRequest request) {
		return contactService.userContact(request);
	}
	
	@PostMapping(path="/contactOwner")
	public Response contactOwner(@RequestBody ContactOwnerRequest request) {
		return contactService.contactOwner(request);
	}
	
	@PostMapping(path="/loginUser")
	public Response loginUser(@RequestBody LoginUserRequest request) throws Exception {
		return adminService.loginUser(request);
	}
	
	@PostMapping(path="/doInitialSetup")
	public Response doInitialSetup() {
		return adminService.doInitialSetup();
	}
	
	@PostMapping(path="/clearAllData")
	public Response clearAllData() {
		return adminService.clearAllData();
	}
	
	@PostMapping(path="/clearAllProperties")
	public Response clearAllProperties() {
		return adminService.clearAllProperties();
	}
	
	@PostMapping(path="/updatePassword")
	public Response updatePassword( @RequestBody UpdateAdminPasswordRequest request) throws Exception {
		return adminService.updatePassword(request);
	}
	
	@GetMapping(path="/fetchData")
	public Response fetchData() {
		return adminService.fetchData();
	}
	@PostMapping(path="/editPricingPackage")
	public Response editPricingPackage(@RequestBody EditPackagePricingRequest request) {
		return adminService.editPricingPackage(request);
	}
	
}
