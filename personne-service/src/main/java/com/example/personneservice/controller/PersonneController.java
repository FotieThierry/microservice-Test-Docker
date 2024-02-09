package com.example.personneservice.controller;

import com.example.personneservice.Dto.PersonneDto;
import com.example.personneservice.entities.Personne;
import com.example.personneservice.serviceImpl.PersonneServiceImpl;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/personnes")
public class PersonneController {
    private final PersonneServiceImpl personneService;
    private final MessageSource messageSource;


    public PersonneController(PersonneServiceImpl personneService, MessageSource messageSource) {
        this.personneService = personneService;
        this.messageSource = messageSource;
    }

    @GetMapping
    public ResponseEntity<PersonneDto> getPersonneByName(@RequestParam String last_name){
        return new ResponseEntity<>(personneService.getPersonneByPrenom(last_name), HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<PersonneDto> getAllPersonnes(){
        return personneService.getAllPersonnes();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePersonne(@PathVariable Long id,
                                                 @RequestHeader("key") String key,
                                                 @RequestHeader("Accept-Language") Locale locale,
                                                 @RequestBody Personne personne){


        try {
            return new ResponseEntity<>(personneService.getPersonneByKeyAndIdAndPaylod(id, key, personne), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("age/average")
    public ResponseEntity<Double> getAge(){
        System.out.println("in service");
        return new ResponseEntity<>(personneService.getAverageAge(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Personne> savePersonne(@RequestBody Personne personne){
        System.out.println("in service");
    return new ResponseEntity<>(personneService.savePersonne(personne), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personne> getPersonneById(@PathVariable Long id){
        return new ResponseEntity<>(personneService.getPersonneById(id), HttpStatus.OK);
    }
}
