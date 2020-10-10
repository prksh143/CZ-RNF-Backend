package com.roofnfloor.roofnfloorz.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.roofnfloor.roofnfloorz.model.Notification;
import com.roofnfloor.roofnfloorz.model.Payments;
import com.roofnfloor.roofnfloorz.model.PricingPackage;
import com.roofnfloor.roofnfloorz.model.User;
import com.roofnfloor.roofnfloorz.model.UserType;
import com.roofnfloor.roofnfloorz.repository.PaymentsRepository;
import com.roofnfloor.roofnfloorz.repository.PricingPackageRepository;
import com.roofnfloor.roofnfloorz.repository.UserRepository;
import com.roofnfloor.roofnfloorz.repository.UserTypeRepository;
import com.roofnfloor.roofnfloorz.request.AddPaymentRequest;
import com.roofnfloor.roofnfloorz.response.Response;
import com.roofnfloor.roofnfloorz.service.PaymentService;
@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PaymentsRepository paymentRepository;
	
	@Autowired
	PricingPackageRepository pricingPackageRepository;
	
	@Autowired
	UserTypeRepository userTypeRepository;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@Override
	public Response savePayment(AddPaymentRequest request) {
		Response resp  = new Response();
		User user = userRepository.findById(Long.parseLong(request.getUserId())).get();
		
		
		String packageCode = request.getPackageCode();
		Payments payment = new Payments();
		if(user == null) {
			resp.setError(true);
			resp.setMessage("User Do Not Exists");
		}else {

			if(!user.getUuidKey().equals(request.getSalt())) {
				resp.setError(true);
				resp.setMessage("Unauthenticated User");
				resp.setResponse(null);
				return resp;
			}
			
			PricingPackage pp = pricingPackageRepository.fetchPricingPackageByCode(packageCode);
			
			payment.setOwner(user);
			payment.setPackageCode(request.getPackageCode());
			payment.setStripeToken(request.getStripeToken());
			payment.setPricingPackage(pricingPackageRepository.fetchPricingPackageByCode(request.getPackageCode()));
			
			payment = paymentRepository.save(payment);
			user.setUserType(pp.getUserType());
			user = userRepository.save(user);
			if(payment == null) {
				resp.setError(true);
				resp.setMessage("Payment Not Done");
			}else {
				resp.setError(false);
				resp.setMessage("Payment Successfull");
				resp.setResponse(payment);
			}
		}

		Notification notification = new Notification("New Subscription");
		notification.setResponse(packageCode);
		this.messagingTemplate.convertAndSend("/topic/newSubscription",notification);
		
		return resp;
	}

}
