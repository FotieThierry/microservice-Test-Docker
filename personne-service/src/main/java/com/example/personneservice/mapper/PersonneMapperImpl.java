package com.example.personneservice.mapper;

import com.example.personneservice.Dto.PersonneDto;
import com.example.personneservice.entities.Personne;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonneMapperImpl implements PersonneMapper {

    @Override
    public PersonneDto personnetoDto(Personne personne) {
        if ( personne == null ) {
            return null;
        }

        PersonneDto.PersonneDtoBuilder personneDto = PersonneDto.builder();

        personneDto.id( personne.getId() );
        personneDto.prenom( personne.getPrenom() );

        return personneDto.build();
    }

    @Override
    public List<PersonneDto> personnestoDtos(List<Personne> personnes) {
        if ( personnes == null ) {
            return null;
        }

        List<PersonneDto> list = new ArrayList( personnes.size() );
        for ( Personne personne : personnes ) {
            list.add( personnetoDto( personne ) );
        }

        return list;
    }

    @Override
    public Personne dtoToPersonne(PersonneDto personneDto) {
        if ( personneDto == null ) {
            return null;
        }

        Personne.PersonneBuilder personne = Personne.builder();

        personne.id( personneDto.getId() );
        personne.prenom( personneDto.getPrenom() );

        return personne.build();
    }

    @Override
    public List<Personne> dtosToPersonnes(List<PersonneDto> personneDtos) {
        if ( personneDtos == null ) {
            return null;
        }

        List<Personne> list = new ArrayList<Personne>( personneDtos.size() );
        for ( PersonneDto personneDto : personneDtos ) {
            list.add( dtoToPersonne( personneDto ) );
        }

        return list;
    }
}
