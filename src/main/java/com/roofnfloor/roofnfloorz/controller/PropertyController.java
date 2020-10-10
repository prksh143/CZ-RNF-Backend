package com.roofnfloor.roofnfloorz.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;

import javax.activation.FileTypeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.roofnfloor.roofnfloorz.request.DeleteUserPropertyRequest;
import com.roofnfloor.roofnfloorz.request.PropertyRequest;
import com.roofnfloor.roofnfloorz.request.PropertyTypeRequest;
import com.roofnfloor.roofnfloorz.response.Response;
import com.roofnfloor.roofnfloorz.service.CityService;
import com.roofnfloor.roofnfloorz.service.FileService;
import com.roofnfloor.roofnfloorz.service.PropertyService;
import com.roofnfloor.roofnfloorz.service.PropertyTypeService;

@RestController
@RequestMapping(path = "/roofnfloor/property")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PropertyController {

	@Autowired
	PropertyTypeService propertyTypeService;

	@Autowired
	CityService cityService;

	@Autowired
	PropertyService propertyService;

	@Autowired
	FileService fileService;

	@PostMapping(path = "/addPropertyType")
	public Response addPropertyType(@RequestBody PropertyTypeRequest request) {
		return propertyTypeService.addPropertyType(request);
	}

	@PostMapping(path = "/addProperty")
	public Response addProperty(@RequestBody PropertyRequest request) {
		return propertyService.addProperty(request);
	}

	@PostMapping(path = "/verifyProperty")
	public Response verifyProperty(@RequestBody DeleteUserPropertyRequest request) {
		return propertyService.verifyProperty(request);
	}
	
	@PostMapping(path = "/uploadPropertyFiles")
	public Response uploadFile(@RequestParam("file") MultipartFile[] file, @RequestParam("propertyId") Long propertyId) throws ParseException {
		return fileService.uploadFile(file, propertyId);
	}

	@GetMapping(path = "/fetchAllProperty")
	public Response fetchAllProperty() {
		return propertyService.fetchAllProperty();
	}

	@GetMapping(path = "/fetchAllBuyProperty")
	public Response fetchAllBuyProperty(@RequestParam String location) {
		return propertyService.fetchAllBuyProperty(location);
	}

	@GetMapping(path = "/fetchAllRentProperty")
	public Response fetchAllRentProperty(@RequestParam String location) {
		return propertyService.fetchAllRentProperty(location);
	}

	@GetMapping(path = "/fetchAllCommercialProperty")
	public Response fetchAllCommercialProperty(@RequestParam String location) {
		return propertyService.fetchAllCommercialProperty(location);
	}

	@GetMapping(path = "/fetchAllDealerProperty")
	public Response fetchAllDealerProperty(@RequestParam String location) {
		return propertyService.fetchAllDealerProperty(location);
	}

	@GetMapping(path = "/fetchAllProjectProperty")
	public Response fetchAllProjectProperty(@RequestParam String location) {
		return propertyService.fetchAllProjectProperty(location);
	}

	@GetMapping(path = "/fetchCityInformation")
	public Response fetchCityInformation(@RequestParam String cityCode) {
		return cityService.fetchCityInformation(cityCode);
	}
	@RequestMapping(value = "/uploads/properties/{name}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException {
		File img = new File("uploads/properties/"+name);
		return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img))).body(Files.readAllBytes(img.toPath()));
	}
}
