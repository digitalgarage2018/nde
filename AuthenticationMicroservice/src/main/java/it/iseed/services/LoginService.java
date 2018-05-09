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

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import it.iseed.entities.User;


public interface LoginService {

	//deprecato
	public boolean authenticateUser(User userBean);

	public Optional<User> authenticateUser(String username, String password) ;

	Optional<String> createJwt(String subject, String name, String permission, Date date) ;
	//-> JwtUtils.generateJwt(...) 						 -> UnsupportedEncodingException

	Optional< Map<String, Object> > verifyJwtAndGetData(HttpServletRequest request) ;
	//-> JwtUtils.getJwtFromHttpRequest(request)		-> UserNotLoggedException
	// 	  -> JwtUtils.jwt2Map(jwt)						-> UnsupportedEncodingException
	//												->  ExpiredJwtException

}
