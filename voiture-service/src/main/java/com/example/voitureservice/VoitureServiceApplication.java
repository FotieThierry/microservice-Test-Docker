package com.example.voitureservice;

import com.example.voitureservice.entities.Voiture;
import com.example.voitureservice.repositories.VoitureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class VoitureServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VoitureServiceApplication.class, args);
    }

  /*  @Bean
    CommandLineRunner commandLineRunner(VoitureRepository voitureRepository){
        return  args -> {
            List<Voiture> voitureList = List.of(
                    Voiture.builder()
                            .price(12)
                            .dateAchat(LocalDate.now())
                            .personneId(Long.valueOf(1))
                            .build(),
                    Voiture.builder()
                            .price(2)
                            .dateAchat(LocalDate.now().plusDays(1))
                            .personneId(Long.valueOf(2))
                            .build()
            );

            voitureRepository.saveAll(voitureList);

        };
    }*/
}
