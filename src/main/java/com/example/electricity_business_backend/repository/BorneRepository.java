package com.example.electricity_business_backend.repository;

import com.example.electricity_business_backend.model.Borne;
import com.example.electricity_business_backend.model.EtatBorneEnum;
import com.example.electricity_business_backend.model.Lieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorneRepository extends JpaRepository<Borne, Long> {
/*    List<Borne> findByLieu(Lieu lieu);

    List<Borne> findByEtat(Borne etatBorne);

    List<Borne> findByOccupee(Borne occupee);

    List<Borne> findByLieuAndEtat(Lieu lieu, Borne etatBorne);*/
}
