/*
 ============================================================================
 Name        : LoginServiceImpl.java
 Author      : Alessio Onori
 Version     : 1.0
 Copyright   : Your copyright notice
 Description : Implementazione dell'interfaccia 
 ============================================================================
 */

package it.iseed.services;

import java.util.Date;
import java.util.Optional;


//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import io.jsonwebtoken.ExpiredJwtException;
import it.iseed.daos.UserDao;
import it.iseed.daos.WalletDao;
import it.iseed.entities.User;




@Service
public class LoginServiceImpl implements LoginService {
	
//	private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	WalletDao walletDao;
	
	@Autowired
	private SessionService sessionService;
	


	public Optional<String> authenticateUser(String username, String password) {
		
		Optional<String> resultJWT = Optional.empty();
		
		Optional<User> user = userDao.getUserByUsername(username);
		
		/*
		 * Username puo contenere una password oppure una username, entrambi 
		 * contemplati ai fini del login
		 */
		if(!user.isPresent())
			user = userDao.getUserByEmail(username);
		
		if( user.isPresent() ){
			 if( !(user.get().getPassword().equals(password) && ( user.get().getUsername().equals(username) || user.get().getEmail().equals(username) )) ){
				 /*
				  * utente senza privilegi!
				  */
				 resultJWT = Optional.empty();
			 }
			 else {
				//check if user exists in DB -> if exists generate JWT and send back to client
				resultJWT = sessionService.createJwt(""+user.get().getId(), user.get().getUsername(), "user", new Date());
			 }//else wallet
		 }
		
		return resultJWT;
	}



	@Override
	public Optional<User> getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}




}
