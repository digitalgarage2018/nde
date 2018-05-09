package it.iseed.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.iseed.daos.CityDao;
import it.iseed.entities.City;

@Service
public class CityServiceImpl implements CityService{

	@Autowired
	CityDao cityDao;
	
	@Override
	public City findByName(String name) {
		return cityDao.findByName(name);	
	}
	
}
