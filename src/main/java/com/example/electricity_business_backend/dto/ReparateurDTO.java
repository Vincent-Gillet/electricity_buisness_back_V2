package com.example.electricity_business_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReparateurDTO {
    private Long id;

    @NotBlank(message = "Le nom de l'utilisateur est obligatoire")
    private String nomReparateur;

    @NotBlank(message = "L'adresse email est obligatoire")
    @Email(message = "L'adresse email doit être valide")
    private String emailReparateur;

}
