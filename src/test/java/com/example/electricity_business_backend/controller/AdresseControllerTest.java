/*
package com.example.electricity_business_backend.controller;

import com.example.electricity_business_backend.dto.AdresseDTO;
import com.example.electricity_business_backend.mapper.EntityMapper;
import com.example.electricity_business_backend.model.Adresse;
import com.example.electricity_business_backend.service.AdresseService;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(AdresseController.class)
class AdresseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AdresseService adresseService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private EntityMapper mapper;

    @BeforeEach
    public void setup() {
        objectMapper.registerModule(new JavaTimeModule());
    }


    */
/**
     * Test pour vérifier que la méthode getAllAdresses retourne toutes les adresses existantes.
     * On simule le service pour retourner une liste d'adresses prédéfinies.
     *//*

    @Test
    void testGetAllAdresses_whenAdressesExists_shouldReturnAllAdresses() throws Exception {
        Adresse adresse1 = new Adresse(
                1L,
                "Maison Principale",
                "3 rue de la Paix",
                "15000",
                "Aurillac",
                "France",
                "Auvergne-Rhône-Alpes",
                "",
                "1er étage",
                null,
                null
        );

        Adresse adresse2 = new Adresse(
                2L,
                "Bureau",
                "10 avenue des Champs-Élysées",
                "75008",
                "Paris",
                "France",
                "Île-de-France",
                "Près de l'Arc de Triomphe",
                null,
                null,
                null
        );

        List<Adresse> adresses = Arrays.asList(adresse1, adresse2);

        when(adresseService.getAllAdresses()).thenReturn(adresses);

        MvcResult result = this.mockMvc.perform(get("/api/adresses"))
                .andExpect(status().isOk())// On vérifie que le statut est 200 OK
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        String json = result.getResponse().getContentAsString();
        List<Adresse> adressesResponse = objectMapper.readValue(json, new TypeReference<>() {
        });

        assertNotNull(adressesResponse);
        assertEquals(2, adressesResponse.size());

    }

    */
/**
     * Test pour vérifier que la méthode getAllAdresses retourne une liste vide si aucune adresse n'existe.
     * On simule le service pour retourner une liste vide.
     *//*

    @Test
    void testGetAllAdresses_whenNoAdressesExists_shouldReturnEmptyList() throws Exception {

        List<Adresse> adresses = Collections.emptyList();

        when(adresseService.getAllAdresses()).thenReturn(adresses);

        mockMvc.perform(get("/api/adresses"))
                .andExpect(status().isOk());
    }

    */
/**
     * Test de la méthode getAdresseById() du contrôleur UtilisateurController
     * Vérifie que la récupération d'une adresse existante retourne l'adresse correspondante.
     *//*

    @Test
    void testGetAdresseById_whenAdresseExists_shouldReturnAdresse() throws Exception {
        Adresse adresse = new Adresse(
                1L,
                "Maison Principale",
                "3 rue de la Paix",
                "15000",
                "Aurillac",
                "France",
                "Auvergne-Rhône-Alpes",
                "azerty",
                "1er étage",
                null,
                null
        );

        AdresseDTO adresseDTO = new AdresseDTO(
                1L,
                "Maison Principale",
                "3 rue de la Paix",
                "15000",
                "Aurillac",
                "France",
                "Auvergne-Rhône-Alpes",
                "azerty",
                "1er étage",
                null
        );

        when(adresseService.getAdresseById(1L)).thenReturn(Optional.of(adresse));
        when(mapper.toDTO(adresse)).thenReturn(adresseDTO);

        mockMvc.perform(get("/api/adresses/1"))
                .andExpect(status().isOk())// On vérifie que le statut est 200 OK
                .andExpect(jsonPath("$.ville").value("Aurillac"))// On vérifie le contenu du JSON de la réponse
                .andExpect(jsonPath("$.pays").value("France"));
    }

    */
/**
     * Test de la méthode getAdresseById() du contrôleur UtilisateurController
     * Vérifie que la récupération d'une adresse inexistante retourne un statut HTTP 404 Not Found.
     *//*

    @Test
    void testGetAdresseById_whenAdresseDoesNotExist_shouldReturnNotFound() throws Exception{

        when(adresseService.getAdresseById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/adresses/1"))
                .andExpect(status().isNotFound()); // On vérifie que le statut est 404 Not Found
    }

    */
/**
     * Test de la méthode postAdresse() du contrôleur UtilisateurController
     * Vérifie que la création d'une nouvelle adresse retourne l'adresse créée.
     * On simule le service pour retourner l'adresse créée.
     *//*

    @Test
    void testPostAdresse_whenValidAdresse_shouldReturnCreatedAdresse() throws Exception {
        Adresse adresse = new Adresse(
                null,
                "Maison Principale",
                "3 rue de la Paix",
                "15000",
                "Aurillac",
                "France",
                "Auvergne-Rhône-Alpes",
                "",
                "1er étage",
                null,
                null
        );

        AdresseDTO adresseDTO = new AdresseDTO(
                null,
                "Maison Principale",
                "3 rue de la Paix",
                "15000",
                "Aurillac",
                "France",
                "Auvergne-Rhône-Alpes",
                "",
                "1er étage",
                null
        );

        when(adresseService.saveAdresse(any())).thenReturn(adresse);
        when(mapper.toEntity(any(AdresseDTO.class))).thenReturn(adresse);
        when(mapper.toDTO(any(Adresse.class))).thenReturn(adresseDTO);

        mockMvc.perform(post("/api/adresses")
                        .content(objectMapper.writeValueAsString(adresseDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ville").value("Aurillac"))
                .andExpect(jsonPath("$.pays").value("France"));
    }



    */
/**
     * Test de la méthode updateAdresse() du contrôleur UtilisateurController
     * Vérifie que la mise à jour d'une adresse existante retourne l'adresse mise à jour.
     * On simule le service pour retourner l'adresse mise à jour.
     *//*

    @Test
    void testUpdateAdresse_whenAdresseExists_shouldReturnUpdatedAdresse() throws Exception {

        Adresse adresse = new Adresse(
                1L,
                "Maison Principale",
                "3 rue de la Paix",
                "15000",
                "Aurillac",
                "France",
                "Auvergne-Rhône-Alpes",
                "",
                "1er étage",
                null,
                null
        );

        AdresseDTO adresseDTO = new AdresseDTO(
                1L,
                "Maison Principale",
                "3 rue de la Paix",
                "15000",
                "Aurillac",
                "France",
                "Auvergne-Rhône-Alpes",
                "",
                "1er étage",
                null
        );

        when(adresseService.existsById(1L)).thenReturn(true);

        when(adresseService.updateAdresse(any(), any())).thenReturn(adresse);
        when(mapper.toEntity(any(AdresseDTO.class))).thenReturn(adresse);
        when(mapper.toDTO(any(Adresse.class))).thenReturn(adresseDTO);

        mockMvc.perform(put("/api/adresses/1")
                        .content(objectMapper.writeValueAsString(adresseDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ville").value("Aurillac"))
                .andExpect(jsonPath("$.pays").value("France"));
    }


    */
/**
     * Test de la méthode updateAdresse() du contrôleur UtilisateurController
     * Vérifie que la mise à jour d'une adresse inexistante retourne un statut HTTP 400 Bad Request.
     *//*

    @Test
    void testUpdateAdresse_whenAdresseDoesNotExist_shouldReturnError() throws Exception {

        AdresseDTO adresseDTO = new AdresseDTO();

        when(adresseService.existsById(1L)).thenReturn(false);

        mockMvc.perform(put("/api/adresses/1")
                        .content(objectMapper.writeValueAsString(adresseDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    */
/**
     * Test de la méthode deleteAdresseById() du contrôleur UtilisateurController
     * Vérifie que la suppression d'une adresse existante retourne un statut HTTP 204 No Content.
     *//*

    @Test
    void testDeleteAdresse_whenAdresseExists_shouldReturnDeletedAdresse() throws Exception {

        when(adresseService.existsById(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/adresses/1"))
                .andExpect(status().isNoContent()); // On vérifie que le statut est 204 No Content
    }

    */
/**
     * Test de la méthode deleteAdresseById() du contrôleur UtilisateurController
     * Vérifie que la suppression d'une adresse inexistante retourne un statut HTTP 404 Not Found.
     *//*

    @Test
    void testDeleteAdresse_whenAdresseDoesNotExist_shouldReturnNotFound() throws Exception {

        when(adresseService.existsById(1L)).thenReturn(false);

        mockMvc.perform(delete("/api/adresses/1"))
                .andExpect(status().isNotFound());
    }

}*/
