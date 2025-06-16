package com.example.electricity_business_backend.controller;

import com.example.electricity_business_backend.dto.VehiculeDTO;
import com.example.electricity_business_backend.mapper.EntityMapper;
import com.example.electricity_business_backend.model.Vehicule;
import com.example.electricity_business_backend.service.VehiculeService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.Year;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Classe de test pour le contrôleur VehiculeController.
 * Teste les méthodes du contrôleur pour s'assurer qu'elles fonctionnent correctement.
 */
@WebMvcTest(VehiculeController.class)
class VehiculeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private VehiculeService vehiculeService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private EntityMapper mapper;

    @BeforeEach
    public void setup() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    /**
     * Test de la méthode getAllVehicules() du contrôleur VehiculeController.
     * Vérifie que la méthode renvoie tous les véhicules existants.
     */
    @Test
    void testGetAllVehicules_whenVehiculesExists_shouldReturnAllVehicules() throws Exception {
        Vehicule vehicule1 = new Vehicule(
                12010L,
                "AB123CD",
                "Tesla",
                "Model S",
                Year.of(2019),
                20,
                null
        );

        Vehicule vehicule2 = new Vehicule(
                12020L,
                "AC123CF",
                "Tesla",
                "Model Y",
                Year.of(2015),
                30,
                null
        );

        List<Vehicule> vehicules = Arrays.asList(vehicule1, vehicule2);

        when(vehiculeService.getAllVehicules()).thenReturn(vehicules);

        MvcResult result = this.mockMvc.perform(get("/api/vehicules"))// On exécute une requête GET
                .andExpect(status().isOk())// On vérifie que le statut est 200 OK
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        String json = result.getResponse().getContentAsString();
        List<Vehicule> vehiculesResponse = objectMapper.readValue(json, new TypeReference<>(){});

        assertNotNull(vehiculesResponse);
        assertEquals(2, vehiculesResponse.size());

    }

    /**
     * Test de la méthode getAllVehicules() du contrôleur VehiculeController.
     * Vérifie que la méthode renvoie une liste vide si aucun véhicule n'existe.
     */
    @Test
    void testGetAllVehicules_whenVehiculesDoesNotExist_shouldReturnEmptyList() throws Exception {

        List<Vehicule> vehicules = Collections.emptyList();

        when(vehiculeService.getAllVehicules()).thenReturn(vehicules);

        mockMvc.perform(get("/api/vehicules"))
                .andExpect(status().isOk());
    }

    /**
     * Test de la méthode getVehiculeById() du contrôleur VehiculeController.
     * Vérifie que la méthode renvoie un véhicule par son ID s'il existe.
     */
    @Test
    void testGetVehiculeById_whenVehiculeExists_shouldReturnVehicule() throws Exception {
        Vehicule vehicule = new Vehicule(
                12010L,
                "AB123CD",
                "Tesla",
                "Model S",
                Year.of(2019),
                20,
                null
        );

        VehiculeDTO vehiculeDTO = new VehiculeDTO(
                12010L,
                "AB123CD",
                "Tesla",
                "Model S",
                Year.of(2019),
                20
        );

        when(vehiculeService.getVehiculeById(12010L)).thenReturn(Optional.of(vehicule));
        when(mapper.toDTO(vehicule)).thenReturn(vehiculeDTO);

        mockMvc.perform(get("/api/vehicules/12010"))
                .andExpect(status().isOk())// On vérifie que le statut est 200 OK
                .andExpect(jsonPath("$.plaqueImmatriculation").value("AB123CD"))// On vérifie le contenu du JSON de la réponse
                .andExpect(jsonPath("$.marque").value("Tesla"));
    }

    /**
     * Test de la méthode getVehiculeById() du contrôleur VehiculeController.
     * Vérifie que la méthode renvoie un statut 404 Not Found si le véhicule n'existe pas.
     */
    @Test
    void testGetVehiculeById_whenVehiculeDoesNotExist_shouldReturnNotFound() throws Exception {
        when(vehiculeService.getVehiculeById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/vehicules/99"))
                .andExpect(status().isNotFound());// On vérifie que le statut est 404 Not Found
    }

    /**
     * Test de la méthode saveVehicule() du contrôleur VehiculeController.
     * Vérifie que la méthode crée un nouveau véhicule et renvoie le véhicule créé.
     */
    @Test
    void testPostVehicule_whenVehiculeDoesNotExists_shouldReturnVehicule() throws Exception {
        Vehicule vehicule = new Vehicule(
                12010L,
                "AB123CD",
                "Tesla",
                "Model S",
                Year.of(2019),
                20,
                null);

        VehiculeDTO vehiculeDTO = new VehiculeDTO(
                12010L,
                "AB123CD",
                "Tesla",
                "Model S",
                Year.of(2019),
                20);

        when(vehiculeService.saveVehicule(any())).thenReturn(vehicule);
        when(mapper.toEntity(any(VehiculeDTO.class))).thenReturn(vehicule);
        when(mapper.toDTO(any(Vehicule.class))).thenReturn(vehiculeDTO);

        mockMvc.perform(post("/api/vehicules")
                        .content(objectMapper.writeValueAsString(vehiculeDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())// On vérifie que le statut est 200 OK
                .andExpect(jsonPath("$.plaqueImmatriculation").value("AB123CD"))
                .andExpect(jsonPath("$.marque").value("Tesla"));
    }

    @Test
    void testPostVehicule_whenVehiculeDoesNotExists_shouldReturnError() throws Exception {

        VehiculeDTO vehiculeDTO = new VehiculeDTO();

        mockMvc.perform(post("/api/vehicules")
                        .content(objectMapper.writeValueAsString(vehiculeDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());// On vérifie que le statut est 200 OK
    }


    /**
     * Test de la méthode updateVehicule() du contrôleur VehiculeController.
     * Vérifie que la méthode renvoie un statut 400 Bad Request si le véhicule n'est pas valide.
     */
    @Test
    void testUpdateVehicule_whenVehiculeExists_shouldReturnVehicule() throws Exception {

        Vehicule vehicule = new Vehicule(
                12010L,
                "AB123CD",
                "Tesla",
                "Model S",
                Year.of(2019),
                20,
                null);

        Vehicule newVehicule = new Vehicule(
                12010L,
                "AC123CF",
                "Tesla",
                "Model Y",
                Year.of(2015),
                30,
                null);

        VehiculeDTO vehiculeDTO = new VehiculeDTO(
                12010L,
                "AB123CD",
                "Tesla",
                "Model S",
                Year.of(2019),
                20);

        VehiculeDTO newVehiculeDTO = new VehiculeDTO(
                12010L,
                "AC123CF",
                "Tesla",
                "Model Y",
                Year.of(2015),
                30);

        when(vehiculeService.existsById(12010L)).thenReturn(true);

        when(mapper.toEntity(any(VehiculeDTO.class))).thenReturn(newVehicule);
        when(vehiculeService.updateVehicule(eq(12010L), eq(newVehicule))).thenReturn(newVehicule);
        when(mapper.toDTO(any(Vehicule.class))).thenReturn(newVehiculeDTO);

        mockMvc.perform(put("/api/vehicules/12010")
                        .content(objectMapper.writeValueAsString(newVehiculeDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())// On vérifie que le statut est 200 OK
                .andExpect(jsonPath("$.plaqueImmatriculation").value("AC123CF"))
                .andExpect(jsonPath("$.marque").value("Tesla"));
    }

    @Test
    void testUpdateVehicule_whenVehiculeNotExists_shouldReturnError() throws Exception {

        Vehicule vehicule = new Vehicule();
        Vehicule newVehicule = new Vehicule();

        VehiculeDTO vehiculeDTO = new VehiculeDTO();
        VehiculeDTO newVehiculeDTO = new VehiculeDTO();

        when(vehiculeService.existsById(999L)).thenReturn(true);

        when(mapper.toEntity(any(VehiculeDTO.class))).thenReturn(newVehicule);
        when(vehiculeService.updateVehicule(eq(999L), eq(newVehicule))).thenReturn(newVehicule);
        when(mapper.toDTO(any(Vehicule.class))).thenReturn(newVehiculeDTO);

        mockMvc.perform(put("/api/vehicules/999")
                        .content(objectMapper.writeValueAsString(newVehiculeDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());// On vérifie que le statut est 200 OK
    }



    /**
     * Test de la méthode deleteVehicule() du contrôleur VehiculeController.
     * Vérifie que la méthode supprime un véhicule par son ID.
     */
    @Test
    void testDeleteVehicule_whenVehiculeExists_shouldReturnNoContent() throws Exception {
        when(vehiculeService.existsById(12010L)).thenReturn(true);

        mockMvc.perform(delete("/api/vehicules/12010"))
                .andExpect(status().isNoContent());// On vérifie que le statut est 204 No Content
    }


    /**
     * Test de la méthode deleteVehicule() du contrôleur VehiculeController.
     * Vérifie que la méthode renvoie un statut 404 Not Found si le véhicule n'existe pas.
     */
    @Test
    void testDeleteVehicule_whenVehiculeExists_shouldReturnNotFound() throws Exception {
        when(vehiculeService.existsById(12010L)).thenReturn(false);

        mockMvc.perform(delete("/api/vehicules/12010"))
                .andExpect(status().isNotFound());// On vérifie que le statut est 204 No Content
    }


}