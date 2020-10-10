package com.roofnfloor.roofnfloorz.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.activation.FileTypeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.roofnfloor.roofnfloorz.request.CityRequest;
import com.roofnfloor.roofnfloorz.request.DeleteCityRequest;
import com.roofnfloor.roofnfloorz.response.Response;
import com.roofnfloor.roofnfloorz.service.CityService;

@RestController
@RequestMapping(path="/roofnfloor/city")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CityController {

	@Autowired
	CityService cityService;

	@PostMapping(path="/addCity")
	public Response addCity(@RequestBody CityRequest request) {
		return cityService.addCity(request);
	}
	
	@PostMapping(path="/updateCity")
	public Response updateCity(@RequestBody CityRequest request) {
		return cityService.updateCity(request);
	}
	
	@DeleteMapping(path="/deleteCity")
	public Response deleteCity(@RequestBody DeleteCityRequest request) {
		return cityService.deleteCity(request);
	}
	
	@GetMapping(path="/fetchAllCities")
	public Response fetchAllCities() {
		return cityService.fetchAllCities();
	}
	
	@RequestMapping(value = "/images/{name}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException {
		File img = new File("uploads/cities/"+name);
		return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img))).body(Files.readAllBytes(img.toPath()));
	}
}
