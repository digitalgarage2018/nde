package it.iseed.daos;

import java.util.List;
import java.util.Map;

import it.iseed.entities.House;

public interface HouseDao {
	
	House findById(int id);
	
	List<House> findByCityName(String cityName);
	
	List<House> findByFilterParameters(Map<String, String> parameters);

}
