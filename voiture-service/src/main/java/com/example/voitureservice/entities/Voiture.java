package com.example.voitureservice.entities;

import com.example.voitureservice.modals.Personne;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor @NoArgsConstructor @Data
@Builder
public class Voiture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double price;
    private LocalDate dateAchat;
    @Transient
    private Personne personne;
    private Long personneId;
}
