package com.example.electricity_business_backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "utilisateurs")
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("utilisateurNom")
    @NotBlank
    @Length(min = 2, max = 50)
    private String utilisateurNom;

    @NotNull
    @Length(min = 2, max = 50)
    private String prenom;

    @Column(unique = true)
    @NotNull
    private String pseudo;

    @Column(unique = true)
    @Email
    @NotBlank
    private String utilisateurEmail;

    @Column(name = "mot_de_passe")
    @NotBlank
    private String utilisateurMotDePasse;

    @Enumerated
/*
    @NotNull
*/
    private RoleUtilisateurEnum role = RoleUtilisateurEnum.UTILISATEUR;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return utilisateurMotDePasse;
    }

    @Override
    public String getUsername() {
        return utilisateurEmail;
    }

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDeNaissance;

    @Length(min = 27, max = 27)
    private String iban;

    @NotNull
    private Boolean banni = false;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_media")
    private Media media;

/*    @ManyToMany
    private Set<Borne> bornes = new HashSet<>();*/

    @OneToMany
    private Set<Borne> bornes = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Vehicule> vehicule = new HashSet<>();

}