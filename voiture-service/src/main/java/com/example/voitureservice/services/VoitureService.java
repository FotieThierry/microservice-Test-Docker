package com.example.voitureservice.services;

import com.example.voitureservice.entities.Voiture;

import java.util.List;

public interface VoitureService {
    Voiture saveVoiture(Voiture voiture);
    void deleteVoiture(Long id);
    List<Voiture> getAllVoiture();
    Voiture updateVoiture(Long id);
    Voiture getVoiture(Long id);
}
