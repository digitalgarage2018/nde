/*
 ============================================================================
 Name        : WishlistController.java
 Author      : Alessio Onori
 Version     : 1.0
 Copyright   : Your copyright notice
 Description : Controller per la gestione delle richieste alla wishlist dello specifico user
 Implementata la sessione con un JWT, una specie di Cookie in formato JSON
 ============================================================================
 */

package it.iseed.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.iseed.entities.JsonResponseBody;
import it.iseed.entities.Wishlist;
import it.iseed.services.WishlistService;

@RestController
@CrossOrigin
@RequestMapping("/wishlist")
public class WishlistController {

	@Autowired
	private WishlistService wishlistService;



	@RequestMapping("/test")
	public String test(){
		return "Wishlist service works correctly";
	}


	/*
	 * vincolo su post!
	 * utilizzo della classe JsonResponseBody per uniformare le risposte
	 * 
	 * SESSIONE: mantenuta con un JWT, un particolare cookie di fatto, che viene restituito
	 * nel header al chiamante nel caso di avvenuta autenticazione con successo.
	 * 
	 * il jwt contiene l'informazione sullo User, username, ma anche id!
	 */
	@RequestMapping(
			value = "/getWishlist",
			params = { "jwt" }, 
			method = RequestMethod.POST
			)
	public ResponseEntity<JsonResponseBody> getWishlist(@RequestParam (value = "jwt") String jwt ) {

		Optional< Wishlist > wishlist = wishlistService.getWishlist(jwt);
		if( wishlist.isPresent() ) {
			return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), wishlist.get() ));
		}
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new JsonResponseBody(HttpStatus.UNAUTHORIZED.value(), "user not authorized !" ));
		}

	}//getWishlist


	/*
	 * vincolo su post!
	 * utilizzo della classe JsonResponseBody per uniformare le risposte
	 * 
	 * SESSIONE: mantenuta con un JWT, un particolare cookie di fatto, che viene restituito
	 * nel header al chiamante nel caso di avvenuta autenticazione con successo.
	 * 
	 * il jwt contiene l'informazione sullo User, username, ma anche id!
	 */
	@RequestMapping(
			value = "/addWish",
			params = { "jwt","idHouse" }, 
			method = RequestMethod.POST
			)
	public ResponseEntity<JsonResponseBody> addWish(@RequestParam (value = "jwt") String jwt, @RequestParam (value = "idHouse") int idHouse  ) {

		boolean result = wishlistService.addWish(jwt, idHouse);
		if( result == true ) {
			return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), "House aggiunta alla wishlist" ));
		}
		else {
			//potrebbe anche essere che ha sbagliato l'idHouse inserito...
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new JsonResponseBody(HttpStatus.UNAUTHORIZED.value(), "user not authorized !" ));
		}

	}//addWish



	/*
	 * vincolo su post!
	 * utilizzo della classe JsonResponseBody per uniformare le risposte
	 * 
	 * SESSIONE: mantenuta con un JWT, un particolare cookie di fatto, che viene restituito
	 * nel header al chiamante nel caso di avvenuta autenticazione con successo.
	 * 
	 * il jwt contiene l'informazione sullo User, username, ma anche id!
	 */
	@RequestMapping(
			value = "/removeWish",
			params = { "jwt","idHouse" }, 
			method = RequestMethod.POST
			)
	public ResponseEntity<JsonResponseBody> removeWish(@RequestParam (value = "jwt") String jwt, @RequestParam (value = "idHouse") int idHouse  ) {

		boolean result = wishlistService.removeWish(jwt, idHouse);
		if( result == true ) {
			return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), "House rimossa dalla wishlist" ));
		}
		else {
			//potrebbe anche essere che ha sbagliato l'idHouse inserito...
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new JsonResponseBody(HttpStatus.UNAUTHORIZED.value(), "user not authorized !" ));
		}

	}//removeWish


	
	@RequestMapping(
			value = "/create",
			method = RequestMethod.POST
			)
	public ResponseEntity<JsonResponseBody> creatWishlist(@RequestBody Map<String, String> body) {

		boolean result = wishlistService.createWishlist(body.get("jwt"), body.get("name"));
		if( result == true ) {
			return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), "Creata nuova wishlist" ));
		}
		else {
			//potrebbe anche essere che ha sbagliato l'idHouse inserito...
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new JsonResponseBody(HttpStatus.UNAUTHORIZED.value(), "user not authorized !" ));
		}

	}//removeWish


}
