/*
 ============================================================================
 Name        : SessionController.java
 Author      : Alessio Onori
 Version     : 1.0
 Copyright   : Your copyright notice
 Description : Controller per la gestione delle sessioni
 Implementata la sessione con un JWT, una specie di Cookie in formato JSON
 ============================================================================
 */

package it.iseed.controllers;

//import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.iseed.entities.JsonResponseBody;
import it.iseed.services.SessionService;

@RestController
@CrossOrigin
@RequestMapping("/session")
public class SessionController {

	@Autowired
	private SessionService sessionService;


	@RequestMapping("/test")
    public String test(){
        return "Session service works correctly";
    }
	
	
	/*
	 * servizio offerto all'esterno agli altri microservices per autenticare le richieste 
	 * che arrivano loro
	 * 
	 * Migliorabile: try e catch non dovrebbero stare a livello di controller, bensì sotto nei services
	 * ==> MIGLIORATO: uso di Optional e gestione eccezzioni a livello più basso
	 */
	@RequestMapping(
			value = "/validateSession"
			)
	public ResponseEntity<JsonResponseBody> validateSession( HttpServletRequest request ) {

		//request -> fetch JWT -> recover User Data -> Get user accounts from DB
		Optional< Map<String, Object> > userData = sessionService.verifyJwtAndGetData(request);
		if( userData.isPresent() ) {
			//provvisiorio: ritorno il nome dell'utente a cui è associato il token
			return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), userData.get().get("name") ));
		}
		else {
			//UnsoportedEncoding, Expiration time, oppure User not Logged
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.GATEWAY_TIMEOUT.value(), "Session Expired oppure User not logged! Login first!" ));
		}
	}//validateSession
	
	
	

}
