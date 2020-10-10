package com.roofnfloor.roofnfloorz.serviceimpl;

import java.util.Optional;

import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.roofnfloor.roofnfloorz.model.Contacted;
import com.roofnfloor.roofnfloorz.model.Property;
import com.roofnfloor.roofnfloorz.model.User;
import com.roofnfloor.roofnfloorz.repository.ContactedRepository;
import com.roofnfloor.roofnfloorz.repository.PropertyRepository;
import com.roofnfloor.roofnfloorz.request.ContactOwnerRequest;
import com.roofnfloor.roofnfloorz.request.UserContactRequest;
import com.roofnfloor.roofnfloorz.response.Response;
import com.roofnfloor.roofnfloorz.service.ContactedService;
import com.roofnfloor.roofnfloorz.utility.DateUtils;

@Service
public class ContactedServiceImpl implements ContactedService{

	@Autowired
	ContactedRepository contactedRepository;
	
	@Autowired
	DateUtils dateUtils;
	
	@Autowired
	PropertyRepository propertyRepository;
	
	@Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SimpleMailMessage preConfiguredMessage;
	
	@Override
	public Response userContact(UserContactRequest request) {
		Response response = new Response();
		Contacted contacted = new Contacted();
		contacted.setName(request.getName());
		contacted.setEmail(request.getEmail());
		contacted.setCreatedAt(dateUtils.getCurrentDate());
		contacted.setMessage(request.getMessage());
		contacted.setMobile(request.getMobile());
		contacted.setIsQuickContact(request.getIsQuickContact());
		
		Contacted saved = contactedRepository.save(contacted);
		
		String msg = "";
		if(request.getMessage() == "") {
			msg =" No Message ";
		}else {
			msg=request.getMessage();
		}
		sendMail("raman@codingzap.com", "You have been contacted", "You have been contacted by "+ request.getName()+ ", email : "+request.getEmail()+"Message : "+msg );
		 
		
		if(saved == null) {
			response.setError(true);
			response.setMessage("Oops, Your message could not be sent!");
			response.setResponse(null);
		}else {
			response.setError(false);
			response.setMessage("Your message is sent successfully!");
			response.setResponse(saved);	
		}
		return response;
	}

	@Override
	public Response contactOwner(ContactOwnerRequest request) {
		Response response = new Response();
		
		Optional<Property> prop = propertyRepository.findById(request.getPropertyId());
		Property property = prop.get();
		if(property == null) {
			response.setError(true);
			response.setMessage("Couldn't contact owner, try again!");
			response.setResponse(null);
			return response;
		}
		
		Contacted contacted = new Contacted();
		contacted.setName(request.getName());
		contacted.setEmail(request.getEmail());
		contacted.setCreatedAt(dateUtils.getCurrentDate());
		contacted.setMessage("");
		contacted.setMobile("");
		contacted.setIsQuickContact(2);
		
		 User owner = property.getOwner();
		 // notify owner via email or mobile
		 
		 sendMail(owner.getEmail(), "Viewer Interested In your property", "You have been contacted by "+ request.getName()+" \n Email"+ request.getEmail() );
		 
		 
		
		Contacted saved = contactedRepository.save(contacted);
		if(saved == null) {
			response.setError(true);
			response.setMessage("Couldn't contact owner, try again!");
			response.setResponse(null);
		}else {
			response.setError(false);
			response.setMessage("Owner has been Notified successfully!");
			response.setResponse(saved);	
		}
		return response;
	}
	 public void sendMail(String to, String subject, String body) 
	    {
	    	try {
	    		SimpleMailMessage message = new SimpleMailMessage();
	            message.setTo(to);
	            message.setSubject(subject);
	            message.setText(body);
	            message.setFrom((new InternetAddress("raman.10102@gmail.com", "Roofs and floors")).toString());
	            mailSender.send(message);
	    	}catch(Exception e) {
	    	}
	    }
}
