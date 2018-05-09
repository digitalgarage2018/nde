package it.iseed.services;

import java.util.List;
import java.util.Map;

import it.iseed.entities.House;

public interface HouseService {

	House findById(int id);
	
	List<House> findByCityName(String cityName);
	
	List<House> findByFilterParameters(Map<String, String> parameters);

}
