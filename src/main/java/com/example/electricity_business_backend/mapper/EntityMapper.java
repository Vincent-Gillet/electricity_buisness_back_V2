package com.example.electricity_business_backend.mapper;

import com.example.electricity_business_backend.dto.*;
import com.example.electricity_business_backend.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Mapper pour convertir entre entités JPA et DTOs
 * Évite les références circulaires en contrôlant la sérialisation
 */
@Component
public class EntityMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;


    // === LIEU ===
    public LieuDTO toDTO(Lieu lieu) {
        if (lieu == null) return null;
        return new LieuDTO(lieu.getId(), lieu.getInstructions());
    }

    public Lieu toEntity(LieuDTO dto) {
        if (dto == null) return null;
        Lieu lieu = new Lieu();
        lieu.setId(dto.getId());
        lieu.setInstructions(dto.getInstructions());
        return lieu;
    }

    // === ADRESSE ===
    public AdresseDTO toDTO(Adresse adresse) {
        if (adresse == null) return null;
        return new AdresseDTO(
            adresse.getId(),
            adresse.getNomAdresse(),
            adresse.getAdresse(),
            adresse.getCodePostal(),
            adresse.getVille(),
            adresse.getPays(),
            adresse.getRegion(),
            adresse.getComplement(),
            adresse.getEtage(),
            toDTO(adresse.getLieu())
        );
    }

    public Adresse toEntity(AdresseDTO dto) {
        if (dto == null) return null;
        Adresse adresse = new Adresse();
        adresse.setId(dto.getId());
        adresse.setNomAdresse(dto.getNomAdresse());
        adresse.setAdresse(dto.getAdresse());
        adresse.setCodePostal(dto.getCodePostal());
        adresse.setVille(dto.getVille());
        adresse.setPays(dto.getPays());
        adresse.setRegion(dto.getRegion());
        adresse.setComplement(dto.getComplement());
        adresse.setEtage(dto.getEtage());
        adresse.setLieu(toEntity(dto.getLieu()));
        return adresse;
    }

    // === UTILISATEUR ===
    public UtilisateurDTO toDTO(Utilisateur utilisateur) {
        if (utilisateur == null) return null;
        return new UtilisateurDTO(
            utilisateur.getId(),
            utilisateur.getUtilisateurNom(),
            utilisateur.getPrenom(),
            utilisateur.getPseudo(),
            utilisateur.getRole(),
            utilisateur.getUtilisateurEmail(),
            utilisateur.getDateDeNaissance(),
            utilisateur.getIban(),
            utilisateur.getBanni()
        );
    }

    public Utilisateur toEntity(UtilisateurDTO dto) {
        if (dto == null) return null;
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(dto.getId());
        utilisateur.setUtilisateurNom(dto.getUtilisateurNom());
        utilisateur.setPrenom(dto.getPrenom());
        utilisateur.setPseudo(dto.getPseudo());
        utilisateur.setRole(dto.getRole());
        utilisateur.setUtilisateurEmail(dto.getUtilisateurEmail());
        utilisateur.setDateDeNaissance(dto.getDateDeNaissance());
        utilisateur.setIban(dto.getIban());
        utilisateur.setBanni(dto.getBanni());
        return utilisateur;
    }

    // === UTILISATEUR CREATE ===

    public UtilisateurCreateDTO toCreateDTO(Utilisateur utilisateur) {
        if (utilisateur == null) return null;
        return new UtilisateurCreateDTO(
            utilisateur.getUtilisateurNom(),
            utilisateur.getPrenom(),
            utilisateur.getPseudo(),
            utilisateur.getUtilisateurMotDePasse(),
            utilisateur.getUtilisateurEmail(),
            utilisateur.getDateDeNaissance()
        );
    }

    public Utilisateur toEntity(UtilisateurCreateDTO dto) {
        if (dto == null) return null;
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setUtilisateurNom(dto.getUtilisateurNom());
        utilisateur.setPrenom(dto.getPrenom());
        utilisateur.setPseudo(dto.getPseudo());
/*
        utilisateur.setRole(dto.getRole());
*/
        utilisateur.setRole(RoleUtilisateurEnum.UTILISATEUR);
        utilisateur.setUtilisateurMotDePasse(passwordEncoder.encode(dto.getUtilisateurMotDePasse()));
        utilisateur.setUtilisateurEmail(dto.getUtilisateurEmail());
        utilisateur.setDateDeNaissance(dto.getDateDeNaissance());
/*
        utilisateur.setIban(dto.getIban());
*/
        utilisateur.setIban(null);
        utilisateur.setBanni(false);

        return utilisateur;
    }

    // === BORNE ===
    public BorneDTO toDTO(Borne borne) {
        if (borne == null) return null;
        return new BorneDTO(
            borne.getId(),
            borne.getNomBorne(),
            borne.getLatitude(),
            borne.getLongitude(),
            borne.getTarif(),
            borne.getPuissance(),
            borne.getInstruction(),
            borne.getSurPied(),
            borne.getEtatBorne(),
            borne.getOccupee(),
            borne.getDateCreation(),
            borne.getDerniereMaintenance()
        );
    }

    public Borne toEntity(BorneDTO dto) {
        if (dto == null) return null;
        Borne borne = new Borne();
        borne.setId(dto.getId());
        borne.setNomBorne(dto.getNomBorne());
        borne.setLatitude(dto.getLatitude());
        borne.setLongitude(dto.getLongitude());
        borne.setPuissance(dto.getPuissance());
        borne.setInstruction(dto.getInstruction());
        borne.setSurPied(dto.getSurPied());
        borne.setEtatBorne(dto.getEtatBorne());
        borne.setOccupee(dto.getOccupee());
        borne.setDateCreation(dto.getDateCreation());
        borne.setDerniereMaintenance(dto.getDerniereMaintenance());
        return borne;
    }

    // === MEDIA ===
    public MediaDTO toDTO(Media media) {
        if (media == null) return null;
        return new MediaDTO(
            media.getId(),
            media.getNomMedia(),
            media.getType(),
            media.getUrl(),
            media.getDescriptionMedia(),
            media.getTaille(),
            media.getDateCreation()
        );
    }

    public Media toEntity(MediaDTO dto) {
        if (dto == null) return null;
        Media media = new Media();
        media.setId(dto.getId());
        media.setNomMedia(dto.getNomMedia());
        media.setType(dto.getType());
        media.setUrl(dto.getUrl());
        media.setDescriptionMedia(dto.getDescriptionMedia());
        media.setTaille(dto.getTaille());
        media.setDateCreation(dto.getDateCreation());

        return media;
    }

    // === RESERVATION ===
    public ReservationDTO toDTO(Reservation reservation) {
        if (reservation == null) return null;
        return new ReservationDTO(
            reservation.getId(),
            reservation.getDateDebut(),
            reservation.getDateFin(),
            reservation.getStatut(),
            reservation.getMontantPaye(),
            reservation.getDatePaiement(),
            reservation.getUtilisateur() != null ? reservation.getUtilisateur().getId() : null,
            reservation.getBorne() != null ? reservation.getBorne().getId() : null,
            reservation.getVehicule() != null ? reservation.getVehicule().getId() : null,
            reservation.getServiceSup() != null ? reservation.getServiceSup().getId() : null
        );
    }

    public Reservation toEntity(ReservationDTO dto) {
        if (dto == null) return null;
        Reservation reservation = new Reservation();
        reservation.setId(dto.getId());
        reservation.setDateDebut(dto.getDateDebutReservation());
        reservation.setDateFin(dto.getDateFinReservation());
        reservation.setStatut(dto.getStatut());
        reservation.setMontantPaye(dto.getMontantPaye());
        return reservation;
    }

    // === VEHICULE ===

    public VehiculeDTO toDTO(Vehicule vehicule) {
        if (vehicule == null) return null;
        return new VehiculeDTO(
            vehicule.getId(),
            vehicule.getPlaqueImmatriculation(),
            vehicule.getMarque(),
            vehicule.getModele(),
            vehicule.getAnnee(),
            vehicule.getCapaciteBatterie()
        );
    }

    public Vehicule toEntity(VehiculeDTO dto) {
        if (dto == null) return null;
        Vehicule vehicule = new Vehicule();
        vehicule.setId(dto.getId());
        vehicule.setPlaqueImmatriculation(dto.getPlaqueImmatriculation());
        vehicule.setMarque(dto.getMarque());
        vehicule.setModele(dto.getModele());
        vehicule.setAnnee(dto.getAnnee());
        vehicule.setCapaciteBatterie(dto.getCapaciteBatterie());
        return vehicule;
    }

    // === REPARATEUR ===

    public ReparateurDTO toDTO(Reparateur reparateur) {
        if (reparateur == null) return null;
        return new ReparateurDTO(
            reparateur.getId(),
            reparateur.getNomReparateur(),
            reparateur.getEmailReparateur()
        );
    }

    public Reparateur toEntity(ReparateurDTO dto) {
        if (dto == null) return null;
        Reparateur reparateur = new Reparateur();
        reparateur.setId(dto.getId());
        reparateur.setNomReparateur(dto.getNomReparateur());
        reparateur.setEmailReparateur(dto.getEmailReparateur());
        return reparateur;
    }

    // === SERVICE SUPPLEMENTAIRE ===

    public ServiceSupDTO toDTO(ServiceSup serviceSup) {
        if (serviceSup == null) return null;
        return new ServiceSupDTO(
            serviceSup.getId(),
            serviceSup.getNomServiceSup(),
            serviceSup.getTarifServiceSup(),
            serviceSup.getDescriptionServiceSup()
        );
    }

    public ServiceSup toEntity(ServiceSupDTO dto) {
        if (dto == null) return null;
        ServiceSup serviceSup = new ServiceSup();
        serviceSup.setId(dto.getId());
        serviceSup.setNomServiceSup(dto.getNomServiceSup());
        serviceSup.setTarifServiceSup(dto.getTarifServiceSup());
        serviceSup.setDescriptionServiceSup(dto.getDescriptionServiceSup());
        return serviceSup;
    }


} 