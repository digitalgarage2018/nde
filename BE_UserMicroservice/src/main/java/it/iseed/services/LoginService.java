/*
 ============================================================================
 Name        : LoginService.java
 Author      : Alessio Onori
 Version     : 1.0
 Copyright   : Your copyright notice
 Description : Interfaccia di servizio per funzionalit� di Login
 Gestione delle eccezioni con OPTIONAL, in quanto voglio che siano gestite tutte 
 a livello Service
 ============================================================================
 */

package it.iseed.services;

import java.util.Optional;


public interface LoginService {

	/*
	 * verifica credenziali e generazione jwt
	 */
	public Optional<String> authenticateUser(String username, String password) ;


}
