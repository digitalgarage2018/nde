package it.iseed;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class HouseApplication {

	private static final Logger log = LoggerFactory.getLogger(HouseApplication.class);

	public static void main(String[] args){
		SpringApplication.run(HouseApplication.class, args);
	}
}
