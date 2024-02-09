package com.example.personneservice.entities;

import com.example.personneservice.enumeration.Sexe;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@Data @AllArgsConstructor @NoArgsConstructor
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   // @NotNull(message = "Le nom est obligatoire")
   //  @NotEmpty
   //  @NotBlank
    private String nom;

    //  @NotNull(message = "Le prénom est obligatoire")
    //   @NotEmpty
    //   @NotBlank
    private String prenom;

    // @PastOrPresent(message = "la date doit être inferieur ou égal à la date du jour")
    private int date;
    @Enumerated(EnumType.STRING)
    private Sexe sexe;

}
