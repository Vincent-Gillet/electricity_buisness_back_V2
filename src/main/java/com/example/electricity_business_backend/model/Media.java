package com.example.electricity_business_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "medias")
@NoArgsConstructor
@AllArgsConstructor
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomMedia;


    @Column(name = "url", length = 500, nullable = false)
    @NotBlank(message = "L'URL est obligatoire")
    private String url;

    @Column(name = "type", length = 50, nullable = false)
    @NotBlank(message = "Le type est obligatoire")
    private String type;

    @Column(name = "description", columnDefinition = "TEXT")
    private String descriptionMedia;

    @Column(name = "taille")
    private String taille;

    @Column(name = "date_creation", nullable = false)
    private LocalDateTime dateCreation = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "serviceSups")
    private ServiceSup serviceSups;

    @ManyToOne
    @JoinColumn(name = "borne")
    private Borne borne;

    @ManyToMany(mappedBy = "medias")
    private List<Lieu> lieux;
}
