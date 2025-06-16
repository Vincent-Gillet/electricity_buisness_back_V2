package com.example.electricity_business_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "bornes_reparateurs")
@NoArgsConstructor
@AllArgsConstructor
public class BorneReparateur {

    @EmbeddedId
    private BorneReparateurId id = new BorneReparateurId();

    @ManyToOne
    @JoinColumn(name = "reparateurId", referencedColumnName = "id", insertable= false, updatable = false)
    private Reparateur reparateur;

    @ManyToOne
    @JoinColumn(name = "id_borne", referencedColumnName = "id", insertable= false, updatable = false)
    private Borne borne;

    private String reference;

    private LocalDate date_reparation;

    @NotBlank
    private String description;


}
