/*
 ============================================================================
 Name        : WalletDao.java
 Author      : Alessio Onori
 Version     : 1.0
 Copyright   : Your copyright notice
 Description : Interfaccia Dao per il bean Wallet
 ============================================================================
 */

package it.iseed.daos;

import java.util.Optional;

import it.iseed.entities.Wallet;


public interface WalletDao {

	public Optional<Wallet> getWalletById(int id);

	public Optional<Wallet> getWalletByIdUser(int idUser);
	
	/*
	 * ogni wallet � specifico di un solo utente
	 * ==> idUser
	 */
	public boolean createWallet(int idUser);


}
