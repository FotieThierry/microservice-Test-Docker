package com.example.personneservice.serviceImpl;

import com.example.personneservice.Dto.PersonneDto;
import com.example.personneservice.entities.Personne;
import com.example.personneservice.mapper.PersonneMapper;
import com.example.personneservice.repository.PersonneRepository;
import com.example.personneservice.service.PersonneService;
import jakarta.transaction.Transactional;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PersonneServiceImpl implements PersonneService {

    private final PersonneRepository personneRepository;
    private final PersonneMapper personneMapper;
    private final MessageSource messageSource;


    public PersonneServiceImpl(PersonneRepository personneRepository, PersonneMapper personneMapper, MessageSource messageSource) {
        this.personneRepository = personneRepository;
        this.personneMapper = personneMapper;
        this.messageSource = messageSource;
    }

    @Override
    public PersonneDto getPersonneByPrenom(String prenom) {
        Personne personne = personneRepository.findPersonneByPrenom(prenom).orElseThrow(() -> new RuntimeException(""));
        return personneMapper.personnetoDto(personne);
    }

    @Override
    public String getPersonneByKeyAndIdAndPaylod(Long id, String key, Personne personne) {
        Personne personne1 = personneRepository.findById(id).orElseThrow(() -> new RuntimeException(messageSource.getMessage("error.personne.introuvable", null, LocaleContextHolder.getLocale())));

        if(!key.equals("La force n'est pas avec toi !")){
            throw new RuntimeException(messageSource.getMessage("error.personne.key", null, LocaleContextHolder.getLocale()));
        }

        if(personne1.getDate()< 18){
            throw new RuntimeException(messageSource.getMessage("error.personne.age", null, LocaleContextHolder.getLocale()));
        }

        return messageSource.getMessage("personne.service.reponse", null, LocaleContextHolder.getLocale());
    }

    @Override
    public Double getAverageAge() {
        System.out.println("in controller");
        return personneRepository.getAvgAgePersonne();
    }

    @Override
    public List<PersonneDto> getAllPersonnes() {
        return personneRepository.findAll().stream().map(personne -> personneMapper.personnetoDto(personne)).toList();
    }

    @Override
    public Personne savePersonne(Personne personne) {
        return personneRepository.save(personne);
    }

    @Override
    public Personne getPersonneById(Long id) {
        return personneRepository.findById(id).orElseThrow(()-> new RuntimeException("personne introuvable"));
    }
}
