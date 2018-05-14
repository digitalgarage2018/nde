package it.iseed.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import it.iseed.daos.HouseDao;
import it.iseed.entities.House;
import it.iseed.entities.JsonResponseBody;

@Service
public class HouseServiceImpl implements HouseService{

	
	private static final Logger log = LoggerFactory.getLogger(HouseServiceImpl.class);
	
	@Autowired
	HouseDao houseDao;

	@Override
	public Optional<House> findById(int id, String jwt) {
		Optional<String> username = getUserGivenJwt(jwt);

		if( username.isPresent() ) {
			return Optional.of(houseDao.findById(id));
		}
		else {
			return Optional.empty();
		}
	}


	/*
	 * Implementato servizio di validazione Sessione
	 */
	@Override
	public Optional<List<House>> findByCityName(String cityName, String jwt) {

		//tento validazione sessione
		Optional<String> username = getUserGivenJwt(jwt);
		System.out.println(username);

		if( username.isPresent() ) {
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
	public Optional<List<House>> findByFilterParametersAndCityName(Map<String, String> parameters, String jwt) {
		Optional<String> username = getUserGivenJwt(jwt);

		if( username.isPresent() ) {
			return Optional.of( houseDao.findByFilterParametersAndCityName(parameters));
		}
		else {
			return Optional.empty();
		}
		
	}

	@Override
	public Optional<List<House>> findByFilterParametersAndMapCoordinates(Map<String, String> parameters, String jwt) {
		Optional<String> username = getUserGivenJwt(jwt);

		if(username.isPresent()) {
			System.out.println("SCOMMESSA DEL CAFFE VINTA DA ALESSIO");//debug
			return Optional.of( houseDao.findByFilterParametersAndMapCoordinates(parameters));
		}
		else {
			System.out.println("SCOMMESSA VINTA DA ALESSANDRO");//debug
			return Optional.empty();
		}
	}



	//makes the call to the UserMicroservice
	/*
	 * BY ALE: si potrebbe dedicargli una calsse per il servizio di validazione
	 * trami exchange su microservizio dedicato....
	 * al momento lasciato qui dentro
	 * 
	 * caso specifico, un solo user ritornato, ritorno il suo username
	 * 
	 * implementata gestione eccezioni
	 */
	private Optional<String> getUserGivenJwt(String jwt){
		
		//preparing the returned object
		Optional<String> username;

		//preparing the header for the request (adding the jwt)
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("jwt", jwt);

		//preparing request packet to be sended 
		HttpEntity<?> request = new HttpEntity(String.class, headers);

		//preparing and sending the HTTP POST
		RestTemplate restTemplate = new RestTemplate();
		try {
			ResponseEntity<JsonResponseBody> responseEntity = restTemplate.exchange("http://localhost:8070/session/validateSession", HttpMethod.POST, request, JsonResponseBody.class);
			username = Optional.of( (String) responseEntity.getBody().getResponse() );
		}
		catch(RestClientException e) {
			log.info("exchange to AuthenticationMicroservice problem: " + e.getMessage());
			username = Optional.empty();
		}
		
		return username;
	}
	
	


}
