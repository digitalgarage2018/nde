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
	


	public Optional<User> authenticateUser(String username, String password) {
		/*
		 * Username pu� contenere una password oppure una username, entrambi 
		 * contemplati ai fini del login
		 */
		
		Optional<User> result = Optional.empty();
		
		result = userDao.getUserByUsername(username);
		
		if(!result.isPresent())
			result = userDao.getUserByEmail(username);
		
		if( result.isPresent() ){
			 if( !(result.get().getPassword().equals(password) && ( result.get().getUsername().equals(username) || result.get().getEmail().equals(username) )) ){
				 /*
				  * utente senza privilegi!
				  */
				 result = Optional.empty();
			 }
			 else {
				 /*
				  * allaccio anche il wallet ad esso associato:
				  * per ora non gestisco una possibile casistica di errore
				  * ma sarebbe da fare!
				  */
				 result.get().setWallet( walletDao.getWalletByIdUser(result.get().getId()).get() );
			 }//else wallet
		 }
		
		return result;
	}




}
