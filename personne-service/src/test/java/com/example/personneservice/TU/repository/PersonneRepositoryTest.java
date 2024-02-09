package com.example.personneservice.TU.repository;

import com.example.personneservice.entities.Personne;
import com.example.personneservice.enumeration.Sexe;
import com.example.personneservice.repository.PersonneRepository;
import com.example.personneservice.serviceImpl.PersonneServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PersonneRepositoryTest {
    @Mock
    private PersonneRepository personneRepository;

    @InjectMocks
    private PersonneServiceImpl personneService;

    @Test
    public void savePersonneTest(){
        //when
        Personne personne = Personne.builder()
                .sexe(Sexe.FEMININ)
                .nom("fotie")
                .prenom("thierry")
                .date(10)
                .build();



        //and
        Personne personneSaved = personneRepository.save(personne);

        //then
        Assertions.assertThat(personneSaved).isNotNull();
        Assertions.assertThat(personneSaved.getId()).isGreaterThan(0);
        Assertions.assertThat(personneSaved.getNom()).isEqualTo("fotie");

    }


}
