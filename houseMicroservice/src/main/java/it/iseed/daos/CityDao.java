package it.iseed.daos;

import it.iseed.entities.City;

public interface CityDao {
	
	City findByName(String name);

}
