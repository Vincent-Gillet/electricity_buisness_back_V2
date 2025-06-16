package com.example.electricity_business_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "services_sup")
@NoArgsConstructor
@AllArgsConstructor
public class ServiceSup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom_service_sup")
    @NotNull(message = "Le nom du service supplémentaire est obligatoire")
    private String nomServiceSup;

    @Column(name = "tarif_service_sup")
    @NotNull(message = "Le tarif du service supplémentaire est obligatoire")
    private BigDecimal tarifServiceSup;

    @Column(name = "description_service_sup")
    @NotNull(message = "La description du service supplémentaire est obligatoire")
    private String descriptionServiceSup;

    @OneToMany(mappedBy = "serviceSups")
    private Set<Media> media = new HashSet<>();

}