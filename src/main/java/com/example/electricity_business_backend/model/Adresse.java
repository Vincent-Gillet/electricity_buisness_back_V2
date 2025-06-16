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

    @Column(name = "nom_adresse")
    @NotNull
    private String nomAdresse;

    @NotNull
    private String adresse;

    @Column(name = "code_postal")
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
    @JoinColumn(name = "id_lieu")
    private Lieu lieu;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;
}
