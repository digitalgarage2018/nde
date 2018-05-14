package it.iseed.services;

import java.util.List;
import java.util.Optional;

import it.iseed.entities.City;

public interface CityService {

	Optional<List<City>> findByName(String name);
	
}
