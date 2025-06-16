package com.example.electricity_business_backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO pour l'entité ServiceSupDTO
 * Actuellement vide, mais peut être étendu pour inclure des propriétés spécifiques
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceSupDTO {

    private Long id;

    @NotNull(message = "Le nom du service supplémentaire est obligatoire")
    private String nomServiceSup;

    @NotNull(message = "Le tarif du service supplémentaire est obligatoire")
    private BigDecimal tarifServiceSup;

    @NotNull(message = "La description du service supplémentaire est obligatoire")
    private String descriptionServiceSup;

}
