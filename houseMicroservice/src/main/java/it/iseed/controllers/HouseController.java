package it.iseed.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.iseed.entities.House;
import it.iseed.services.HouseService;

@Controller
@RequestMapping("/house")
public class HouseController {

	@Autowired
	HouseService houseService;
	
	@RequestMapping(value = "/findById", method = RequestMethod.GET)
	public ResponseEntity<House> getHouseById(@RequestParam(value = "id") int id){
		House house = houseService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(house);
	}

	@RequestMapping(value = "/findByCityName", method = RequestMethod.GET)
	public ResponseEntity<List<House>> findByCityName(@RequestParam(value = "cityName") String cityName){
		List<House> houses = houseService.findByCityName(cityName);
		return ResponseEntity.status(HttpStatus.OK).body(houses);
	}
	

	@RequestMapping(value = "/findByFilterParametersAndCityName", method = RequestMethod.POST)
	public ResponseEntity<List<House>> findByFilterParametersAndCityName(@RequestBody Map<String, String> body){
		List<House> houses = houseService.findByFilterParametersAndCityName(body);	
		return ResponseEntity.status(HttpStatus.OK).body(houses);
	}
	

	@RequestMapping(value = "/findByFilterParametersAndMapCoordinates", method = RequestMethod.POST)
	public ResponseEntity<List<House>> findByFilterParametersAndMapCoordinates(@RequestBody Map<String, String> body){
		List<House> houses = houseService.findByFilterParametersAndMapCoordinates(body);	
		return ResponseEntity.status(HttpStatus.OK).body(houses);
	}


}
