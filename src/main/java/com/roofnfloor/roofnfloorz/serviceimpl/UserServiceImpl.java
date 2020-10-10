package com.roofnfloor.roofnfloorz.serviceimpl;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.Base64.Decoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.roofnfloor.roofnfloorz.helper.PropertyHelper;
import com.roofnfloor.roofnfloorz.model.Admin;
import com.roofnfloor.roofnfloorz.model.Notification;
import com.roofnfloor.roofnfloorz.model.Payments;
import com.roofnfloor.roofnfloorz.model.PricingPackage;
import com.roofnfloor.roofnfloorz.model.Property;
import com.roofnfloor.roofnfloorz.model.PropertyAddress;
import com.roofnfloor.roofnfloorz.model.PropertyImages;
import com.roofnfloor.roofnfloorz.model.ResidentialPropertyAmneties;
import com.roofnfloor.roofnfloorz.model.ResidentialPropertyDetails;
import com.roofnfloor.roofnfloorz.model.User;
import com.roofnfloor.roofnfloorz.model.UserType;
import com.roofnfloor.roofnfloorz.repository.AdminRepository;
import com.roofnfloor.roofnfloorz.repository.PropertyAddressRepository;
import com.roofnfloor.roofnfloorz.repository.PropertyImagesRepository;
import com.roofnfloor.roofnfloorz.repository.PropertyRepository;
import com.roofnfloor.roofnfloorz.repository.ResidentialPropertyAmnetiesRepository;
import com.roofnfloor.roofnfloorz.repository.ResidentialPropertyDetailsRepository;
import com.roofnfloor.roofnfloorz.repository.UserRepository;
import com.roofnfloor.roofnfloorz.repository.UserTypeRepository;
import com.roofnfloor.roofnfloorz.request.DeleteUserPropertyRequest;
import com.roofnfloor.roofnfloorz.request.LoginUserByMobileRequest;
import com.roofnfloor.roofnfloorz.request.LoginUserRequest;
import com.roofnfloor.roofnfloorz.request.RegisterUserRequest;
import com.roofnfloor.roofnfloorz.request.UpdateUserPasswordRequest;
import com.roofnfloor.roofnfloorz.response.PropertyResponse;
import com.roofnfloor.roofnfloorz.response.Response;
import com.roofnfloor.roofnfloorz.service.UserService;
import com.roofnfloor.roofnfloorz.utility.DateUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	DateUtils dateUtils;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserTypeRepository userTypeRepository;

	@Autowired
	PropertyRepository propertyRepository;

	@Autowired
	PropertyImagesRepository propertyImagesRepository;

	@Autowired
	PropertyAddressRepository propertyAddressRepository;

	@Autowired
	ResidentialPropertyAmnetiesRepository residentialPropertyAmnetiesRepository;

	@Autowired
	ResidentialPropertyDetailsRepository residentialPropertyDetailsRepository;

	@Autowired
	PropertyHelper propertyHelper;

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	private static final String UTF8 = "UTF-8";

	@Override
	public Response registerUser(RegisterUserRequest request) throws Exception {
		Response response = new Response();
		User user = new User();

		User user1 = userRepository.fetchUserByEmail(request.getEmail());
		if (user1 != null) {
			System.out.println(3);
			response.setError(true);
			response.setMessage("Email already registered, User not registered");
			response.setResponse(null);
			return response;
		}

		User user2 = userRepository.fetchUserByMobile(request.getMobile());
		if (user2 != null) {
			response.setError(true);
			response.setMessage("Phone number already registered, User not registered");
			response.setResponse(null);

			return response;
		}

		user.setCreatedAt(dateUtils.getCurrentDate());
		user.setEmail(request.getEmail());
		user.setPassword(decrypt(request.getPassword()));
		user.setMobile(request.getMobile());
		user.setName(request.getName());
		user.setMobileVerified(true);
		user.setEmailVerified(false);
		System.out.println(request.getUserTypeCode());
		System.out.println(userTypeRepository.fetchUserTypeByCode(request.getUserTypeCode()));
		user.setUserType(userTypeRepository.fetchUserTypeByCode(request.getUserTypeCode()));

		User saved = userRepository.save(user);
		if (saved == null) {
			response.setError(true);
			response.setMessage("User not registered");
			response.setResponse(null);
		} else {
			response.setError(false);
			response.setMessage("User registered");
			response.setResponse(saved);
		}

		Notification notification = new Notification("New User Added");
		this.messagingTemplate.convertAndSend("/topic/newUser", notification);

		return response;
	}

	@Override
	public Response loginUser(LoginUserRequest request) throws Exception {
		Response response = new Response();
		User user = userRepository.loginUser(request.getEmail(), decrypt(request.getPassword()));
		if (user == null) {
			response.setError(true);
			response.setMessage("Login Unsuccessful, INVALID credentials");
			response.setResponse(null);
		} else {
			user.setUuidKey(UUID.randomUUID().toString());
			userRepository.save(user);
			response.setError(false);
			response.setMessage("Welcome to RnF, Login Successful");
			response.setResponse(user);
		}
		Notification notification = new Notification("New Leads Added");
		this.messagingTemplate.convertAndSend("/topic/newProperty", notification);
		return response;
	}

	@Override
	public Response loginUserByMobile(LoginUserByMobileRequest request) {
		Response response = new Response();
		User user = userRepository.fetchUserByMobile(request.getMobile());

		if (user == null) {
			response.setError(true);
			response.setMessage("Login Unsuccessful, INVALID credentials");
			response.setResponse(null);
		} else {
			response.setError(false);
			response.setMessage("Welcome to RnF, Login Successful");
			response.setResponse(user);
		}
		return response;
	}

	@Override
	public Response addUserTypes() {
		Response resp = new Response();
		// Normal User
		UserType normalUserType = new UserType();
		normalUserType.setCreatedAt(dateUtils.getCurrentDate());
		normalUserType.setTitle("normal");
		normalUserType.setTypeCode("001");
		normalUserType = userTypeRepository.save(normalUserType);

		// Owner
		UserType ownerUserType = new UserType();
		ownerUserType.setCreatedAt(dateUtils.getCurrentDate());
		ownerUserType.setTitle("owner");
		ownerUserType.setTypeCode("002");
		ownerUserType = userTypeRepository.save(ownerUserType);

		// Dealer
		UserType dealerUserType = new UserType();
		dealerUserType.setCreatedAt(dateUtils.getCurrentDate());
		dealerUserType.setTitle("dealer");
		dealerUserType.setTypeCode("003");
		dealerUserType = userTypeRepository.save(dealerUserType);

		// builder
		UserType builderUserType = new UserType();
		builderUserType.setCreatedAt(dateUtils.getCurrentDate());
		builderUserType.setTitle("builder");
		builderUserType.setTypeCode("004");
		builderUserType = userTypeRepository.save(builderUserType);

		UserType adminUserType = new UserType();
		adminUserType.setCreatedAt(dateUtils.getCurrentDate());
		adminUserType.setTitle("admin");
		adminUserType.setTypeCode("000");
		adminUserType = userTypeRepository.save(adminUserType);

		if (normalUserType == null || ownerUserType == null || dealerUserType == null || builderUserType == null
				|| adminUserType == null) {
			resp.setError(true);
			resp.setMessage("User Type Not Set");
			resp.setResponse(null);
		} else {
			resp.setError(false);
			resp.setMessage("User Types are Set");
			ArrayList<Object> list = new ArrayList<>();
			list.add(normalUserType);
			list.add(ownerUserType);
			list.add(dealerUserType);
			list.add(builderUserType);
			resp.setResponse(list);
		}

		return resp;
	}

	@Override
	public Response fetchPropertiesOfUser(String email) {
		Response resp = new Response();

		User user = userRepository.fetchUserByEmail(email);

		if (user == null) {
			resp.setError(true);
			resp.setMessage("No User Found");
			resp.setResponse(null);
		} else {
			resp.setError(false);
			resp.setMessage("User Found");
			Set<Property> props = user.getAdPropertieslist();
			List<PropertyResponse> list = new ArrayList<PropertyResponse>();
			for (Property p : props) {
				list.add(propertyHelper.mapPropertyDetails(p));
			}
			resp.setResponse(list);
		}

		return resp;
	}

	@Override
	public Response updateUserPassword(UpdateUserPasswordRequest request) throws Exception {
		Response resp = new Response();
		User user = userRepository.fetchUserByEmail(request.getUserEmail());
		if (user == null) {
			resp.setError(true);
			resp.setMessage("No User Found");
			resp.setResponse(null);
		} else {
			if (user.getPassword().equals(decrypt(request.getOldPassword()))) {
				user.setPassword(decrypt(request.getNewPassword()));
				userRepository.save(user);
				resp.setError(false);
				resp.setMessage("User Password Updated Successfully!");
			} else {
				resp.setError(true);
				resp.setMessage("Invalid Password Entered!");
				resp.setResponse(null);
			}

		}
		return resp;
	}

	@Override
	public Response deleteUserProperty(DeleteUserPropertyRequest request) {
		Response resp = new Response();
		
		boolean authorised = false;
		if(request.getEmail().equals("admin@admin.com")) {
			Admin ad = adminRepository.fetchAdminByEmail(request.getEmail());
			if (request.getSalt().equals(ad.getUuidKey())) {
				authorised = true;
			}
		}else {
			User us = userRepository.fetchUserByEmail(request.getEmail());
			if (request.getSalt().equals(us.getUuidKey())) {
				authorised = true;
			}
		}
		
			
		if(authorised)
		{
			List<PropertyImages> imgs = propertyImagesRepository.fetchImagesByPropertyId(request.getPropertyId());
			if (imgs.size() > 0) {
				for (int i = 0; i < imgs.size(); i++) {
					propertyImagesRepository.deleteById(imgs.get(i).getId());
				}

//				propertyImagesRepository.deletePropertyImages(request.getPropertyId());
			}
			List<ResidentialPropertyAmneties> amneties = residentialPropertyAmnetiesRepository
					.fetchAmnetiesByPropertyId(request.getPropertyId());
			System.out.println(amneties);
			if (amneties.size() > 0) {
				for (int i = 0; i < amneties.size(); i++) {
					residentialPropertyAmnetiesRepository.deleteById(amneties.get(i).getId());
				}
			}

			List<ResidentialPropertyDetails> details = residentialPropertyDetailsRepository
					.fetchDetailsByPropertyId(request.getPropertyId());
			if (details.size() > 0) {
				for (int i = 0; i < details.size(); i++) {
					residentialPropertyDetailsRepository.deleteById(details.get(i).getId());
				}
			}
			List<PropertyAddress> addr = propertyAddressRepository.fetchAddressByPropertyId(request.getPropertyId());
			if (addr.size() > 0) {
				for (int i = 0; i < addr.size(); i++) {
					propertyAddressRepository.deleteById(addr.get(i).getId());
				}
			}
			propertyRepository.deleteById(request.getPropertyId());
			resp.setError(false);
			resp.setMessage("Property Deleted Successfully!");
			resp.setResponse(null);
	}else {
			resp.setError(true);
			resp.setMessage("Unauthenticated User");
			resp.setResponse(null);
		}
		
		return resp;
	}

	@Override
	public Response getUserSubscription(String userId) {
		Response resp = new Response();
		User user = userRepository.findById(Long.parseLong(userId)).get();
		Set<Payments> paymentsList = user.getPaymentsList();
		if (paymentsList.size() == 0) {
			resp.setError(true);
			resp.setMessage("No Payments");
			resp.setResponse(null);
		} else {
			Payments newPay = null;
			for (Payments p : paymentsList) {
				newPay = p;
				break;
			}
			PricingPackage pricingPackage = newPay.getPricingPackage();
			resp.setError(false);
			resp.setMessage("Package Found");
			resp.setResponse(pricingPackage);
		}

		return resp;
	}

	@Override
	public Response fetchAllUsers() {
		Response response = new Response();
		List<User> user = userRepository.findAll();

		if (user.size() == 0) {
			response.setError(true);
			response.setMessage("No Users Found");
			response.setResponse(null);
		} else {
			response.setError(false);
			response.setMessage("Users fetched Successfully!");
			response.setResponse(user);
		}
		return response;
	}

	@Override
	public Response deleteUser(String email, String salt) {

		Response response = new Response();

		String adminEmail = "admin@admin.com";
		Admin ad = adminRepository.fetchAdminByEmail(adminEmail);
		if (salt.equals(ad.getUuidKey())) {

			User user = userRepository.fetchUserByEmail(email);

			if (user == null) {
				response.setError(true);
				response.setMessage("No User Found!");
				response.setResponse(null);
			} else {

				Set<Property> propertyList = user.getAdPropertieslist();

				for (Property p : propertyList) {
					DeleteUserPropertyRequest dlr = new DeleteUserPropertyRequest();
					dlr.setPropertyId(p.getId());

					deleteUserProperty(dlr);
				}
				userRepository.deleteById(user.getId());
				response.setError(false);
				response.setMessage("User deleted Successfully!");
				response.setResponse(user);
			}
		} else {
			response.setError(true);
			response.setMessage("Unauthenticated User");
			response.setResponse(null);
		}

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
		} catch (BadPaddingException | IllegalBlockSizeException | UnsupportedEncodingException
				| InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException
				| NoSuchPaddingException | NoSuchProviderException ex) {

			throw new Exception(ex.getMessage());
		}
	}

}
