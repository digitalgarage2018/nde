package it.iseed.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.iseed.entities.House;
import it.iseed.entities.JsonResponseBody;
import it.iseed.services.HouseService;

@RestController
@RequestMapping("/house")
public class HouseController {

	@Autowired
	HouseService houseService;
	
	@RequestMapping("/test")
    public String test(){
        return "HouseMicroservice service works correctly";
    }
	
	@RequestMapping(value = "/findById", method = RequestMethod.GET)
	public ResponseEntity<House> getHouseById(@RequestParam(value = "id") int id){
		House house = houseService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(house);
	}

	
	/*
	 * interfaccia richiamabile solo se correttamente loggati
	 */
	@RequestMapping(value = "/findByCityName", method = RequestMethod.GET)
	public ResponseEntity<JsonResponseBody> findByCityName(@RequestParam (value = "jwt") String jwt, @RequestParam(value = "cityName") String cityName){
		Optional< List<House> > houses = houseService.findByCityName(jwt, cityName);
		if( houses.isPresent() ) {
			return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), houses.get() ));
		}
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new JsonResponseBody(HttpStatus.UNAUTHORIZED.value(), "user not authorized !" ));
		}
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
