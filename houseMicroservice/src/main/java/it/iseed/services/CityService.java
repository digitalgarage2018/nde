package it.iseed.services;

import it.iseed.entities.City;

public interface CityService {

	City findByName(String name);
	
}
