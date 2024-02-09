package com.example.voitureservice.controllers;

import com.example.voitureservice.clients.PersonneRestClient;
import com.example.voitureservice.entities.Voiture;
import com.example.voitureservice.serviceImpl.VoitureServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voitures")
public class VoitureController {
    private final VoitureServiceImpl voitureService;


    public VoitureController(VoitureServiceImpl voitureService) {
        this.voitureService = voitureService;
    }

    @PostMapping
    public ResponseEntity<Voiture> saveVoiture(@RequestBody Voiture voiture){
        return  new ResponseEntity<>(voitureService.saveVoiture(voiture), HttpStatus.OK);
    }

    @GetMapping
    public  ResponseEntity<List<Voiture>> getAllVoiture(){
        return new ResponseEntity<>(voitureService.getAllVoiture(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voiture> getVoiture(@PathVariable Long id){
        return new ResponseEntity<>(voitureService.getVoiture(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Voiture> updateVoiture(@PathVariable Long id){
        return new ResponseEntity<>(voitureService.updateVoiture(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVoiture(@PathVariable Long id){
        voitureService.deleteVoiture(id);
        return new ResponseEntity<>("Voiture suppimé avec succes", HttpStatus.OK);
    }

}
