package it.iseed.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.iseed.entities.City;
import it.iseed.entities.JsonResponseBody;
import it.iseed.services.CityService;

@Controller
@RequestMapping("/city")
@CrossOrigin
public class CityController {


	@Autowired
	CityService cityService;

	@RequestMapping(value = "/findByName", method = RequestMethod.GET)
	public ResponseEntity<JsonResponseBody> findByName(@RequestParam(value = "name") String name){
		Optional<List<City>> city = cityService.findByName(name);
		return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), city.get()));
	}
	
}
