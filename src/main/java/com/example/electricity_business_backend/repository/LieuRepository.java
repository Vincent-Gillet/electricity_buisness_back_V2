package com.example.electricity_business_backend.repository;

import com.example.electricity_business_backend.model.Lieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LieuRepository extends JpaRepository<Lieu, Long> {
}
