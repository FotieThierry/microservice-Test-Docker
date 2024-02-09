package com.example.personneservice.repository;

import com.example.personneservice.entities.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PersonneRepository extends JpaRepository<Personne, Long>{

    Optional<Personne> findPersonneByPrenom(String prenom);

    @Query(value = "SELECT AVG(p.date) from Personne p")
    Double getAvgAgePersonne();
}
