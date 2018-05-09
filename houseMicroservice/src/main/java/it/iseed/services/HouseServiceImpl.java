package it.iseed.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.iseed.daos.HouseDao;
import it.iseed.entities.House;

@Service
public class HouseServiceImpl implements HouseService{

	@Autowired
	HouseDao houseDao;
	
	@Override
	public House findById(int id) {
		return houseDao.findById(id);	
	}
	
	@Override
	public List<House> findByCityName(String cityName) {
		return houseDao.findByCityName(cityName);
	}
	
	@Override
	public List<House> findByFilterParameters(Map<String, String> parameters) {
		return houseDao.findByFilterParameters(parameters);
	}
}
