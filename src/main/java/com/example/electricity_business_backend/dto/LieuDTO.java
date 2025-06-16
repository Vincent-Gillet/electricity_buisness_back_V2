package com.example.electricity_business_backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO pour l'entité Lieu
 * Représentation simplifiée sans relations circulaires
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LieuDTO {
    private Long id;
    
    @NotBlank(message = "Les instructions sont obligatoires")
    private String instructions;
} 