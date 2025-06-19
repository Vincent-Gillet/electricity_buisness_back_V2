package com.example.electricity_business_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "adresses")
@NoArgsConstructor
@AllArgsConstructor
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nomAdresse;

    @NotNull
    private String adresse;

    @NotNull
    private String codePostal;

    @NotNull
    private String ville;

    @NotNull
    private String pays;

    @NotNull
    private String region;

    private String complement;
    private String etage;

    @OneToOne(cascade = CascadeType.ALL)
    private Lieu lieu;

    @ManyToOne
    private Utilisateur utilisateur;
}
