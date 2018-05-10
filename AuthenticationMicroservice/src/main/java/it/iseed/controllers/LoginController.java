/*
 ============================================================================
 Name        : LoginController.java
 Author      : Alessio Onori
 Version     : 1.0
 Copyright   : Your copyright notice
 Description : Controller per la gestione delle richieste di Login
 Implementata la sessione con un JWT, una specie di Cookie in formato JSON
 ============================================================================
 */

package it.iseed.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import it.iseed.entities.JsonResponseBody;
import it.iseed.entities.User;
import it.iseed.services.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;


	@RequestMapping("/authentication/test")
    public String test(){
        return "Authentication service works correctly";
    }
	
	/*
	 * vincolo su post!
	 * utilizzo della classe JsonResponseBody per uniformare le risposte
	 * 
	 * SESSIONE: mantenuta con un JWT, un particolare cookie di fatto, che viene restituito
	 * nel header al chiamante nel caso di avvenuta autenticazione con successo.
	 */
	@RequestMapping(
			value = "/authentication/logIn",
			params = { "username", "password" }, 
			method = RequestMethod.POST
			)
	public ResponseEntity<JsonResponseBody> logIn(@RequestParam (value = "username") String username, @RequestParam (value = "password") String password ) {

		/*
		 * il parametro userneme potrebbe contenere una mail
		 */

		try {

			/*
			 * tento l'autenticazione
			 */
			Optional<User> loggedUser = loginService.authenticateUser(username, password);

			//check if user exists in DB -> if exists generate JWT and send back to client
			if( loggedUser.isPresent() ) {	
				/*
				 * utente correttamente loggato, caricato anche 
				 * il suo wallet
				 */

				//generate JWT
				Optional<String> jwt = loginService.createJwt(""+loggedUser.get().getId(), loggedUser.get().getUsername(), "user", new Date());
				if(jwt.isPresent()) {
					/*
					 * posso scegliere se restituire l'utente o una stringa di successo,
					 * basta commentare adeguatamente
					 */
					return ResponseEntity.status(HttpStatus.OK).header("jwt", jwt.get()).body(new JsonResponseBody(HttpStatus.OK.value(), "Success! User logged in!"));
					//					return ResponseEntity.status(HttpStatus.OK).header("jwt", jwt.get()).body(new JsonResponseBody(HttpStatus.OK.value(), loggedUser.get()));
				}
				else {
					/*
					 * possibile UnsupportedEncodingException
					 */
					return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Token Error: UnsupportedEncodingException"));
				}

			}//if logged User is present
			else {
				return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), "Utente o Password errati" ) );
			}

		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), "Error: " + e.toString()));
		}

	}//userCheck
	
	
	
	
	/*
	 * servizio offerto all'esterno agli altri microservices per autenticare le richieste 
	 * che arrivano loro
	 * 
	 * Migliorabile: try e catch non dovrebbero stare a livello di controller, bensì sotto nei services
	 * ==> MIGLIORATO: uso di Optional e gestione eccezzioni a livello più basso
	 */
	@RequestMapping(
			value = "/authentication/validateSession"
			)
	public ResponseEntity<JsonResponseBody> validateSession( HttpServletRequest request ) {

		//request -> fetch JWT -> recover User Data -> Get user accounts from DB
		Optional< Map<String, Object> > userData = loginService.verifyJwtAndGetData(request);
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
