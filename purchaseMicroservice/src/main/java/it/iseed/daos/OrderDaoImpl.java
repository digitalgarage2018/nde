package it.iseed.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.iseed.entities.Order;

@Repository
@Transactional
public class OrderDaoImpl implements OrderDao{

	@PersistenceContext
	public EntityManager entityManager;

	public Order findById(int id){
		return entityManager.find(Order.class, id);
	}

}

