package it.iseed.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.iseed.entities.City;
import it.iseed.services.CityService;

@Controller
@RequestMapping("/city")
public class CityController {


	@Autowired
	CityService cityService;

	@RequestMapping(value = "/findByName", method = RequestMethod.GET)
	public ResponseEntity<City> findByName(@RequestParam(value = "name") String name){
		City city = cityService.findByName(name);
		return ResponseEntity.status(HttpStatus.OK).body(city);
	}
	
}
