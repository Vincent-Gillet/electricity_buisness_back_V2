package com.example.electricity_business_backend.controller;

import com.example.electricity_business_backend.dto.BorneDTO;
import com.example.electricity_business_backend.mapper.EntityMapper;
import com.example.electricity_business_backend.model.Borne;
import com.example.electricity_business_backend.model.EtatBorneEnum;
import com.example.electricity_business_backend.service.BorneService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(BorneController.class)
class BorneControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BorneService borneService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private EntityMapper mapper;

    @BeforeEach
    public void setup() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void testGetAllBornes_whenBornesExists_shouldReturnAllBorne() throws Exception {
        Borne borne1 = new Borne(
                1L,
                "Borne 1",
                BigDecimal.valueOf(48.8754905),
                BigDecimal.valueOf(2.246170156),
                BigDecimal.valueOf(2.0),
                BigDecimal.valueOf(10),
                "Instruction 1",
                false,
                EtatBorneEnum.LIBRE,
                false,
                LocalDateTime.parse("2023-10-01T10:00:00"),
                LocalDateTime.parse("2023-10-31T00:00:00"),
                null,
                null,
                null,
                new HashSet<>()
        );

        Borne borne2 = new Borne(
                2L,
                "Borne 2",
                BigDecimal.valueOf(48.8754905),
                BigDecimal.valueOf(2.246170156),
                BigDecimal.valueOf(3.0),
                BigDecimal.valueOf(20),
                "Instruction 2",
                false,
                EtatBorneEnum.OCCUPEE,
                false,
                LocalDateTime.parse("2023-10-01T10:00:00"),
                LocalDateTime.parse("2023-10-31T00:00:00"),
                null,
                null,
                null,
                new HashSet<>()
        );

        List<Borne> bornes = Arrays.asList(borne1, borne2);

        when(borneService.getAllBornes()).thenReturn(bornes);

        MvcResult result = this.mockMvc.perform(get("/api/bornes"))// On exécute une requête GET
                .andExpect(status().isOk())// On vérifie que le statut est 200 OK
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        String json = result.getResponse().getContentAsString();
        List<Borne> bornesResponse = objectMapper.readValue(json, new TypeReference<>(){});

        assertNotNull(bornesResponse);
        assertEquals(2, bornesResponse.size());

    }


    @Test
    void testGetAllBornes_whenBornesDoesNotExist_shouldReturnAllBornesIsOk() throws Exception {
        List<Borne> bornes = Collections.emptyList();

        when(borneService.getAllBornes()).thenReturn(bornes);

        mockMvc.perform(get("/api/bornes"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetBorneById_whenBorneExists_shouldReturnBorne() throws Exception {
        Borne borne = new Borne(
                1L,
                "Borne 1",
                BigDecimal.valueOf(48.8754905),
                BigDecimal.valueOf(2.246170156),
                BigDecimal.valueOf(2.0),
                BigDecimal.valueOf(10),
                "Instruction 1",
                false,
                EtatBorneEnum.LIBRE,
                false,
                LocalDateTime.parse("2023-10-01T10:00:00"),
                LocalDateTime.parse("2023-10-31T00:00:00"),
                null,
                null,
                null,
                new HashSet<>()
        );

        BorneDTO borneDTO = new BorneDTO(
                1L,
                "Borne 1",
                BigDecimal.valueOf(48.8754905),
                BigDecimal.valueOf(2.246170156),
                BigDecimal.valueOf(2.0),
                BigDecimal.valueOf(10),
                "Instruction 1",
                false,
                EtatBorneEnum.LIBRE,
                false,
                LocalDateTime.parse("2023-10-01T10:00:00"),
                LocalDateTime.parse("2023-10-31T00:00:00")
        );


        when(borneService.getBorneById(1L)).thenReturn(Optional.of(borne));
        when(mapper.toDTO(borne)).thenReturn(borneDTO);

        mockMvc.perform(get("/api/bornes/1"))// On exécute une requête GET
                .andExpect(status().isOk())// On vérifie que le statut est 200 OK
                .andExpect(jsonPath("$.nomBorne").value("Borne 1"))// On vérifie le contenu du JSON de la réponse
                .andExpect(jsonPath("$.surPied").value(false));
    }

    @Test
    void testGetBorneById_whenBorneDoesNotExist_shouldReturnNotFound() throws Exception {
        // Arrange// On définit le comportement du mock pour un ID qui n'existe pas
        when(borneService.getBorneById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(get("/api/bornes/1"))
                .andExpect(status().isNotFound());// On vérifie que le statut est 404 Not Found
    }

    @Test
    void testPostBorne_whenBorneIsValid_shouldReturnCreatedBorne() throws Exception {
        BorneDTO borneDTO = new BorneDTO(
                1L,
                "Borne 1",
                BigDecimal.valueOf(48.8754905),
                BigDecimal.valueOf(2.246170156),
                BigDecimal.valueOf(2.0),
                BigDecimal.valueOf(10),
                "Instruction 1",
                false,
                EtatBorneEnum.LIBRE,
                false,
                LocalDateTime.parse("2023-10-01T10:00:00"),
                LocalDateTime.parse("2023-10-31T00:00:00")
        );

        Borne borne = new Borne(
                1L,
                "Borne 1",
                BigDecimal.valueOf(48.8754905),
                BigDecimal.valueOf(2.246170156),
                BigDecimal.valueOf(2.0),
                BigDecimal.valueOf(10),
                "Instruction 1",
                false,
                EtatBorneEnum.LIBRE,
                false,
                LocalDateTime.parse("2023-10-01T10:00:00"),
                LocalDateTime.parse("2023-10-31T00:00:00"),
                null,
                null,
                null,
                new HashSet<>()
        );

        when(borneService.saveBorne(any())).thenReturn(borne);
        when(mapper.toEntity(any(BorneDTO.class))).thenReturn(borne);
        when(mapper.toDTO(any(Borne.class))).thenReturn(borneDTO);

        mockMvc.perform(post("/api/bornes")
                        .content(objectMapper.writeValueAsString(borneDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nomBorne").value("Borne 1"))
                .andExpect(jsonPath("$.surPied").value(false));
    }


    @Test
    void testPostBorne_whenBorneIsInvalid_shouldReturnBadRequest() throws Exception {
        BorneDTO borneDTO = new BorneDTO();

        mockMvc.perform(post("/api/bornes")
                        .content(objectMapper.writeValueAsString(borneDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}