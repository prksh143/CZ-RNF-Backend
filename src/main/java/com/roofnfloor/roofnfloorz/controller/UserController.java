package com.roofnfloor.roofnfloorz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.roofnfloor.roofnfloorz.request.DeleteUserPropertyRequest;
import com.roofnfloor.roofnfloorz.request.UpdateUserPasswordRequest;
import com.roofnfloor.roofnfloorz.response.Response;
import com.roofnfloor.roofnfloorz.service.UserService;

@RestController
@RequestMapping(path="/roofnfloor")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	
	@Autowired
	UserService userService;
	
	@PostMapping(path="/addUserTypes")
	public Response addUserType() {
		return userService.addUserTypes(); 
	}
	
	@GetMapping(path="/fetchAllUsers")
	public Response fetchAllUsers() {
		return userService.fetchAllUsers(); 
	}
	
	@PostMapping(path="/fetchPropertiesOfUser")
	public Response fetchPropertiesOfUser(@RequestParam String email) {
		return userService.fetchPropertiesOfUser(email);
	}
	
	@PostMapping(path="/deleteUser")
	public Response deleteUser(@RequestParam String email, @RequestParam String salt) {
		return userService.deleteUser(email, salt);
	}
	
	@PostMapping(path="/updateUserPassword")
	public Response updateUserPassword(@RequestBody UpdateUserPasswordRequest request) throws Exception {
		return userService.updateUserPassword(request);
	}
	
	@PostMapping(path="/deleteUserProperty")
	public Response deleteUserProperty(@RequestBody DeleteUserPropertyRequest request) {
		return userService.deleteUserProperty(request);
	}
	
	
	@PostMapping(path="/getUserSubscription")
	public Response getUserSubscription(@RequestParam("userId") String userId) {
		return userService.getUserSubscription(userId);
	}
	
}
