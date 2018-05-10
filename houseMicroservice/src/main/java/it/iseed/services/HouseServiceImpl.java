package it.iseed.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import it.iseed.daos.HouseDao;
import it.iseed.entities.House;
import it.iseed.entities.JsonResponseBody;

@Service
public class HouseServiceImpl implements HouseService{

	@Autowired
	HouseDao houseDao;

	@Override
	public House findById(int id) {
		return houseDao.findById(id);	
	}


	/*
	 * Implementato servizio di validazione Sessione
	 */
	@Override
	public Optional< List<House> > findByCityName(String jwt, String cityName) {

		//tento validazione sessione
		String username="";
		username = getUserGivenJwt(jwt);

		if(username != null && username != "") {
			//servizio
			System.out.println("Debug di jwt:"+jwt);//debug
			return Optional.of( houseDao.findByCityName(cityName) );
		}
		else {
			//validazione sessione fallita: il richiedente non è in possesso del token jwt
			return Optional.empty();
		}

	}


	@Override
	public List<House> findByFilterParametersAndCityName(Map<String, String> parameters) {
		return houseDao.findByFilterParametersAndCityName(parameters);
	}

	@Override
	public List<House> findByFilterParametersAndMapCoordinates(Map<String, String> parameters) {
		return houseDao.findByFilterParametersAndMapCoordinates(parameters);
	}



	//makes the call to the AccountMicroservice
	/*
	 * caso specifico, un solo user ritornato, ritorno il suo username
	 * 
	 * restituzione attuale username ==> 
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

		/*
		 * sarebbero da gestire meglio le eccezioni: se passo un token non valido sono cazzi
		 */
		
		String username = (String) responseEntity.getBody().getResponse();
		return username;
	}
	
	


}
