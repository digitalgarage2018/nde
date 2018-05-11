/*
 ============================================================================
 Name        : PerAlessandroAuthentication.java
 Author      : Alessio Onori
 Version     : 1.0
 Copyright   : Your copyright notice
 Description : Alessandro si sta occupando di un microservizio che richiede l'autentcazione
 prima di essere reso disponibile. Questo è un template che deve implementare a livello di 
 Servizi
 ============================================================================
 */

package it.iseed.utils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import it.iseed.entities.JsonResponseBody;

public class PerAlessandroAuthentication{


	
	/*
	 * template di generico servizio che richiede un check da parte del servizio di 
	 * autenticazione
	 * Richiede in ingresso il jwt
	 * Da implementare a livello di service, dove vanno anche gestite le eccezioni
	 */
	public Optional<Object> servizioSeEsoloSeAutenticato(String jwt){

		//tento validazione sessione
		String username = getUserGivenJwt(jwt);

		if(username != null && username != "") {
			//servizio
			String s = "oggetto a caso";
			return Optional.of(s);
		}
		else {
			//validazione sessione fallita: il richiedente non è in possesso del token jwt
			return Optional.empty();
		}

	}




	//makes the call to the AccountMicroservice
	/*
	 * caso specifico, un solo user ritornato, ritorno il suo username
	 */
	private String getUserGivenJwt(String jwt){

		//preparing the header for the request (adding the jwt)
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("jwt", jwt);

		//preparing request packet to be sended 
		HttpEntity<?> request = new HttpEntity(String.class, headers);

		//preparing and sending the HTTP POST
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<JsonResponseBody> responseEntity = restTemplate.exchange("http://localhost:8070/authentication/validateSession", HttpMethod.POST, request, JsonResponseBody.class);

		String username = (String) responseEntity.getBody().getResponse();
		return username;

	}






	//makes the call to the AccountMicroservice
	/*
	 * lascio una lista di ritorno come caso generale, anche se in questo caso ad un 
	 * subject/id jwt è associato 1 ed 1 solo utente
	 */
	private List<LinkedHashMap> getUsersGivenJwt(String jwt){

		//preparing the header for the request (adding the jwt)
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("jwt", jwt);

		//preparing request packet to be sended 
		HttpEntity<?> request = new HttpEntity(String.class, headers);

		//preparing and sending the HTTP POST
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<JsonResponseBody> responseEntity = restTemplate.exchange("http://localhost:8094/authentication/validateSession", HttpMethod.POST, request, JsonResponseBody.class);

		List<LinkedHashMap> users = (List) responseEntity.getBody().getResponse();
		return users;

	}



	/*
	 * template di generico servizio che richiede un check da parte del servizio di 
	 * autenticazione
	 * Richiede in ingresso il jwt
	 * Da implementare a livello di service, dove vanno anche gestite le eccezioni
	 */
	public Optional<Object> servizioSeEsoloSeAutenticato2(String jwt){

		//tento autenticazione
		List<LinkedHashMap> accounts = getUsersGivenJwt(jwt);

		if(accounts !=null && accounts.size() > 0){
			//servizio
			String s = "oggetto a caso";
			return Optional.of(s);
		}
		else {
			//validazione sessione fallita: il richiedente non è in possesso del token jwt
			return Optional.empty();
		}

	}









}

