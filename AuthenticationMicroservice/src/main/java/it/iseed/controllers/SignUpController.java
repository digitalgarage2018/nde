/*
 ============================================================================
< Name        : SignUpController.java
 Author      : Alessio Onori
 Version     : 1.0
 Copyright   : Your copyright notice
 Description : Controller per la gestione delle richieste di registrazione
 ============================================================================
 */

package it.iseed.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.iseed.entities.JsonResponseBody;
import it.iseed.services.SignUpService;

@RestController
public class SignUpController {

	@Autowired
	private SignUpService signUpService;


	/*
	 * non vincolo su post...
	 * utilizzo della classe JsonResponseBody per uniformare le risposte
	 */
	@RequestMapping(
			value = "/authentication/signUp",
			params = { "username", "email", "password" }
			)
	public ResponseEntity<JsonResponseBody>  signUser(@RequestParam (value = "username") String username, @RequestParam (value = "email") String email, @RequestParam (value = "password") String password ) {

		try {

			/*
			 * tento la creazione dell'user e del suo wallet
			 */
			System.out.println("tentativo di creazione user: username "+username);//debug
			boolean result = signUpService.createUser(username, email, password);

			if( result == true ) {

				// attributo per attivare un alert lato view... migliorabile
				//				model.addObject("authenticated", true);
				//				request.setAttribute("authenticated", true);
				/*
				 * non può piu funzionare cosi ora.... da sistemare lato client
				 */

				return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), result ) );

			}
			else {
				return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), result ) );
			}


		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), "Error: " + e.toString()));
		}

	}


}
