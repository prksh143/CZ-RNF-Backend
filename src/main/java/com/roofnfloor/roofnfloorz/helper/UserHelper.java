package com.roofnfloor.roofnfloorz.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roofnfloor.roofnfloorz.model.User;
import com.roofnfloor.roofnfloorz.repository.UserRepository;
import com.roofnfloor.roofnfloorz.response.UserResponseForProperty;

@Component
public class UserHelper {

	@Autowired
	UserRepository userRepository;
	
	public UserResponseForProperty mapUserDetailsForProperty(User user) {
		
		UserResponseForProperty userResp = new UserResponseForProperty();
		userResp.setEmail(user.getEmail());
		userResp.setMobile(user.getMobile());
		userResp.setName(user.getName());
//		userResp.setUserType(user.getUserType());
		userResp.setIsEmailVerified(user.isEmailVerified());
		userResp.setIsMobileVerified(user.isMobileVerified());
		
		return userResp;
	}
}
