package com.roofnfloor.roofnfloorz.service;
import org.springframework.stereotype.Service;

import com.roofnfloor.roofnfloorz.request.ContactOwnerRequest;
import com.roofnfloor.roofnfloorz.request.UserContactRequest;
import com.roofnfloor.roofnfloorz.response.Response;

@Service
public interface ContactedService {

	Response userContact(UserContactRequest request);

	Response contactOwner(ContactOwnerRequest request);

}
