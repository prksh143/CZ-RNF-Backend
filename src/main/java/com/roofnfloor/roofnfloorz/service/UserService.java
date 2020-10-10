package com.roofnfloor.roofnfloorz.service;

import org.springframework.stereotype.Service;

import com.roofnfloor.roofnfloorz.request.DeleteUserPropertyRequest;
import com.roofnfloor.roofnfloorz.request.LoginUserByMobileRequest;
import com.roofnfloor.roofnfloorz.request.LoginUserRequest;
import com.roofnfloor.roofnfloorz.request.RegisterUserRequest;
import com.roofnfloor.roofnfloorz.request.UpdateUserPasswordRequest;
import com.roofnfloor.roofnfloorz.response.Response;

@Service
public interface UserService {

	Response registerUser(RegisterUserRequest request) throws Exception;

	Response loginUser(LoginUserRequest request) throws Exception;

	Response loginUserByMobile(LoginUserByMobileRequest request);

	Response addUserTypes();

	Response fetchPropertiesOfUser(String email);

	Response updateUserPassword(UpdateUserPasswordRequest request) throws Exception;

	Response deleteUserProperty(DeleteUserPropertyRequest request);

	Response getUserSubscription(String userId);

	Response fetchAllUsers();

	Response deleteUser(String email, String salt);

}
