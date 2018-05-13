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

//import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.iseed.entities.JsonResponseBody;
import it.iseed.services.LoginService;

@RestController
@CrossOrigin
@RequestMapping("/authentication")
public class LoginController {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);


	@Autowired
	private LoginService loginService;


	/*
	 * la risposta è convertita in JSON in automatico graze alla
	 * annotation @RestController (invece di @Controller)
	 */
	@RequestMapping("/test")
	public String test(){
		return "Authentication service works correctly";
	}

	@RequestMapping("/test2")
	public ResponseEntity<JsonResponseBody> test2(){
		return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), "Authentication service works correctly" ));
	}


	/*
	 * vincolo di richiesta post!
	 * 
	 * SESSIONE: mantenuta con un JWT, un particolare cookie di fatto, che viene restituito
	 * nel header al chiamante nel caso di avvenuta autenticazione con successo.
	 * 
	 */
	@RequestMapping(
			value = "/logIn",
			method = RequestMethod.POST
			)
	public ResponseEntity<JsonResponseBody> logIn(@RequestBody RequestDTO request) {

		try {
			/*
			 * tento l'autenticazione: mi interfaccio con LoginService
			 */
			log.info(request.toString());//debug

			Optional<String> jwt = loginService.authenticateUser(request.getUsername(), request.getPassword());

			//check if user exists in DB -> if exists generate JWT and send back to client
			if( jwt.isPresent() ) {	
				/*
				 * utente correttamente loggato, generato anche 
				 * il suo jwt
				 */
				log.info(jwt.get());//debug

				ResponseDTO response= new ResponseDTO();
				response.setSuccess(true);
				response.setToken(jwt.get());

				/* 
				 * Settaggio degli header per corretto interfacciamento con axios
				 */
				return ResponseEntity.status(HttpStatus.OK)
						.header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Credentials", "true")
						.header("Access-Control-Allow-Headers", "jwt")
						.header("Access-Control-Expose-Headers", "jwt")
						//.header("jwt", jwt.get()) //restituito nel body
						.body(new JsonResponseBody(HttpStatus.OK.value(), response));
				//.body(new JsonResponseBody(HttpStatus.OK.value(), "Utente correttamente loggato"));

			}//if logged User is present
			else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Utente o Password errati" ) );
			}

		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), "Error: " + e.toString()));
		}

	}//login


	/*
	 * DTO che wrappa la richiesta
	 * 
	 * Essendo una nested class, in ingresso
	 * sono costretto a farla statica 
	 */
	private static class RequestDTO {
		String username;
		String password;
		public String getUsername() {
			return username;
		}
		public String getPassword() {
			return password;
		}
		@SuppressWarnings("unused")
		public void setUsername(String username) {
			this.username = username;
		}
		@SuppressWarnings("unused")
		public void setPassword(String password) {
			this.password = password;
		}
		@Override
		public String toString() {
			return "RequestDTO [username=" + username + ", password=" + password + "]";
		}
	}//DTO


	/*
	 * DTO che wrappa la risposta
	 */
	private class ResponseDTO{
		private boolean success;
		private String token;
		@SuppressWarnings("unused")
		public boolean isSuccess() {
			return success;
		}
		@SuppressWarnings("unused")
		public String getToken() {
			return token;
		}
		public void setSuccess(boolean success) {
			this.success = success;
		}
		public void setToken(String token) {
			this.token = token;
		}
		@Override
		public String toString() {
			return "ResponseDTO [success=" + success + ", token=" + token + "]";
		}
	}//DTO




}
