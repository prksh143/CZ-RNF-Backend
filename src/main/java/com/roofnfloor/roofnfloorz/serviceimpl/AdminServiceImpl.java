package com.roofnfloor.roofnfloorz.serviceimpl;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.Base64.Decoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.roofnfloor.roofnfloorz.utility.*;
import com.roofnfloor.roofnfloorz.model.Admin;
import com.roofnfloor.roofnfloorz.model.City;
import com.roofnfloor.roofnfloorz.model.Payments;
import com.roofnfloor.roofnfloorz.model.PricingPackage;
import com.roofnfloor.roofnfloorz.model.Property;
import com.roofnfloor.roofnfloorz.model.PropertyType;
import com.roofnfloor.roofnfloorz.model.UserType;
import com.roofnfloor.roofnfloorz.repository.AdminRepository;
import com.roofnfloor.roofnfloorz.repository.CityRepository;
import com.roofnfloor.roofnfloorz.repository.ContactedRepository;
import com.roofnfloor.roofnfloorz.repository.PaymentsRepository;
import com.roofnfloor.roofnfloorz.repository.PricingPackageRepository;
import com.roofnfloor.roofnfloorz.repository.PropertyAddressRepository;
import com.roofnfloor.roofnfloorz.repository.PropertyImagesRepository;
import com.roofnfloor.roofnfloorz.repository.PropertyRepository;
import com.roofnfloor.roofnfloorz.repository.PropertyTypeRepository;
import com.roofnfloor.roofnfloorz.repository.ResidentialPropertyAmnetiesRepository;
import com.roofnfloor.roofnfloorz.repository.ResidentialPropertyDetailsRepository;
import com.roofnfloor.roofnfloorz.repository.UserRepository;
import com.roofnfloor.roofnfloorz.repository.UserTypeRepository;
import com.roofnfloor.roofnfloorz.request.AddAdminRequest;
import com.roofnfloor.roofnfloorz.request.EditPackagePricingRequest;
import com.roofnfloor.roofnfloorz.request.LoginUserRequest;
import com.roofnfloor.roofnfloorz.request.UpdateAdminPasswordRequest;
import com.roofnfloor.roofnfloorz.response.AdminDataResponse;
import com.roofnfloor.roofnfloorz.response.Response;
import com.roofnfloor.roofnfloorz.service.AdminService;
import com.roofnfloor.roofnfloorz.service.CityService;
import com.roofnfloor.roofnfloorz.service.PackageService;
import com.roofnfloor.roofnfloorz.service.PropertyService;
import com.roofnfloor.roofnfloorz.service.PropertyTypeService;
import com.roofnfloor.roofnfloorz.service.UserService;
@Service
public class AdminServiceImpl  implements AdminService{

	@Autowired
	PropertyTypeService propertyTypeService;
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	PackageService packageService;
	
	@Autowired
	UserTypeRepository userTypeRepository;
	
	@Autowired
	CityRepository cityRespository;
	
	@Autowired
	PropertyTypeRepository propertyTypeRepository;
	
	@Autowired
	PricingPackageRepository pricingPackageRepository;
	
	@Autowired
	ContactedRepository contactedRepository;
	
	@Autowired
	PaymentsRepository paymentsRepository;
	
	@Autowired
	PropertyAddressRepository propertyAddressRepository;
	
	@Autowired
	PropertyImagesRepository propertyImagesRepository;
	
	@Autowired
	PropertyRepository propertyRepository;
	
	@Autowired
	ResidentialPropertyAmnetiesRepository residentialPropertyAmnetiesRepository;
	
	@Autowired
	ResidentialPropertyDetailsRepository residentialPropertyDetailsRepository;
	
	@Autowired 
	UserRepository userRepository;
	

	private static final String UTF8 = "UTF-8";
	
	@Override
	public Response fetchData() {
		Response resp = new Response();
		
		AdminDataResponse adminDataResponse = new AdminDataResponse();
		
		//Store Counts of Users
		
		UserType normalUserType =  userTypeRepository.fetchUserTypeByCode("001");
		adminDataResponse.setNormalCount(normalUserType.getUsersList().size());
		
		UserType ownerUserType =  userTypeRepository.fetchUserTypeByCode("002");
		adminDataResponse.setOwnerCount(ownerUserType.getUsersList().size());
		
		UserType dealerUserType =  userTypeRepository.fetchUserTypeByCode("003");
		adminDataResponse.setDealerCount(dealerUserType.getUsersList().size());
		
		UserType builderUserType =  userTypeRepository.fetchUserTypeByCode("004");
		adminDataResponse.setBuilderCount(builderUserType.getUsersList().size());
		
		
		// Store Properties Count
		int resrent = 0;
		int resbuy = 0;
		PropertyType resedentialPropertyType = propertyTypeRepository.fetchPropertyTypeByCode("1");
		if(resedentialPropertyType != null) {
			for(Property p : resedentialPropertyType.getProperty()) {
				if(p.isRent() == true) {
					resrent++;
				}else {
					resbuy++;
				}
			}
		}
		
		adminDataResponse.setResedentialRentCount(resrent);
		adminDataResponse.setResedentialBuyCount(resbuy);
		int comres=0;
		int combuy=0;
		PropertyType commercialPropertyType = propertyTypeRepository.fetchPropertyTypeByCode("2");
		if(commercialPropertyType != null) {
			for(Property p : commercialPropertyType.getProperty()) {
				if(p.isRent() == true) {
					comres++;
				}else {
					combuy++;
				}
			}
		}
		
		
		
		adminDataResponse.setCommercialRentCount(comres);
		adminDataResponse.setCommercialBuyCount(combuy);
		
		
		PricingPackage ownerSilver = pricingPackageRepository.fetchPricingPackageByCode("001");
		if(ownerSilver != null) {
			adminDataResponse.setOwnerSilverPackageCount(ownerSilver.getPaymentsList().size());
		}else {
			adminDataResponse.setOwnerSilverPackageCount(0);
		}
		
		
		
		PricingPackage ownerGold= pricingPackageRepository.fetchPricingPackageByCode("002");
		if(ownerGold != null) {
			adminDataResponse.setOwnerGoldPackageCount(ownerGold.getPaymentsList().size());
		}else {
			adminDataResponse.setOwnerGoldPackageCount(0);
		}
		
		
		PricingPackage ownerPlatinum= pricingPackageRepository.fetchPricingPackageByCode("003");
		if(ownerPlatinum != null) {
			adminDataResponse.setOwnerPlatinumPackageCount(ownerPlatinum.getPaymentsList().size());
		}else {
			adminDataResponse.setOwnerPlatinumPackageCount(0);
		}
		
		
		PricingPackage dealerSilver = pricingPackageRepository.fetchPricingPackageByCode("004");
		if(dealerSilver != null) {
			adminDataResponse.setDealerSilverPackageCount(dealerSilver.getPaymentsList().size());
		}else {
			adminDataResponse.setDealerSilverPackageCount(0);
		}
		
		
		
		PricingPackage dealerGold= pricingPackageRepository.fetchPricingPackageByCode("005");
		if(dealerGold != null) {
			adminDataResponse.setDealerGoldPackageCount(dealerGold.getPaymentsList().size());
		}else {
			adminDataResponse.setDealerGoldPackageCount(0);
		}
		
		
		PricingPackage dealerPlatinum= pricingPackageRepository.fetchPricingPackageByCode("006");
		if(dealerPlatinum!= null) {
			adminDataResponse.setDealerPlatinumPackageCount(dealerPlatinum.getPaymentsList().size());
		}else {
			adminDataResponse.setDealerPlatinumPackageCount(0);
		}
		
		
		resp.setError(false);
		resp.setMessage("Data Fetched");
		resp.setResponse(adminDataResponse);
		
		return resp;
	}


	@Override
	public Response doInitialSetup() {
		Response response = new Response();
		AddAdminRequest req = new AddAdminRequest();
		req.setEmail("admin@admin.com");
		req.setPassword("admin");
		adminService.addAdmin(req);
		// Property Types and user types..
		propertyTypeService.addPropertyTypes();
		userService.addUserTypes();
		
		// cities
		cityService.addCities();
		
		// packages
		packageService.addPackages();
		
		response.setError(false);
		response.setMessage("Initial setup done");
		response.setResponse(null);
		return response;
	}


	@Override
	@Transactional
	public Response clearAllData() {
		Response response = new Response();
		cityRespository.truncateTable();
		contactedRepository.truncateTable();
		paymentsRepository.truncateTable();
		pricingPackageRepository.truncateTable();
		propertyAddressRepository.truncateTable();
		propertyImagesRepository.truncateTable();
		propertyRepository.truncateTable();
		propertyTypeRepository.truncateTable();
		residentialPropertyAmnetiesRepository.truncateTable();
		residentialPropertyDetailsRepository.truncateTable();
		userRepository.truncateTable();
		userTypeRepository.truncateTable();
		adminRepository.truncateTable();
		response.setError(false);
		response.setMessage("Data Cleared");
		response.setResponse(null);
		return response;
	}


	@Override
	public Response updatePassword( UpdateAdminPasswordRequest request) throws Exception {
		Response response = new Response();
		if(adminRepository.loginUser(request.getEmail(), decrypt(request.getOldPassword())).size()>0) {
			Admin admin = adminRepository.loginUser(request.getEmail(), decrypt(request.getOldPassword())).get(0);
			admin.setPassword(decrypt(request.getNewPassword()));
			adminRepository.save(admin);
			response.setError(false);
			response.setMessage("Password Updated!");
			response.setResponse(admin);	
		}else {
			response.setError(true);
			response.setMessage("Password Entered Is Incorrect!");
			response.setResponse(null);			
		}

		return response;
	}


	@Override
	public Response addAdmin(AddAdminRequest request) {
		Response response = new Response();
		
		Admin admin = new Admin();
		admin.setEmail(request.getEmail());
		admin.setPassword(request.getPassword());
		
		Admin saved = adminRepository.save(admin);
		if(saved == null) {
			response.setError(true);
			response.setMessage("Admin Not Added");
			response.setResponse(null);	
		}else {

			response.setError(false);
			response.setMessage("Admin added successfully!");
			response.setResponse(saved);	
		}
		return null;
	}


	@Override
	public Response loginUser(LoginUserRequest request) throws Exception {
		Response response = new Response();
		
		if(adminRepository.loginUser(request.getEmail(), decrypt(request.getPassword())).size()>0) {

			Admin admin = adminRepository.loginUser(request.getEmail(), decrypt(request.getPassword())).get(0);
			admin.setUuidKey(UUID.randomUUID().toString());
			adminRepository.save(admin);
			response.setError(false);
			response.setMessage("User Details Fetched!");
			response.setResponse(admin);	
		}else {
			response.setError(true);
			response.setMessage("Invalid credentials!");
			response.setResponse(null);			
		}

		return response;
	}


	@Override
	public Response editPricingPackage(EditPackagePricingRequest request) {
		Response response = new Response();
		String adminEmail = "admin@admin.com";
		Admin ad = adminRepository.fetchAdminByEmail(adminEmail);
		if (request.getSalt().equals(ad.getUuidKey())) {

			PricingPackage pricingPackage = pricingPackageRepository.fetchPricingPackageByCode(request.getPackageCode());
			pricingPackage.setPackageAmount(request.getPackageAmount());
			pricingPackage = pricingPackageRepository.save(pricingPackage);
			if(Objects.isNull(pricingPackage)) {
				response.setError(true);
				response.setMessage("Error Editing Package Price");
				response.setResponse(null);
			}else {
				response.setError(false);
				response.setMessage("Pricing Package Updated");
				response.setResponse(pricingPackage);
			}
		}else {
			response.setError(true);
			response.setMessage("Unauthenticated User");
			response.setResponse(null);
		}
		return response;
	}


	@Override
	@Transactional
	public Response clearAllProperties() {
		Response response = new Response();
		propertyAddressRepository.truncateTable();
		propertyImagesRepository.truncateTable();
		residentialPropertyAmnetiesRepository.truncateTable();
		residentialPropertyDetailsRepository.truncateTable();
		propertyRepository.truncateTable();
		
		response.setError(false);
		response.setMessage("Properties Cleared");
		response.setResponse(null);
		return response;
	}
	
	public String decrypt(String cipherText) throws Exception {
		 Decoder decoder = Base64.getDecoder();   
       byte[] encrypted1 = decoder.decode(cipherText);
       String psk = "0123456789012345";
       String iv = "0123456789012345";
       try {
           String encryptionKey = psk;
           final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
           final SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes(UTF8), "AES");
           cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv.getBytes(UTF8)));
           return new String(cipher.doFinal(encrypted1), UTF8);
       } catch (BadPaddingException | IllegalBlockSizeException | UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | NoSuchProviderException ex) {
       
           throw new Exception(ex.getMessage());
       }
	}

}
