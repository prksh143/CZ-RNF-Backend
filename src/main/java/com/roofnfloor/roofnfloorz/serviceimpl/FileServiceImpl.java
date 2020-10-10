package com.roofnfloor.roofnfloorz.serviceimpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.roofnfloor.roofnfloorz.exception.FileStorageException;
import com.roofnfloor.roofnfloorz.model.Property;
import com.roofnfloor.roofnfloorz.model.PropertyImages;
import com.roofnfloor.roofnfloorz.repository.PropertyImagesRepository;
import com.roofnfloor.roofnfloorz.repository.PropertyRepository;
import com.roofnfloor.roofnfloorz.response.Response;
import com.roofnfloor.roofnfloorz.service.FileService;
import com.roofnfloor.roofnfloorz.utility.DateUtils;
@Service
public class FileServiceImpl implements FileService {
	
	private Path fileStorageLocation;
	
	@Autowired
	DateUtils dateUtils;
	
	@Autowired
	PropertyRepository propertyRepository;
	
	@Autowired
	PropertyImagesRepository propertyImagesRepository;

	@Override
	public Response uploadFile(MultipartFile[] file, Long propertyId) {
		Response resp = new Response();
		Boolean flag= true;

		Optional<Property> prop = propertyRepository.findById(propertyId);
		Property property = prop.get();
		
		// saving images of property
		for(int i=0;i<file.length;i++) {
			List<String> fileNameResp = storeFile(file[i]);
			String fileName = fileNameResp.get(0);
			String generatedName = fileNameResp.get(1);
			String filePath = "uploads/properties/" + generatedName;
			
			PropertyImages img = new PropertyImages();
			img.setImage(filePath);
			img.setPid(propertyRepository.findById(propertyId).get());
			img= propertyImagesRepository.save(img);
			if(img == null) {
				flag= false;
			}
		}	
		
		if(property!= null) {
			
			if(flag == false) {
				resp.setError(false);
				resp.setMessage("Unable to save some images");
				resp.setResponse(property);
			}else {
				resp.setError(false);
				resp.setMessage("Images saved successfully!");
				resp.setResponse(property);
			}
		}else {
			resp.setError(true);
			resp.setMessage("No property Found");
			resp.setResponse(null);
		}	
		
		resp.setError(false);
		return resp;
	}
	public List<String> storeFile(MultipartFile file) {
		// Normalize file name
		List<String> responseStrings = new ArrayList<String>();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String generatedName =  dateUtils.getTimeMilli()+"-"+fileName;
		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + generatedName);
			}

			// Copy file to the target location (Replacing existing file with the same name)
//			Path targetLocation = this.fileStorageLocation.resolve(generatedName);
            Files.copy(file.getInputStream(), Paths.get("uploads/properties/" + generatedName, new String[0]), StandardCopyOption.REPLACE_EXISTING);
			responseStrings.add(fileName);
			responseStrings.add(generatedName);
			return responseStrings;
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

}
