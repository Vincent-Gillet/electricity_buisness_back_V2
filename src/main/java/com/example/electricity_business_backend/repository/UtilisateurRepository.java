package com.example.electricity_business_backend.repository;

import com.example.electricity_business_backend.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

/*
    Optional<Utilisateur> findByPseudo(String pseudo);
*/

    Optional<Utilisateur> findByUtilisateurEmail(String adresseMail);

/*    boolean existsByPseudo(String pseudo);

    boolean existsByAdresseMail(String adresseMail);*/
}
