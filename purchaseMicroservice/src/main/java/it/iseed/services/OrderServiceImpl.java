package it.iseed.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.iseed.daos.OrderDao;
import it.iseed.entities.Order;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderDao orderDao;
	
	@Override
	public Order findById(int id) {
		return orderDao.findById(id);	
	}
	
}
