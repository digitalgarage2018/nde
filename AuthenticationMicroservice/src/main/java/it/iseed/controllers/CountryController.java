package it.iseed.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import it.iseed.entities.CountryEntity;
import it.iseed.entities.JsonResponseBody;
import it.iseed.services.CountryService;




@RestController
public class CountryController {
	
	@Autowired
	CountryService countryService;
	
	/*
	 * questo per chiamarlo: ..../country/18
	 */
	@RequestMapping(value = "/country/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public CountryEntity getCountryById(@PathVariable int id)
	{
		return countryService.getCountryById(id);
	}
	
	/*
	 * questo per chiamarlo: ..../country?id=18
	 * utilizzo della classe JsonResponseBody per uniformare le risposte
	 */
    @RequestMapping("/country")
    public ResponseEntity<JsonResponseBody> getCountryById2(@RequestParam (value = "id") int id){
        try {
            CountryEntity country = countryService.getCountryById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), country));
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), "Error: " + e.toString()));
        }
    }
    
    
    /*
     * FUNZIONANTE: passaggio di più parametri... il prossimo passo è il passaggio tramite POST
     */
    @RequestMapping(
    		value = "/country",
    		params = { "id", "second" }, 
    		method = RequestMethod.GET
    		)
    public ResponseEntity<JsonResponseBody> getCountryById3(@RequestParam (value = "id") int id, @RequestParam (value = "second") String second ){
        try {
            CountryEntity country = countryService.getCountryById(id);
            System.out.println("Ricevuto il parametro second: "+second);
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), country));
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), "Error: " + e.toString()));
        }
    }
    
    
    /*
     * vincolo su post!
     */
    @RequestMapping(
    		value = "/country",
    		params = { "username", "password" }, 
    		method = RequestMethod.POST
    		)
    public ResponseEntity<JsonResponseBody> getCountryById4(@RequestParam (value = "username") String username, @RequestParam (value = "password") String password ){
        try {
        	
        	String response = "";
        	response += "User: "+username+ " ,Pass: "+password;

            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), response));
            
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), "Error: " + e.toString()));
        }
    }
    
    
}
