package com.example.electricity_business_backend.dto;

import com.example.electricity_business_backend.model.RoleUtilisateurEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

/**
 * DTO pour l'entité Utilisateur
 * Exclut le mot de passe et évite les relations circulaires
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDTO {
    private Long id;

    @NotBlank(message = "Le nom de l'utilisateur est obligatoire")
    private String utilisateurNom;
    
    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;
    
    @NotBlank(message = "Le pseudo est obligatoire")
    private String pseudo;
    
    @NotNull(message = "Le rôle est obligatoire")
    private RoleUtilisateurEnum role;
    
    @NotBlank(message = "L'adresse email est obligatoire")
    @Email(message = "L'adresse email doit être valide")
    private String utilisateurEmail;
    
    @Past(message = "La date de naissance doit être dans le passé")
    private LocalDate dateDeNaissance;
    
    private String iban;


    private Boolean banni;
}