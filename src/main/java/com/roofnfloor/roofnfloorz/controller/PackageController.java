package com.roofnfloor.roofnfloorz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roofnfloor.roofnfloorz.request.AddPackageRequest;
import com.roofnfloor.roofnfloorz.request.DeletePackageRequest;
import com.roofnfloor.roofnfloorz.response.Response;
import com.roofnfloor.roofnfloorz.service.PackageService;

@RestController
@RequestMapping(path="/roofnfloor")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PackageController {

	@Autowired
	PackageService packageService;
	
	@PostMapping(path="/addPackage")
	public Response addPackage(@RequestBody AddPackageRequest request) {
		return packageService.addPackage(request);
	}
	
	@PostMapping(path="/updatePackage")
	public Response updatePackage(@RequestBody AddPackageRequest request) {
		return packageService.updatePackage(request);
	}
	
	@GetMapping(path="/fetchAllPackage")
	public Response fetchAllPackage() {
		return packageService.fetchAllPackage();
	}
	
	@DeleteMapping(path="/deletePackage")
	public Response deletePackage(@RequestBody DeletePackageRequest request) {
		return packageService.deletePackage(request);
	}
}
