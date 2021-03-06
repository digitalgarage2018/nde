package it.iseed.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.iseed.entities.City;

@Repository
@Transactional
public class CityDaoImpl implements CityDao{

	@PersistenceContext
	public EntityManager entityManager;

	@Override
	public List<City> findByName(String name) {
		TypedQuery<City> query = entityManager.createQuery("SELECT c FROM City c WHERE c.name LIKE :name", City.class);
		
		query.setParameter("name", name + "%");

		return query.getResultList();
	}
}
