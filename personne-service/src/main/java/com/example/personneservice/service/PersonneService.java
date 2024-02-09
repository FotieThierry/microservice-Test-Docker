package com.example.personneservice.service;

import com.example.personneservice.Dto.PersonneDto;
import com.example.personneservice.entities.Personne;

import java.util.List;

public interface PersonneService {

    PersonneDto getPersonneByPrenom(String prenom);
    String getPersonneByKeyAndIdAndPaylod(Long id, String key, Personne personne);
    Double getAverageAge();
    List<PersonneDto> getAllPersonnes();
    Personne savePersonne(Personne personneDto);
    Personne getPersonneById(Long id);

}
