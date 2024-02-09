package com.example.personneservice;

import com.example.personneservice.config.GlobalConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(GlobalConfig.class)
public class PersonneServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonneServiceApplication.class, args);
	}

	/*@Bean
	CommandLineRunner commandLineRunner(PersonneRepository personneRepository){
		return args -> {
			Personne personne1 = Personne.builder()
					.nom("user1")
					.prenom("ththt")
					.date(10)
					.sexe(Sexe.FEMININ)
					.build();
			personneRepository.save(personne1);

			Personne personne2 = Personne.builder()
					.nom("user2")
					.prenom("ththt")
					.date(12)
					.sexe(Sexe.MASCULIN)
					.build();
			personneRepository.save(personne2);
		};
	}*/

}
