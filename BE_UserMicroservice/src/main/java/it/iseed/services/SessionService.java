/*
 ============================================================================
 Name        : SessionService.java
 Author      : Alessio Onori
 Version     : 1.0
 Copyright   : Your copyright notice
 Description : Interfaccia di servizio per funzionalit� di Sessione
 ============================================================================
 */

package it.iseed.services;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;



public interface SessionService {

	/*
	 * subject è in relazione con ID dell'entity e fa da indentificatore
	 * Subject è l'identificatore chiave del jwt, generato a partire dall'id dell'entity
	 */
	Optional<String> createJwt(String subject, String name, String permission, Date date) ;
	//-> JwtUtils.generateJwt(...) 						 -> UnsupportedEncodingException

	Optional< Map<String, Object> > verifyJwtAndGetData(HttpServletRequest request) ;
	//-> JwtUtils.getJwtFromHttpRequest(request)		-> UserNotLoggedException
	// 	  -> JwtUtils.jwt2Map(jwt)						-> UnsupportedEncodingException
	//												->  ExpiredJwtException
	
	//per servizi locali a questo microservice
	Optional< Map<String, Object> > verifyJwtAndGetData(String jwt) ;

}
