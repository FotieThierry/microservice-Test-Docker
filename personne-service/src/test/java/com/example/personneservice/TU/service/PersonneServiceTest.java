package com.example.personneservice.TU.service;

import com.example.personneservice.Dto.PersonneDto;
import com.example.personneservice.entities.Personne;
import com.example.personneservice.enumeration.Sexe;
import com.example.personneservice.mapper.PersonneMapperImpl;
import com.example.personneservice.repository.PersonneRepository;
import com.example.personneservice.serviceImpl.PersonneServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class PersonneServiceTest {
    @Mock
    private PersonneRepository personneRepository;

    @InjectMocks
    private PersonneServiceImpl personneService;

    @Mock
    private PersonneMapperImpl personneMapper;

    @Mock
    private MessageSource messageSource;

    private Personne personne1;
    @BeforeEach
    public void setUp() {
        personne1 = Personne.builder()
                .nom("user 1")
                .prenom("fg")
                .sexe(Sexe.MASCULIN)
                .date(3)
                .build();
    }

    @Test
    public void getAverageTest() {

        Personne personne1 = Personne.builder()
                .nom("user 1")
                .prenom("fg")
                .sexe(Sexe.MASCULIN)
                .date(3)
                .build();

        Personne personne2 = Personne.builder()
                .nom("user 2")
                .prenom("fg")
                .sexe(Sexe.MASCULIN)
                .date(3)
                .build();

        when(personneRepository.getAvgAgePersonne()).thenReturn(Double.valueOf((personne1.getDate() + personne2.getDate()) / 2));

        Double averageAge = personneService.getAverageAge();

        Assertions.assertEquals(averageAge, 3);
        Assertions.assertNotNull(averageAge);

    }

    @Test
    public void getAllPersonnesTest() {
        Personne personne1 = Personne.builder()
                .nom("user 1")
                .prenom("fg")
                .sexe(Sexe.MASCULIN)
                .date(3)
                .build();

        Personne personne2 = Personne.builder()
                .nom("user 2")
                .prenom("fg")
                .sexe(Sexe.MASCULIN)
                .date(3)
                .build();

        when(personneRepository.findAll()).thenReturn(List.of(personne1, personne2));

        PersonneDto personneDto1 = new PersonneDto(Long.valueOf(1), "user 1");
        PersonneDto personneDto2 = new PersonneDto(Long.valueOf(1), "user 2");

        when(personneMapper.personnetoDto(personne1)).thenReturn(personneDto1);
        when(personneMapper.personnetoDto(personne1)).thenReturn(personneDto2);

        List<PersonneDto> result = personneService.getAllPersonnes();

        verify(personneRepository, times(1)).findAll();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.size(), 2);

    }

    @Test
    public void getPersonneByPrenomTest() {
        Personne personne = Personne.builder()
                .id(1L)
                .nom("user 1")
                .prenom("user lastname")
                .sexe(Sexe.MASCULIN)
                .date(12)
                .build();

        PersonneDto personneDto = new PersonneDto(1L, "user lastname");

        when(personneRepository.findPersonneByPrenom("user lastname")).thenReturn(Optional.of(personne));
        when(personneMapper.personnetoDto(personne)).thenReturn(personneDto);

        PersonneDto result = personneService.getPersonneByPrenom("user lastname");
        verify(personneRepository, times(1)).findPersonneByPrenom("user lastname");

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getPrenom(), "user lastname");

    }

    @Test
    public void getPersonneByKeyAndIdAndPaylodTest(){
        Long id = 1L;
        String key1 = "La force n'est pas avec toi !";
        String badKey1 = "La force est avec toi !";

        //simulation des messages
        when(messageSource.getMessage("personne.service.reponse", null, LocaleContextHolder.getLocale())).thenReturn("succes");
        when(messageSource.getMessage("error.personne.key", null, LocaleContextHolder.getLocale())).thenReturn("key error");
        when(messageSource.getMessage("error.personne.age", null, LocaleContextHolder.getLocale())).thenReturn("age error");
        when(messageSource.getMessage("error.personne.introuvable", null, LocaleContextHolder.getLocale())).thenReturn("personne introuvable");

        //utilsateur introuvable
        when(personneRepository.findById(2L)).thenReturn(Optional.empty());
        Personne personneIntrouvable = Personne.builder()
                .id(2L)
                .date(20)
                .build();


       RuntimeException exception1 = Assertions.assertThrows(RuntimeException.class, ()->personneService.getPersonneByKeyAndIdAndPaylod(2L, key1,personneIntrouvable));
        Assertions.assertEquals(exception1.getMessage(), "personne introuvable");

        // clef incorect
        Personne personne = Personne.builder()
                .id(id)
                .date(20)
                .build();
        when(personneRepository.findById(id)).thenReturn(Optional.of(personne));
        RuntimeException exception2 = Assertions.assertThrows(RuntimeException.class, () -> personneService.getPersonneByKeyAndIdAndPaylod(id, badKey1, personne));
        Assertions.assertEquals(exception2.getMessage(), "key error");


        // utilisateur correct
        String result1 = personneService.getPersonneByKeyAndIdAndPaylod(id, key1, personne);
        Assertions.assertNotNull(result1);
        Assertions.assertEquals(result1, "succes");

        //age incorrect
        personne.setDate(10);
        RuntimeException exception3 = Assertions.assertThrows(RuntimeException.class, () -> personneService.getPersonneByKeyAndIdAndPaylod(id, key1, personne));
        Assertions.assertEquals("age error", exception3.getMessage());

        verify(personneRepository, times(3)).findById(id);
        verify(personneRepository, times(1)).findById(2L);

    }


}
