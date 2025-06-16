package com.example.electricity_business_backend.repository;

import com.example.electricity_business_backend.model.ServiceSup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceSupRepository extends JpaRepository<ServiceSup, Long> {
}
