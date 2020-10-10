package com.roofnfloor.roofnfloorz.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roofnfloor.roofnfloorz.model.PricingPackage;
import com.roofnfloor.roofnfloorz.model.UserType;
import com.roofnfloor.roofnfloorz.repository.PricingPackageRepository;
import com.roofnfloor.roofnfloorz.repository.UserTypeRepository;
import com.roofnfloor.roofnfloorz.request.AddPackageRequest;
import com.roofnfloor.roofnfloorz.request.DeletePackageRequest;
import com.roofnfloor.roofnfloorz.response.Response;
import com.roofnfloor.roofnfloorz.service.PackageService;
@Service
public class PackageServiceImpl implements PackageService {

	@Autowired
	UserTypeRepository userTypeRepository;
	
	@Autowired
	PricingPackageRepository pricingPackageRepository;
	
	@Override
	public Response addPackage(AddPackageRequest request) {
		Response resp = new Response();
		UserType userType = userTypeRepository.fetchUserTypeByCode(request.getUserTypeCode());
		if(userType == null) {
			resp.setError(true);
			resp.setMessage("User Type do Not Exists");
		}else {
			PricingPackage pricingPackage = new PricingPackage();
			pricingPackage.setPackageAmount(request.getPackageAmount());
			pricingPackage.setPackageCode(request.getPackageCode());
			pricingPackage.setPackageName(request.getPackageName());
			pricingPackage.setUserType(userType);
			pricingPackage = pricingPackageRepository.save(pricingPackage);
			if(pricingPackage == null) {
				resp.setError(true);
				resp.setMessage("Package Not Saved");
			}else {
				resp.setError(false);
				resp.setMessage("Package Saved");
				resp.setResponse(pricingPackage);
			}
			
			
		}
		return resp;
	}

	@Override
	public Response updatePackage(AddPackageRequest request) {
		Response resp = new Response();
		UserType userType = userTypeRepository.fetchUserTypeByCode(request.getUserTypeCode());
		if(userType == null) {
			resp.setError(true);
			resp.setMessage("User Type do Not Exists");
		}else {
			PricingPackage pricingPackage = pricingPackageRepository.fetchPricingPackageByCode(request.getPackageCode());
			pricingPackage.setPackageAmount(request.getPackageAmount());
			pricingPackage.setPackageName(request.getPackageName());
			pricingPackage.setUserType(userType);
			pricingPackage = pricingPackageRepository.save(pricingPackage);
			if(pricingPackage == null) {
				resp.setError(true);
				resp.setMessage("Package Not Updated");
			}else {
				resp.setError(false);
				resp.setMessage("Package Updated");
				resp.setResponse(pricingPackage);
			}
			
			
		}
		return resp;
	}

	@Override
	public Response fetchAllPackage() {
		Response response = new Response();
		response.setError(false);
		response.setMessage("All Packages");
		response.setResponse(pricingPackageRepository.findAll());
		return response;
	}

	@Override
	public Response deletePackage(DeletePackageRequest request) {
		Response response = new Response();
		pricingPackageRepository.deletePackageByCode(request.getPackageCode());
		response.setError(false);
		response.setMessage("Package deleted");
		response.setResponse(null);
		return null;
	}

	@Override
	public Response addPackages() {
		Response response = new Response();
		
		PricingPackage pricingPackage = new PricingPackage();
		PricingPackage pricingPackage1 = new PricingPackage();
		PricingPackage pricingPackage2 = new PricingPackage();
		PricingPackage pricingPackage3 = new PricingPackage();
		PricingPackage pricingPackage4 = new PricingPackage();
		PricingPackage pricingPackage5 = new PricingPackage();
		
		pricingPackage.setPackageAmount(500);
		pricingPackage.setPackageCode("001");
		pricingPackage.setPackageName("Silver Package");
		pricingPackage.setUserType(userTypeRepository.fetchUserTypeByCode("002"));
		pricingPackage = pricingPackageRepository.save(pricingPackage);

		pricingPackage1.setPackageAmount(1000);
		pricingPackage1.setPackageCode("002");
		pricingPackage1.setPackageName("Gold Package");
		pricingPackage1.setUserType(userTypeRepository.fetchUserTypeByCode("002"));
		pricingPackage1 = pricingPackageRepository.save(pricingPackage1);
		

		pricingPackage2.setPackageAmount(5000);
		pricingPackage2.setPackageCode("003");
		pricingPackage2.setPackageName("Platinum Package");
		pricingPackage2.setUserType(userTypeRepository.fetchUserTypeByCode("002"));
		pricingPackage2 = pricingPackageRepository.save(pricingPackage2);
		

		pricingPackage3.setPackageAmount(500);
		pricingPackage3.setPackageCode("004");
		pricingPackage3.setPackageName("Silver Package");
		pricingPackage3.setUserType(userTypeRepository.fetchUserTypeByCode("003"));
		pricingPackage3 = pricingPackageRepository.save(pricingPackage3);
		

		pricingPackage4.setPackageAmount(1000);
		pricingPackage4.setPackageCode("005");
		pricingPackage4.setPackageName("Gold Package");
		pricingPackage4.setUserType(userTypeRepository.fetchUserTypeByCode("003"));
		pricingPackage4 = pricingPackageRepository.save(pricingPackage4);
		

		pricingPackage5.setPackageAmount(5000);
		pricingPackage5.setPackageCode("006");
		pricingPackage5.setPackageName("Platinum Package");
		pricingPackage5.setUserType(userTypeRepository.fetchUserTypeByCode("003"));
		pricingPackage5 = pricingPackageRepository.save(pricingPackage5);
		
		response.setError(false);
		response.setMessage("Packages added");
		response.setResponse(null);
		return response;
	}

}
