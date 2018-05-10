package it.iseed.daos;

import java.util.List;
import java.util.Map;

import it.iseed.entities.House;

public interface HouseDao {
	
	House findById(int id);
	
	List<House> findByCityName(String cityName);

	List<House> findByFilterParametersAndCityName(Map<String, String> parameters);
	
	List<House> findByFilterParametersAndMapCoordinates(Map<String, String> parameters);

}
