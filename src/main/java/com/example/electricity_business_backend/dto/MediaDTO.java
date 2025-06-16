package com.example.electricity_business_backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

/**
 * DTO pour l'entité Media
 * Représentation simplifiée sans relations circulaires
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaDTO {
    private Long id;
    
    @NotBlank(message = "Le nom du média est obligatoire")
    private String nomMedia;
    
    @NotBlank(message = "Le type de média est obligatoire")
    private String type;
    
    @NotBlank(message = "L'URL du média est obligatoire")
    private String url;
    
    private String descriptionMedia;
    private String taille;
    private LocalDateTime dateCreation;
} 