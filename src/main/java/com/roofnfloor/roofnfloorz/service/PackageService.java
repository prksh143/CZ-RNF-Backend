package com.roofnfloor.roofnfloorz.service;

import org.springframework.stereotype.Service;

import com.roofnfloor.roofnfloorz.request.AddPackageRequest;
import com.roofnfloor.roofnfloorz.request.DeletePackageRequest;
import com.roofnfloor.roofnfloorz.response.Response;
@Service
public interface PackageService {

	Response addPackage(AddPackageRequest request);

	Response updatePackage(AddPackageRequest request);

	Response fetchAllPackage();

	Response deletePackage(DeletePackageRequest request);

	Response addPackages();
}
