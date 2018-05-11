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


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import it.iseed.daos.UserDao;
import it.iseed.daos.WalletDao;



@Service
public class SignUpServiceImpl implements SignUpService {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	WalletDao walletDao;
	
	/*
	 * interfaccia per spedire mail, iniettato e  configurato 
	 * per appoggiarsi ad un server smtp di google
	 */
	@Autowired
	JavaMailSender mailSender;
	

	public boolean createUser(String username, String email, String password) {
		
		boolean result = false;
		
		//debug
		System.out.println("tentativo di creazione userR");
		result = userDao.createUser(username, email, password);
		
		if(result != false) {
			/*
			 * recupero di id dello user(autogenerato) e creazione del wallet per lo 
			 * specifico user
			 */
			int idUser = userDao.getUserByUsername(username).get().getId();
			result = walletDao.createWallet(idUser);
			
			if(result != false) {
				//invio della mail di conferma registrazione con successo
				System.out.println("Tentativo di invio email");//debug
				SimpleMailMessage emailObj = new SimpleMailMessage();
				emailObj.setTo(email);
				emailObj.setSubject("Registrazione");
				emailObj.setText("Complimenti "+username+"! sei stato corretamente registrato sul nostro sito! \n"
						+ "Username: "+username+" \n"
								+ "Password: "+password);
				mailSender.send(emailObj);
			}
		}
		
		return result;
	}



}
