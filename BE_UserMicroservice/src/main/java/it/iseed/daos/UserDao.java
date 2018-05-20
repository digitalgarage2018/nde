/*
 ============================================================================
 Name        : UserDao.java
 Author      : Alessio Onori
 Version     : 1.0
 Copyright   : Your copyright notice
 Description : Interfaccia Dao per il bean User
 ============================================================================
 */

package it.iseed.daos;

import java.util.Optional;

import it.iseed.entities.User;


public interface UserDao {
	
	public Optional<User> getUserById(int id);

	public Optional<User> getUserByUsername(String username);

	public Optional<User> getUserByEmail(String email);
	
	//public boolean createUser(String username, String email, String password);
	
	public Optional<User> createUser(String username, String email, String password);
	
}
