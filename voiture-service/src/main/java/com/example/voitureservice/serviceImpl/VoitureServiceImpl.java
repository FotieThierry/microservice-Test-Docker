package com.example.voitureservice.serviceImpl;

import com.example.voitureservice.clients.PersonneRestClient;
import com.example.voitureservice.entities.Voiture;
import com.example.voitureservice.modals.Personne;
import com.example.voitureservice.repositories.VoitureRepository;
import com.example.voitureservice.services.VoitureService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VoitureServiceImpl implements VoitureService {
    private final VoitureRepository voitureRepository;
    private final PersonneRestClient personneRestClient;

    public VoitureServiceImpl(VoitureRepository voitureRepository, PersonneRestClient personneRestClient) {
        this.voitureRepository = voitureRepository;
        this.personneRestClient = personneRestClient;
    }

    @Override
    public Voiture saveVoiture(Voiture voiture) {
        return voitureRepository.save(voiture);
    }

    @Override
    public void deleteVoiture(Long id) {
        voitureRepository.deleteById(id);
    }

    @Override
    public List<Voiture> getAllVoiture() {
        List<Voiture> voitureList = voitureRepository.findAll();
        voitureList.stream()
                .map(voiture -> {
                    Personne personne = personneRestClient.findPersonneById(voiture.getPersonneId());
                    voiture.setPersonne(personne);
                    return voiture;
                })
                .collect(Collectors.toList());

        return voitureList;
    }

    @Override
    public Voiture updateVoiture(Long id) {
        Voiture updatedVoiture = voitureRepository.findById(id).orElseThrow(()-> new RuntimeException("voiture introuvable"));
        Voiture voiture = Voiture.builder()
                .id(updatedVoiture.getId())
                .price(updatedVoiture.getPrice())
                .dateAchat(updatedVoiture.getDateAchat())
                .personne(updatedVoiture.getPersonne())
                .personneId(updatedVoiture.getPersonneId())
                .build();

        return voiture;
    }

    @Override
    public Voiture getVoiture(Long id) {
        Voiture voiture = voitureRepository.findById(id).orElseThrow(() -> new RuntimeException("voiture introuvable"));
        Personne personne = personneRestClient.findPersonneById(voiture.getPersonneId());
        voiture.setPersonne(personne);
        return voiture;
    }


}
