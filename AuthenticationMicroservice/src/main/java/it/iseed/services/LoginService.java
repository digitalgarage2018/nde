/*
 ============================================================================
 Name        : LoginService.java
 Author      : Alessio Onori
 Version     : 1.0
 Copyright   : Your copyright notice
 Description : Interfaccia di servizio per funzionalit� di Login
 ============================================================================
 */

package it.iseed.services;

import java.util.Optional;

import it.iseed.entities.User;


public interface LoginService {
	
	public boolean authenticateUser(User userBean);
	
	public Optional<User> authenticateUser(String username, String password);

}
