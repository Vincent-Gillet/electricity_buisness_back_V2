package com.example.electricity_business_backend.repository;

import com.example.electricity_business_backend.model.Reparateur;
import com.example.electricity_business_backend.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReparateurRepository extends JpaRepository<Reparateur, Long> {

    /**
     * Finds a Reparateur by their email address.
     *
     * @param email the email address of the Reparateur
     * @return an Optional containing the Reparateur if found, or empty if not found
     */
    Optional<Reparateur> findByEmailReparateur(String email);


}
