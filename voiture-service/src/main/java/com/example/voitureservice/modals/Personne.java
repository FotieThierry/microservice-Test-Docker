package com.example.voitureservice.modals;

import com.example.voitureservice.enums.Sexe;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Personne {
    private Long id;
    private String nom;
    private String prenom;
    private int date;
    private Sexe sexe;
}
