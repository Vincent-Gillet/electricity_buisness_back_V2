package com.example.electricity_business_backend.utils;

import com.example.electricity_business_backend.dto.BorneDTO;
import com.example.electricity_business_backend.dto.ReservationDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.cos;
import static java.lang.Math.sqrt;
import static org.aspectj.runtime.internal.Conversions.doubleValue;

public class BorneUtils {

    public static List<Long> get_nearby_borne(List<BorneDTO> liste_borne, BigDecimal longitude, BigDecimal latitude, double rayon) {

        List<Long> liste_borne_resultat = new ArrayList<>();

        for (int i = 0; i < liste_borne.toArray().length; i++) {
            double longitude_borne = doubleValue(liste_borne.get(i).getLongitude());
            double latitude_borne = doubleValue(liste_borne.get(i).getLatitude());

            double x = (doubleValue(longitude)-longitude_borne)*cos((doubleValue(latitude)+latitude_borne)/2);
            double y = latitude_borne-doubleValue(latitude);
            double z = sqrt((x*x)+(y*y));
            double d = 1.852*60*z;

/*            System.out.println("i : " + i);

            System.out.println("longitude : " + longitude);
            System.out.println("latitude : " + latitude);
            System.out.println("longitude_borne : " + longitude_borne);
            System.out.println("latitude_borne : " + latitude_borne);

            System.out.println("x : " + x);
            System.out.println("y : " + y);
            System.out.println("z : " + z);
            System.out.println("d : " + d);
            System.out.println("rayon : " + rayon);*/

            if (d <= rayon) {
                liste_borne_resultat.add(liste_borne.get(i).getId());
            }
        }

        return liste_borne_resultat;
    }

    public static List<Long> get_free_borne(List<ReservationDTO> list_reservation, LocalDateTime time) {
/*
        throw new ExceptionInInitializerError("Pas encore conçu");
*/
        List<Long> liste_borne_resultat = new ArrayList<>();

        for (int i = 0; i < list_reservation.toArray().length; i++) {
            LocalDateTime dateStart = list_reservation.get(i).getDateDebutReservation();
            LocalDateTime dateEnd = list_reservation.get(i).getDateFinReservation();

            if (time.isBefore(dateStart) && time.isBefore(dateEnd) || time.isAfter(dateStart) && time.isAfter(dateEnd)) {

                liste_borne_resultat.add(list_reservation.get(i).getBorne());
            }

/*            System.out.println("list_reservation.get(i).getDateDebut() : " + list_reservation.get(i).getDateDebutReservation());
            System.out.println("list_reservation.get(i).getDateFin() : " + list_reservation.get(i).getDateFinReservation());
            System.out.println("time : " + time);*/
        }

        return liste_borne_resultat;

    }

/*    public static List<Borne> get_free_nearby_borne(List<Borne> liste_borne, double longitude, double latitude, double rayon, List<Reservation> list_reservation, LocalDateTime time) {

        List<Borne> liste_borne_resultat = new ArrayList<>();

        List<Borne> nearby_borne = get_nearby_borne(liste_borne, latitude, longitude, rayon);

        List<Borne> free_borne = get_free_borne(list_reservation, time);

        for (int i = 0; i < list_reservation.toArray().length; i++) {
            if () {
            liste_borne_resultat.add(list_reservation.get(i).getBorne());
        }

        return liste_borne_resultat;
    }*/

}
