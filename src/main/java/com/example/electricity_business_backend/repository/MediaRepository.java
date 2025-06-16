package com.example.electricity_business_backend.repository;

import com.example.electricity_business_backend.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
/*
    List<Media> findByType(String type);
*/
}
