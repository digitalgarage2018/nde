/*
 ============================================================================
 Name        : WalletDaoImpl.java
 Author      : Alessio Onori
 Version     : 1.0
 Copyright   : Your copyright notice
 Description : Implementazione dell'interfaccia
 ============================================================================
 */

package it.iseed.daos;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.iseed.entities.Wallet;


@Repository
@Transactional
public class WalletDaoImpl implements WalletDao {

	@PersistenceContext
	public EntityManager entityManager;


	public Optional<Wallet> getWalletById(int id) {

		Optional<Wallet> result = Optional.empty();

		try {
			result = Optional.of( entityManager.find(Wallet.class, id) );
		}
		catch(NoResultException e){
			result = Optional.empty();
		}

		return result;
	}

	
	public Optional<Wallet> getWalletByIdUser(int idUser) {

		Optional<Wallet> result = Optional.empty();

		//da migliorare la scrittura della query
		Query q = entityManager.createQuery("SELECT w FROM Wallet w WHERE w.idUser = :idUser");
		q.setParameter("idUser", idUser);

		try {
			result = Optional.of( (Wallet)q.getSingleResult() );
		}
		catch(NoResultException e){
			result = Optional.empty();
		}

		return result;
	}

	
	/*
	 * da migliorare la scrittura della query
	 * credito iniziale CABLATO: DA CAMBIARE!!
	 */
	public boolean createWallet(int idUser) {

		boolean result = false;

		//metodo "nativo"
		Query q = entityManager.createNativeQuery("INSERT INTO wallet (id_user,credit) VALUES (:idUser, :credit)");
		//id autogenerato
		q.setParameter("idUser", idUser);
		q.setParameter("credit", 10000);

		try {
			q.executeUpdate();
			result = true;
		}
		catch(Exception e){
			result = false;
		}

		return result;
	}



}
