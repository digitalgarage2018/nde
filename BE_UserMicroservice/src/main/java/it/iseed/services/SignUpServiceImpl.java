/*
 ============================================================================
 Name        : SignUpServiceImpl.java
 Author      : Alessio Onori
 Version     : 1.0
 Copyright   : Your copyright notice
 Description : Implementazione dell'interfaccia 
 ============================================================================
 */

package it.iseed.services;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import it.iseed.controllers.LoginController;
import it.iseed.daos.UserDao;
import it.iseed.daos.WalletDao;
import it.iseed.daos.WishlistDao;
import it.iseed.entities.User;



@Service
public class SignUpServiceImpl implements SignUpService {
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	WalletDao walletDao;
	
	@Autowired
	WishlistDao wishlistDao;
	
	/*
	 * interfaccia per spedire mail, iniettato e  configurato 
	 * per appoggiarsi ad un server smtp di google
	 */
	@Autowired
	JavaMailSender mailSender;
	

	public boolean createUser(String username, String email, String password) {
		
		boolean result = false;
		
		//debug
		log.info("tentativo di creazione user");
		Optional<User> u = userDao.createUser(username, email, password);
		
		if( u.isPresent() ) {
			
			//debug id
			log.info("l id del nuovo user è:"+u.get().getId());
			
			//creazione del wallet
			result = walletDao.createWallet(u.get().getId());
			
			//creazione whislist 
			result = wishlistDao.createWishlist("Lista dei desideri", u.get());
			
			if(result != false) {
				//invio della mail di conferma registrazione con successo
				log.info("Tentativo di invio email");//debug
				SimpleMailMessage emailObj = new SimpleMailMessage();
				emailObj.setTo(email);
				emailObj.setSubject("Registrazione");
				emailObj.setText("Complimenti "+username+"! sei stato corretamente registrato sul nostro sito! \n"
						+ "Username: "+username+" \n"
								+ "Password: "+password);
				/*
				 * operazione critica: sarebbe da fare catch
				 * su windows è necessario disabilitare l'antivirus
				 */
				try {
					mailSender.send(emailObj);
				}
				catch(Exception e){
					log.info("Se sei su windows disabilita l'antivirus, problema di invio mail");
				}
			}//wallet!=false
		}//user is present
		
		return result;
	}



}
