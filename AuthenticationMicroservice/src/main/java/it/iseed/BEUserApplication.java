package it.iseed;


import org.jasypt.util.text.BasicTextEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class BEUserApplication {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(BEUserApplication.class);

	public static void main(String[] args){
		SpringApplication.run(BEUserApplication.class, args);
	}
	
	@Bean
	public BasicTextEncryptor textEncryptor(){
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword("mySecretEncriptionKeyBlaBla1234");
		return textEncryptor;
	}
	
	
}
