package it.iseed.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.iseed.entities.House;

public interface HouseService {

	Optional<House> findById(int id, String jwt);
	
	Optional<List<House>> findByCityName(String cityName, String jwt);
	
	Optional<List<House>> findByFilterParametersAndCityName(Map<String, String> parameters, String jwt);
	
	Optional<List<House>> findByFilterParametersAndMapCoordinates(Map<String, String> parameters, String jwt);

}
