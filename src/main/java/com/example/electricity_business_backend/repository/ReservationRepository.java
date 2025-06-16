package com.example.electricity_business_backend.repository;

import com.example.electricity_business_backend.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    /*    List<Reservation> findByUtilisateur(Utilisateur utilisateur);

    List<Reservation> findByBorne(Borne borne);

    List<Reservation> findByEtat(StatutReservationEnum statut);

    List<Reservation> findByUtilisateurAndEtat(Utilisateur utilisateur, StatutReservationEnum statut);

    List<Reservation> findByBorneAndEtat(Borne borne, StatutReservationEnum statut);

    List<Reservation> findByBorneAndActif(Borne borne, Boolean actif);*/
}
