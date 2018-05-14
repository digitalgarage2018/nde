package it.iseed.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.iseed.daos.CityDao;
import it.iseed.entities.City;

@Service
public class CityServiceImpl implements CityService{

	@Autowired
	CityDao cityDao;
	
	@Override
	public Optional<List<City>> findByName(String name){
		Optional<List<City>> result;
		
		try {
			 result = Optional.of(cityDao.findByName(name));
		}
		catch(Exception ex) {
			result = Optional.empty();
		}
		
		return result;

	}
	
}
