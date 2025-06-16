package com.example.electricity_business_backend.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class ReservationId {
    private Long utilisateurId;
    private Long borneId;
    private Long serviceSupId;
    private Long vehicleId;

}
