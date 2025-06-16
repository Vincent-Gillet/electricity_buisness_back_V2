package com.example.electricity_business_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "reparateurs")
@NoArgsConstructor
@AllArgsConstructor
public class Reparateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nom_reparateur")
    private String nomReparateur;

    @NotBlank
    @Column(name = "email_reparateur")
    private String emailReparateur;

    @NotBlank
    @Column(name = "mot_de_passe_reparateur")
    private String motDePasseReparateur;

    @ManyToMany(mappedBy = "reparateurs")
    private Set<Borne> bornes = new HashSet<>();

}
