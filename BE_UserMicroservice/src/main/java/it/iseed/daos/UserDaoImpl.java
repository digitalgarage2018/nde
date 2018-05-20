/*
 ============================================================================
 Name        : UserDaoImpl.java
 Author      : Alessio Onori
 Version     : 1.0
 Copyright   : Your copyright notice
 Description : Implementazione dell'interfaccia
 ============================================================================
 */

package it.iseed.daos;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.iseed.controllers.LoginController;
import it.iseed.entities.User;


@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@PersistenceContext
	public EntityManager entityManager;


	public Optional<User> getUserById(int id) {

		Optional<User> result = Optional.empty();

		try {
			result = Optional.of( entityManager.find(User.class, id) );
		}
		catch(NoResultException e){
			result = Optional.empty();
		}

		return result;
	}

	
	public Optional<User> getUserByUsername(String username) {

		Optional<User> result = Optional.empty();

		//da migliorare la scrittura della query
		Query q = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :name");
		q.setParameter("name", username);

		try {
			result = Optional.of( (User)q.getSingleResult() );
		}
		catch(NoResultException e){
			result = Optional.empty();
		}

		return result;
	}

	
	public Optional<User> getUserByEmail(String email) {

		Optional<User> result = Optional.empty();

		//da migliorare la scrittura della query
		Query q = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :name");
		q.setParameter("name", email);

		try {
			result = Optional.of( (User)q.getSingleResult() );
		}
		catch(NoResultException e){
			result = Optional.empty();
		}

		return result;
	}

	

	/*
	 * da migliorare la strategia di query
	 */
//	public boolean createUser(String username, String email, String password){
//
//		boolean result = false;
//		
//		/*
//		 * INSERIMENTO di utente con nome o pass gia presenti nel db:
//		 * If an Exception is being thrown in a different thread, a 
//		 * try/catch will not help. You need to thoroughly check for invalid dates 
//		 * before submitting them to your database to prevent this.
//		 */
//		if(getUserByUsername(username).isPresent())
//			return false;
//		if(getUserByEmail(email).isPresent())
//			return false;
//
//		//metodo "nativo"
//		Query q = entityManager.createNativeQuery("INSERT INTO user (username,password,email) VALUES (:username, :password, :email)");
//		//id autogenerato
//		q.setParameter("username", username);
//		q.setParameter("password", password);
//		q.setParameter("email", email);
//
//		try {
//			q.executeUpdate();
//			result = true;
//		}
//		catch(Exception e){
//			log.info("Eccezzione in persistenza: "+e);//debug
//			result = false;
//		}
//
//		return result;
//	}
	
	public Optional<User> createUser(String username, String email, String password){

		Optional<User> result = Optional.empty();
		
		/*
		 * INSERIMENTO di utente con nome o pass gia presenti nel db:
		 * If an Exception is being thrown in a different thread, a 
		 * try/catch will not help. You need to thoroughly check for invalid dates 
		 * before submitting them to your database to prevent this.
		 */
		if(getUserByUsername(username).isPresent())
			return result;
		if(getUserByEmail(email).isPresent())
			return result;

		//utilizzo API
		User u = new User();
		//id autogenerato
		u.setUsername(username);
		u.setEmail(email);
		u.setPassword(password);
		
		try {
			entityManager.persist(u);
			result = Optional.of(u);
		}
		catch(Exception e){
			log.info("Eccezzione in persistenza: "+e);//debug
		}

		return result;
	}


}
