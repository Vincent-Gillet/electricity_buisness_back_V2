package com.example.electricity_business_backend.controller;

import com.example.electricity_business_backend.dto.UtilisateurDTO;
import com.example.electricity_business_backend.mapper.EntityMapper;
import com.example.electricity_business_backend.model.RoleUtilisateurEnum;
import com.example.electricity_business_backend.model.Utilisateur;
import com.example.electricity_business_backend.service.UtilisateurService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Classe de test pour le contrôleur UtilisateurController.
 * Teste les méthodes du contrôleur pour s'assurer qu'elles fonctionnent correctement.
 */
@WebMvcTest(UtilisateurController.class)
class UtilisateurControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UtilisateurService utilisateurService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private EntityMapper mapper;

    @BeforeEach
    public void setup() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    /**
     * Test de la méthode getAllUtilisateurs() du contrôleur UtilisateurController.
     * Vérifie que la méthode renvoie tous les utilisateurs existants.
     */
    @Test
    void testGetAllUtilisateurs_whenUtilisateursExists_shouldReturnAllUtilisateurs() throws Exception {
        Utilisateur utilisateur1 = new Utilisateur(
                1L,
                "Doe",
                "John",
                "john_deo",
                "johndeo@gmail.com",
                "password",
                RoleUtilisateurEnum.UTILISATEUR,
                LocalDate.parse("2002-01-01"),
                "FR4730003000301731589138L36",
                false,
                null,
                null,
                null);

        Utilisateur utilisateur2 = new Utilisateur(
                2L,
                "Jean",
                "Paul",
                "jean_paul",
                "jeanpaul@gmail.com",
                "azerty",
                RoleUtilisateurEnum.UTILISATEUR,
                LocalDate.parse("2003-01-01"),
                "FR4730003000301731589138L36",
                false,
                null,
                null,
                null);

        List<Utilisateur> utilisateurs = Arrays.asList(utilisateur1, utilisateur2);

        when(utilisateurService.getAllUtilisateurs()).thenReturn(utilisateurs);

        MvcResult result = this.mockMvc.perform(get("/api/utilisateurs"))// On exécute une requête GET
                .andExpect(status().isOk())// On vérifie que le statut est 200 OK
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        String json = result.getResponse().getContentAsString();
        List<Utilisateur> utilisateursResponse = objectMapper.readValue(json, new TypeReference<>(){});

        assertNotNull(utilisateursResponse);
        assertEquals(2, utilisateursResponse.size());

    }

    /**
     * Test de la méthode getAllUtilisateurs() du contrôleur UtilisateurController.
     * Vérifie que la méthode renvoie une liste vide si aucun utilisateur n'existe.
     */
    @Test
    void testGetAllUtilisateurs_whenUtilisateursDoesNotExist_shouldReturnAllUtilisateursIsOk() throws Exception {

        List<Utilisateur> utilisateurs = Collections.emptyList();

        when(utilisateurService.getAllUtilisateurs()).thenReturn(utilisateurs);

        mockMvc.perform(get("/api/utilisateurs"))
                .andExpect(status().isOk());
    }

    /**
     * Test de la méthode getUtilisateurById() du contrôleur UtilisateurController.
     * Vérifie que la méthode renvoie un utilisateur par son ID s'il existe.
     */
    @Test
    void testGetUtilisateurById_whenUtilisateurExists_shouldReturnUtilisateur() throws Exception {
        // Arrange (Préparation)
        Utilisateur utilisateur = new Utilisateur(
                1L,
                "Doe",
                "John",
                "john_deo",
                "johndeo@gmail.com",
                "password",
                RoleUtilisateurEnum.UTILISATEUR,
                LocalDate.parse("2002-01-01"),
                "FR4730003000301731589138L36",
                false,
                null,
                null,
                null);

        UtilisateurDTO utilisateurDTO = new UtilisateurDTO(
                1L,
                "Doe",
                "John",
                "john_deo",
                RoleUtilisateurEnum.UTILISATEUR,
                "johndeo@gmail.com",
                LocalDate.parse("2002-01-01"),
                "FR4730003000301731589138L36",
                false);

        // On définit le comportement de notre mock
        when(utilisateurService.getUtilisateurById(1L)).thenReturn(Optional.of(utilisateur));
        when(mapper.toDTO(utilisateur)).thenReturn(utilisateurDTO);

        // Act & Assert (Action & Vérification)
        mockMvc.perform(get("/api/utilisateurs/1"))// On exécute une requête GET
                .andExpect(status().isOk())// On vérifie que le statut est 200 OK
                .andExpect(jsonPath("$.utilisateurNom").value("Doe"))// On vérifie le contenu du JSON de la réponse
                .andExpect(jsonPath("$.pseudo").value("john_deo"));
    }


    /**
     * Test de la méthode getUtilisateurById() du contrôleur UtilisateurController.
     * Vérifie que la méthode renvoie un statut 404 Not Found si l'utilisateur n'existe pas.
     */
    @Test
    void testGetUtilisateurById_whenUtilisateurDoesNotExist_shouldReturnNotFound() throws Exception {
    // Arrange// On définit le comportement du mock pour un ID qui n'existe pas
        when(utilisateurService.getUtilisateurById(99L)).thenReturn(Optional.empty());

    // Act & Assert
        mockMvc.perform(get("/api/utilisateurs/99"))
                .andExpect(status().isNotFound());// On vérifie que le statut est 404 Not Found
    }

    /**
     * Test de la méthode saveUtilisateur() du contrôleur UtilisateurController.
     * Vérifie que la méthode crée un nouvel utilisateur et renvoie le nouvel utilisateur créé.
     */
    @Test
    void testPostUtilisateur_whenUtilisateurDoesNotExists_shouldReturnUtilisateur() throws Exception {
        // Arrange
        Utilisateur utilisateur = new Utilisateur(
                1L,
                "Doe",
                "John",
                "john_deo",
                "johndeo@gmail.com",
                "password",
                RoleUtilisateurEnum.UTILISATEUR,
                LocalDate.parse("2002-01-01"),
                "FR4730003000301731589138L36",
                false,
                null,
                null,
                null);

        UtilisateurDTO utilisateurDTO = new UtilisateurDTO(
                1L,
                "Doe",
                "John",
                "john_deo",
                RoleUtilisateurEnum.UTILISATEUR,
                "johndeo@gmail.com",
                LocalDate.parse("2002-01-01"),
                "FR4730003000301731589138L36",
                false);

        when(utilisateurService.saveUtilisateur(any())).thenReturn(utilisateur);
        when(mapper.toEntity(any(UtilisateurDTO.class))).thenReturn(utilisateur);
        when(mapper.toDTO(any(Utilisateur.class))).thenReturn(utilisateurDTO);

        // Act & Assert
        mockMvc.perform(post("/api/utilisateurs")
                        .content(objectMapper.writeValueAsString(utilisateurDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.utilisateurNom", is("Doe")))
                .andExpect(jsonPath("$.pseudo", is("john_deo")));
    }

    @Test
    void testPostUtilisateur_whenUtilisateurAlreadyExists_shouldReturnBadRequest() throws Exception {

        Utilisateur utilisateur = new Utilisateur();
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();

        when(utilisateurService.saveUtilisateur(any())).thenReturn(utilisateur);

        mockMvc.perform(post("/api/utilisateurs")
                        .content(objectMapper.writeValueAsString(utilisateurDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    /**
     * Test de la méthode updateUtilisateur() du contrôleur UtilisateurController.
     * Vérifie que la méthode met à jour un utilisateur existant et renvoie l'utilisateur mis à jour.
     */
    @Test
    void testUpdateUtilisateur_whenUtilisateurExists_shouldReturnUtilisateur() throws Exception {
        // Arrange (Préparation)
        Utilisateur utilisateur = new Utilisateur(
                1L,
                "Doe",
                "John",
                "john_deo",
                "johndeo@gmail.com",
                "password",
                RoleUtilisateurEnum.UTILISATEUR,
                LocalDate.parse("2002-01-01"),
                "FR4730003000301731589138L36",
                false,
                null,
                null,
                null
        );

        UtilisateurDTO utilisateurDTO = new UtilisateurDTO(
                1L,
                "Doe",
                "John",
                "john_deo",
                RoleUtilisateurEnum.UTILISATEUR,
                "johndeo@gmail.com",
                LocalDate.parse("2002-01-01"),
                "FR4730003000301731589138L36",
                false
        );

        when(utilisateurService.existsById(1L)).thenReturn(true);

        when(utilisateurService.updateUtilisateur(any(), any())).thenReturn(utilisateur);
        when(mapper.toEntity(any(UtilisateurDTO.class))).thenReturn(utilisateur);
        when(mapper.toDTO(any(Utilisateur.class))).thenReturn(utilisateurDTO);

        // Act & Assert (Action & Vérification)
        mockMvc.perform(put("/api/utilisateurs/1")
                        .content(objectMapper.writeValueAsString(utilisateurDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())// On vérifie que le statut est 200 OK
                .andExpect(jsonPath("$.utilisateurNom").value("Doe"))
                .andExpect(jsonPath("$.pseudo").value("john_deo"));
    }


    /**
     * Test de la méthode updateUtilisateur() du contrôleur UtilisateurController.
     * Vérifie que la méthode renvoie une erreur si l'utilisateur n'existe pas.
     */
    @Test
    void testUpdateUtilisateur_whenUtilisateurNotExist_shouldReturnError() throws Exception {
        // Arrange (Préparation)

        UtilisateurDTO newUtilisateurDTO = new UtilisateurDTO();

        when(utilisateurService.existsById(1L)).thenReturn(false);

        // Act & Assert (Action & Vérification)
        mockMvc.perform(put("/api/utilisateurs/1")
                        .content(objectMapper.writeValueAsString(newUtilisateurDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());// On vérifie que le statut est 400 Bad Request
    }


    /**
     * Test de la méthode deleteUtilisateurById() du contrôleur UtilisateurController.
     * Vérifie que la méthode supprime un utilisateur par son ID s'il existe.
     */
    @Test
    void testDeleteUtilisateurById_whenUtilisateurExists_shouldReturnUtilisateur() throws Exception {

        when(utilisateurService.existsById(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/utilisateurs/1"))
                .andExpect(status().isNoContent()); // On vérifie que le statut est 204 No Content
    }

    /**
     * Test de la méthode deleteUtilisateurById() du contrôleur UtilisateurController.
     * Vérifie que la méthode renvoie un statut 404 Not Found si l'utilisateur n'existe pas.
     */
    @Test
    void testDeleteUtilisateurById_whenUtilisateurDoesNotExist_shouldNotFound() throws Exception {

        when(utilisateurService.existsById(1L)).thenReturn(false);

        mockMvc.perform(delete("/api/utilisateurs/1"))
                .andExpect(status().isNotFound()); // On vérifie que le statut est 404 Not Found
    }

}