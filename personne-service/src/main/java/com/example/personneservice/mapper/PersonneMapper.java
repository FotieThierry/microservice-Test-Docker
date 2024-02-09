package com.example.personneservice.mapper;

import com.example.personneservice.Dto.PersonneDto;
import com.example.personneservice.entities.Personne;

import java.util.List;



public interface PersonneMapper {
    PersonneDto personnetoDto(Personne personne);
    List<PersonneDto> personnestoDtos(List<Personne> personnes);
   Personne dtoToPersonne(PersonneDto personneDto);
    List<Personne> dtosToPersonnes(List<PersonneDto> personneDtos);

}
