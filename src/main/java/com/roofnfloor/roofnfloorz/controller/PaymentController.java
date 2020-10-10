package com.roofnfloor.roofnfloorz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roofnfloor.roofnfloorz.request.AddPaymentRequest;
import com.roofnfloor.roofnfloorz.response.Response;
import com.roofnfloor.roofnfloorz.service.PaymentService;
@RestController
@RequestMapping(path="/roofnfloor")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PaymentController {

	
	@Autowired
	PaymentService paymentService;
	
	@PostMapping(path="/savePayment")
	public Response savePayment(@RequestBody AddPaymentRequest request) {
		return paymentService.savePayment(request);
	}
	
	
}
