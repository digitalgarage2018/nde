package it.iseed.daos;

import it.iseed.entities.Order;

public interface OrderDao {
	
	Order findById(int id);
	
}
