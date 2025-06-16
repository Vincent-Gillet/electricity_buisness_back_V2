package com.example.electricity_business_backend.repository;

import com.example.electricity_business_backend.model.Reparateur;
import com.example.electricity_business_backend.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReparateurRepository extends JpaRepository<Reparateur, Long> {
    /**
     * Repository pour l'entité Reservation.
     * Fournit les opérations CRUD et méthodes de recherche par utilisateur, borne et état.
     */
    @Repository
    interface ReservationRepository extends JpaRepository<Reparateur, Long> {
    /*    List<Reservation> findByUtilisateur(Utilisateur utilisateur);

        List<Reservation> findByBorne(Borne borne);

        List<Reservation> findByEtat(StatutReservationEnum statut);

        List<Reservation> findByUtilisateurAndEtat(Utilisateur utilisateur, StatutReservationEnum statut);

        List<Reservation> findByBorneAndEtat(Borne borne, StatutReservationEnum statut);

        List<Reservation> findByBorneAndActif(Borne borne, Boolean actif);*/
    }
}
