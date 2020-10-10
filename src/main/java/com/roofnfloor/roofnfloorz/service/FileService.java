package com.roofnfloor.roofnfloorz.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.roofnfloor.roofnfloorz.response.Response;
@Service
public interface FileService {

	Response uploadFile(MultipartFile[] file, Long propertyId);

}
