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
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.iseed.entities.House;
import it.iseed.entities.User;
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
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Wishlist> cq = cb.createQuery(Wishlist.class);
		Root<Wishlist> wish = cq.from(Wishlist.class);
		cq.select(wish);
		TypedQuery<Wishlist> q = entityManager.createQuery(cq);
		
		try {
			
			List<Wishlist> allWishlist = q.getResultList();
			for(Wishlist w : allWishlist) {
				if(w.getUser().getId() == idUser)
					result = Optional.of(w);
			}
			
		}
		catch(Exception e) {
			System.out.println("problemi in persistenza:" +e.getMessage());
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
		
		boolean result = false;

		Optional<Wishlist> w = getWishlistByIdUser(idUser);
		System.out.println("recuperata wishlist");
		
		if( w.isPresent() ) {
			
			Optional<House> h = this.findHouseById(idHouse);
			if( h.isPresent() ) {
				w.get().getHouses().add(h.get());
				
				try {
					System.out.println("tentativo di aggiornamento wishlist con nuova casa:" +w.get().getHouses().get(0).getAddress());//debug
					entityManager.merge(w.get());
					result = true;
				}
				catch(Exception e) {
					System.out.println("problema in persistenza: " +e);
				}
			}//h is present
		}//w is present
		
		return result;
	}


	@Override
	public boolean removeHouseByIdUser(int idUser, int idHouse) {

		boolean result = false;

		Optional<Wishlist> w = getWishlistByIdUser(idUser);
		System.out.println("recuperata wishlist");
		
		if( w.isPresent() ) {
			
			Optional<House> h = this.findHouseById(idHouse);
			if( h.isPresent() ) {
				w.get().getHouses().remove(h.get());
				
				try {
					System.out.println("tentativo di rimozione casa da wishlist:" +h.get().getAddress());//debug
					entityManager.merge(w.get());
					result = true;
				}
				catch(Exception e) {
					System.out.println("problema in persistenza: " +e);
				}
				
			}//h != null
		}// w is present
		
		return result;
	}


	
	/*
	 * sarebbe da fare una chiamata al microservizio house,
	 * ma per ora metto questa pezza
	 */
	private Optional<House> findHouseById(int id){
		Optional<House> result = Optional.empty();
		
		House h = entityManager.find(House.class, id);
		
		if(h != null)
			result = Optional.of(h);

		return result;
	}
	
}
