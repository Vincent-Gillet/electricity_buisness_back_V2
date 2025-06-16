package com.example.electricity_business_backend.service;

import com.example.electricity_business_backend.model.Reservation;
import com.example.electricity_business_backend.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {
    private final ReservationRepository reservationRepository;

    /**
     * Récupère toutes les réservations.
     * @return Une liste de toutes les réservations
     */
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    /**
     * Récupère une réservation par son ID.
     * @param id L'identifiant de la réservation à récupérer
     * @return Un Optional contenant la réservation si trouvée, sinon vide
     */
    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }


    /**
     * Crée une nouvelle réservation.
     * @param reservation La réservation à enregistrer
     * @return La réservation enregistrée
     */
    @Transactional
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }


    /**
     * Met à jour une réservation existante.
     * @param id L'identifiant de la réservation à mettre à jour
     * @param reservation La réservation avec les nouvelles informations
     * @return La réservation mise à jour
     */
    public Reservation updateReservation(Long id, Reservation reservation) {
        reservation.setId(id);
        return reservationRepository.save(reservation);
    }

    /**
     * Supprime une réservation.
     * @param id L'identifiant de la réservation à supprimer
     */
    public void deleteReservationById(Long id) {
        reservationRepository.deleteById(id);
    }

    /**
     * Vérifie si une réservation existe par son ID.
     * @param id L'identifiant de la réservation à vérifier
     * @return true si la réservation existe, false sinon
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return reservationRepository.existsById(id);
    }

}
