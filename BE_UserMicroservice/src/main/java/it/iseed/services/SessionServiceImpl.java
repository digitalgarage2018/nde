/*
 ============================================================================
 Name        : SessionServiceImpl.java
 Author      : Alessio Onori
 Version     : 1.0
 Copyright   : Your copyright notice
 Description : Implementazione dell'interfaccia 
 ============================================================================
 */

package it.iseed.services;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.ExpiredJwtException;
import it.iseed.daos.UserDao;
import it.iseed.daos.WalletDao;
import it.iseed.utils.JwtUtils;




@Service
public class SessionServiceImpl implements SessionService {
	
	private static final Logger log = LoggerFactory.getLogger(SessionServiceImpl.class);
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	WalletDao walletDao;
	
	

	/*
	 * by Piergiorgio
	 */
	@Override
	public Optional<String> createJwt(String subject, String name, String permission, Date date) {
		Date expDate = date;//now
        expDate.setTime(date.getTime() + (300*1000));//5 minutes
        log.info("JWT Creation. Expiration time: " + expDate.getTime());
        
        Optional<String> token = Optional.empty();
		try {
			token = Optional.of( JwtUtils.generateJwt(subject, expDate, name, permission) );
		} catch (UnsupportedEncodingException e) {
			token = Optional.empty();
			log.info("JWT Creation PROBLEM!!: " + e.getMessage());
		}
		
        return token;
	}


	/*
	 * by Piergiorgio
	 */
	@Override
	public Optional < Map<String, Object> > verifyJwtAndGetData(HttpServletRequest request) {
		
		Optional < Map<String, Object>  > userData = Optional.empty();
		
		String jwt = JwtUtils.getJwtFromHttpRequest(request);
        if(jwt == null){
        	//utente non autenticato
        	log.info("Authentication token not found in the request");
            //throw new UserNotLoggedException("Authentication token not found in the request");
        }
        else {
        	try {
				userData = Optional.of( JwtUtils.jwt2Map(jwt) );
			} catch (ExpiredJwtException e) {
				log.info("ExpiredJwtException: "+e.getMessage());
			} catch (UnsupportedEncodingException e) {
				log.info("UnsupportedEncodingException: "+e.getMessage());
			}
        }
        
        return userData;
	}
	
	
	/*
	 * per validare i servizi locali
	 */
	@Override
	public Optional < Map<String, Object> > verifyJwtAndGetData(String jwt) {
		
		Optional < Map<String, Object>  > userData = Optional.empty();
		
        if(jwt == null){
        	//utente non autenticato
        	log.info("Authentication token not found in the request");
            //throw new UserNotLoggedException("Authentication token not found in the request");
        }
        else {
        	try {
				userData = Optional.of( JwtUtils.jwt2Map(jwt) );
			} catch (ExpiredJwtException e) {
				log.info("ExpiredJwtException: "+e.getMessage());
			} catch (UnsupportedEncodingException e) {
				log.info("UnsupportedEncodingException: "+e.getMessage());
			}
        }
        
        return userData;
	}

	
	


}
