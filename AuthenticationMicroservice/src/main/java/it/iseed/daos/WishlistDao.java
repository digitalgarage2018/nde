/*
 ============================================================================
 Name        : WishlistDao.java
 Author      : Alessio Onori
 Version     : 1.0
 Copyright   : Your copyright notice
 Description : Interfaccia Dao per il bean Wishlist
 ============================================================================
 */

package it.iseed.daos;

import java.util.Optional;

import it.iseed.entities.House;
import it.iseed.entities.User;
import it.iseed.entities.Wishlist;


public interface WishlistDao {
	
	public Optional<Wishlist> getWishlistById(int id);
	
	public Optional<Wishlist> getWishlistByIdUser(int idUser);
	
	public boolean insertHouseByIdUser(int idUser, int idHouse);
	
}
