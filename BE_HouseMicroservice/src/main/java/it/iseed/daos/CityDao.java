package it.iseed.daos;

import java.util.List;

import it.iseed.entities.City;

public interface CityDao {
	
	List<City> findByName(String name);

}
