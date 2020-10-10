package com.roofnfloor.roofnfloorz.service;

import org.springframework.stereotype.Service;

import com.roofnfloor.roofnfloorz.request.AddPaymentRequest;
import com.roofnfloor.roofnfloorz.response.Response;
@Service
public interface PaymentService {

	Response savePayment(AddPaymentRequest request);

}
