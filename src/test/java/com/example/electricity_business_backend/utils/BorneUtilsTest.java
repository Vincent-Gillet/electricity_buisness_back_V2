package com.example.electricity_business_backend.utils;

import com.example.electricity_business_backend.dto.BorneDTO;
import com.example.electricity_business_backend.dto.ReservationDTO;
import com.example.electricity_business_backend.model.EtatBorneEnum;
import com.example.electricity_business_backend.model.StatutReservationEnum;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BorneUtilsTest {
    List<BorneDTO> list_borne = List.of(
            new BorneDTO(1L, "borne 1", BigDecimal.valueOf(2.287592), BigDecimal.valueOf(48.862725), BigDecimal.valueOf(3.12), BigDecimal.valueOf(12), "Instruction 1", false, EtatBorneEnum.LIBRE, false, LocalDateTime.parse("2025-04-10T10:00"), LocalDateTime.parse("2025-04-10T12:00")),
            new BorneDTO(2L, "borne 2", BigDecimal.valueOf(2.288650), BigDecimal.valueOf(48.862725), BigDecimal.valueOf(3.12), BigDecimal.valueOf(12), "Instruction 2", false, EtatBorneEnum.LIBRE, false, LocalDateTime.parse("2025-04-10T10:00"), LocalDateTime.parse("2025-04-10T12:00")),
            new BorneDTO(3L, "borne 3", BigDecimal.valueOf(2.287592), BigDecimal.valueOf(48.862725), BigDecimal.valueOf(3.12), BigDecimal.valueOf(12), "Instruction 3", false, EtatBorneEnum.LIBRE, false, LocalDateTime.parse("2025-04-10T10:00"), LocalDateTime.parse("2025-04-10T12:00")),
            new BorneDTO(4L, "borne 4", BigDecimal.valueOf(2.287592), BigDecimal.valueOf(48.862725), BigDecimal.valueOf(3.12), BigDecimal.valueOf(12), "Instruction 4", false, EtatBorneEnum.LIBRE, false, LocalDateTime.parse("2025-04-10T10:00"), LocalDateTime.parse("2025-04-10T12:00")),
            new BorneDTO(5L, "borne 5", BigDecimal.valueOf(2.987595), BigDecimal.valueOf(48.862720), BigDecimal.valueOf(3.12), BigDecimal.valueOf(12), "Instruction 5", false, EtatBorneEnum.LIBRE, false, LocalDateTime.parse("2025-04-10T10:00"), LocalDateTime.parse("2025-04-10T12:00")),
            new BorneDTO(6L, "borne 6", BigDecimal.valueOf(1.887591), BigDecimal.valueOf(48.862725), BigDecimal.valueOf(3.12), BigDecimal.valueOf(12), "Instruction 6", false, EtatBorneEnum.LIBRE, false, LocalDateTime.parse("2025-04-10T10:00"), LocalDateTime.parse("2025-04-10T12:00"))
    );

    // Recherche de Borne proche

    @ParameterizedTest
    @CsvSource({
            "2.287592, 48.862725, 5.00",
            "2.287592, 48.862725, 3.00",
            "2.287592, 48.862725, 10.00"
    })
    void findBornesNearby(BigDecimal latitude, BigDecimal longitude, double rayon) {
        List<Long> expected = new ArrayList<>(List.of(
                list_borne.get(0).getId(),
                list_borne.get(1).getId(),
                list_borne.get(2).getId(),
                list_borne.get(3).getId()
        ));

        assertEquals(expected, BorneUtils.get_nearby_borne(list_borne, longitude, latitude, rayon));
    }

    List<ReservationDTO> list_reservation = List.of(
            new ReservationDTO(1L, LocalDateTime.parse("2025-04-10T12:00"), LocalDateTime.parse("2025-04-10T12:20"), StatutReservationEnum.ACCEPTEE, BigDecimal.valueOf(3.12), LocalDateTime.parse("2025-04-10T10:00"), 1L, 1L, 1L, 1L),
            new ReservationDTO(2L, LocalDateTime.parse("2025-04-10T12:00"), LocalDateTime.parse("2025-04-10T18:20"), StatutReservationEnum.ACCEPTEE, BigDecimal.valueOf(3.12), LocalDateTime.parse("2025-04-10T10:00"), 1L, 2L, 1L, 1L),
            new ReservationDTO(3L, LocalDateTime.parse("2025-04-10T12:00"), LocalDateTime.parse("2025-04-10T20:20"), StatutReservationEnum.ACCEPTEE, BigDecimal.valueOf(3.12), LocalDateTime.parse("2025-04-10T10:00"), 1L, 3L, 1L, 1L),
            new ReservationDTO(4L, LocalDateTime.parse("2025-05-10T12:00"), LocalDateTime.parse("2025-05-10T12:20"), StatutReservationEnum.ACCEPTEE, BigDecimal.valueOf(3.12), LocalDateTime.parse("2025-04-10T10:00"), 1L, 4L, 1L, 1L),
            new ReservationDTO(5L, LocalDateTime.parse("2025-06-10T12:00"), LocalDateTime.parse("2025-06-10T12:20"), StatutReservationEnum.ACCEPTEE, BigDecimal.valueOf(3.12), LocalDateTime.parse("2025-04-10T10:00"), 1L, 5L, 1L, 1L)
    );

    // Recherche de Borne libre

    @ParameterizedTest
    @CsvSource({
            "2025-04-10T12:10",
            "2025-04-10T12:10",
            "2025-04-10T12:10"
    })
    void findFreeBornes(LocalDateTime time) {
        List<Long> expected = new ArrayList<>(List.of(
                list_reservation.get(3).getBorne(),
                list_reservation.get(4).getBorne()
        ));

        assertEquals(expected, BorneUtils.get_free_borne(list_reservation, time));
    }


    // Recherche de Borne proche et libre

/*    @ParameterizedTest
    @CsvSource({
            "2.287592, 48.862725, 5.00, 2025-04-10T12:10",
            "2.287592, 48.862725, 3.00, 2025-04-10T12:10",
            "2.287592, 48.862725, 10.00, 2025-04-10T12:10"
    })
    void findFreeBornesAndNearby(double latitude, double longitude, double rayon, LocalDateTime time) {
        List<Borne> expected = new ArrayList<>(List.of(
                list_borne.get(0),
                list_borne.get(2),
                list_borne.get(3)

        ));

        assertEquals(expected, BorneUtils.get_free_nearby_borne(list_borne, longitude, latitude, rayon, list_reservation, time));
    }*/

}