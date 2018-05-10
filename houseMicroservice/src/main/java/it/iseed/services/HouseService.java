package it.iseed.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.iseed.entities.House;

public interface HouseService {

	House findById(int id);
	
	/*
	 * con gestione sessione
	 */
	Optional< List<House> > findByCityName(String cityName, String jwt);
	
	List<House> findByFilterParametersAndCityName(Map<String, String> parameters);
	
	List<House> findByFilterParametersAndMapCoordinates(Map<String, String> parameters);

}
