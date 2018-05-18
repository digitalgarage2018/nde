/*
 ============================================================================
 Name        : WishlistServiceImpl.java
 Author      : Alessio Onori
 Version     : 1.0
 Copyright   : Your copyright notice
 Description : Implementazione dell'interfaccia 
 ============================================================================
 */

package it.iseed.services;

import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.iseed.daos.UserDao;
import it.iseed.daos.WishlistDao;
import it.iseed.entities.User;
import it.iseed.entities.Wishlist;




@Service
public class WishlistServiceImpl implements WishlistService {

	private static final Logger log = LoggerFactory.getLogger(WishlistServiceImpl.class);


	@Autowired
	WishlistDao wishlistDao;
	
	@Autowired
	UserDao userDao;

	@Autowired
	SessionService sessionService;

	

	@Override
	public Optional<Wishlist> getWishlist(String jwt) {

		Optional<Wishlist> result;

		//tento validazione sessione
		Optional< Map<String, Object> > map = sessionService.verifyJwtAndGetData(jwt);

		if( map.isPresent() ) {
			//sessione valida
			log.debug("Debug di jwt:"+jwt);//debug

			//subject == idUser
			Optional<Wishlist> wishlist = wishlistDao.getWishlistByIdUser( (int)map.get().get("subject") );
			if( wishlist.isPresent() ) {
				//richiesta al db processata correttamente
				result = wishlist;;
			}
			else {
				result = Optional.empty();
			}

		}
		else {
			//validazione sessione fallita: il richiedente non è in possesso del token jwt
			result = Optional.empty();
		}

		return result;
	}



	@Override
	public boolean addWish(String jwt, int idHouse) {
		
		boolean result;

		//tento validazione sessione
		Optional< Map<String, Object> > map = sessionService.verifyJwtAndGetData(jwt);

		if( map.isPresent() ) {
			//sessione valida
			log.debug("Debug di jwt:"+jwt);//debug

			//subject == idUser
			result = wishlistDao.insertHouseByIdUser( (int)map.get().get("subject"), idHouse);
		}
		else {
			//validazione sessione fallita: il richiedente non è in possesso del token jwt
			result = false;
		}

		return result;
	}



	@Override
	public boolean removeWish(String jwt, int idHouse) {

		boolean result;

		//tento validazione sessione
		Optional< Map<String, Object> > map = sessionService.verifyJwtAndGetData(jwt);

		if( map.isPresent() ) {
			//sessione valida
			log.debug("Debug di jwt:"+jwt);//debug

			//subject == idUser
			result = wishlistDao.removeHouseByIdUser( (int)map.get().get("subject"), idHouse);
		}
		else {
			//validazione sessione fallita: il richiedente non è in possesso del token jwt
			result = false;
		}

		return result;
	}
	
	
	@Override
	public boolean createWishlist(String jwt, String name) {

		boolean result;

		//tento validazione sessione
		Optional< Map<String, Object> > map = sessionService.verifyJwtAndGetData(jwt);
		
		///log.info("CIAO ALESSIO, il tuo id:"+map.get().get("subject"));

		if( map.isPresent() ) {
			//sessione valida
			log.info("Debug di jwt:"+jwt);//debug
			
			//recupero user by id, subject == idUser
			Optional<User> user = userDao.getUserById(Integer.parseInt( (String)map.get().get("subject") ) );

			if( user.isPresent() )
				result = wishlistDao.createWishlist(name, user.get());
			else
				result = false;

		}
		else {
			//validazione sessione fallita: il richiedente non è in possesso del token jwt
			result = false;
		}

		return result;
	}





}
