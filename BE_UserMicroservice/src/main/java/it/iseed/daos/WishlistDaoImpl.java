/*
 ============================================================================
 Name        : WalletDaoImpl.java
 Author      : Alessio Onori
 Version     : 1.0
 Copyright   : Your copyright notice
 Description : Implementazione dell'interfaccia
 ============================================================================
 */

package it.iseed.daos;

import java.util.ArrayList;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.iseed.entities.House;
import it.iseed.entities.User;
import it.iseed.entities.Wallet;
import it.iseed.entities.Wishlist;


@Repository
@Transactional
public class WishlistDaoImpl implements WishlistDao {

	@PersistenceContext
	public EntityManager entityManager;


	public Optional<Wishlist> getWishlistById(int id) {

		Optional<Wishlist> result = Optional.empty();

		try {
			result = Optional.of( entityManager.find(Wishlist.class, id) );
		}
		catch(NoResultException e){
			result = Optional.empty();
		}

		return result;
	}

	
	public Optional<Wishlist> getWishlistByIdUser(int idUser) {

		Optional<Wishlist> result = Optional.empty();

		//da migliorare la scrittura della query
		Query q = entityManager.createQuery("SELECT w FROM Wishlist w WHERE w.user = :idUser");
		q.setParameter("idUser", idUser);

		try {
			result = Optional.of( (Wishlist)q.getSingleResult() );
		}
		catch(NoResultException e){
			result = Optional.empty();
		}

		return result;
	}

	
	public boolean createWishlist(String name, User user) {

		boolean result = false;

		Wishlist w = new Wishlist();
		w.setName(name);
		w.setUser(user);
		w.setHouses(new ArrayList<House>());//lista vuota
		
		try {
			entityManager.persist(w);
			result = true;
		}
		catch(Exception e) {
			System.out.println("problema in persistenza: " +e);
		}

		return result;
	}



	@Override
	public boolean insertHouseByIdUser(int idUser, int idHouse) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean removeHouseByIdUser(int idUser, int idHouse) {
		// TODO Auto-generated method stub
		return false;
	}



}
