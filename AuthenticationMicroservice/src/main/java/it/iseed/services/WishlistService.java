/*
 ============================================================================
 Name        : WishlistService.java
 Author      : Alessio Onori
 Version     : 1.0
 Copyright   : Your copyright notice
 Description : Interfaccia di servizio per funzionalit� di Wishlist
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
import it.iseed.entities.Wishlist;


public interface WishlistService {

	//jwt contiene anche l'id utente
	public Optional<Wishlist> getWishlist(String jwt);

	//jwt contiene anche l'id utente
	public boolean addWish(String jwt, int idHouse);

	//jwt contiene anche l'id utente
	public boolean removeWish(String jwt, int idHouse);

}
