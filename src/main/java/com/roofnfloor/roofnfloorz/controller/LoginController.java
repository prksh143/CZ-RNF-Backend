package com.roofnfloor.roofnfloorz.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.activation.FileTypeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.roofnfloor.roofnfloorz.request.LoginUserByMobileRequest;
import com.roofnfloor.roofnfloorz.request.LoginUserRequest;
import com.roofnfloor.roofnfloorz.request.RegisterUserRequest;
import com.roofnfloor.roofnfloorz.response.Response;
import com.roofnfloor.roofnfloorz.service.UserService;

@RestController
@RequestMapping(path="/roofnfloor")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {

	@Autowired
	UserService userService;
	
	@PostMapping(path="/registerUser")
	public Response registerUser(@RequestBody RegisterUserRequest request) throws Exception {
		return userService.registerUser(request);
	}
	
	@PostMapping(path="/loginUser")
	public Response loginUser(@RequestBody LoginUserRequest request) throws Exception {
		return userService.loginUser(request);
	}
	
	@PostMapping(path="/loginUserByMobile")
	public Response loginUserByMobile(@RequestBody LoginUserByMobileRequest request) {
		return userService.loginUserByMobile(request);
	}
	
}
