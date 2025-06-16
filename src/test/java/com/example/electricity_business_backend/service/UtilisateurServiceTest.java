package com.example.electricity_business_backend.service;

import com.example.electricity_business_backend.model.RoleUtilisateurEnum;
import com.example.electricity_business_backend.model.Utilisateur;
import com.example.electricity_business_backend.repository.UtilisateurRepository;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@Data
@Service
class UtilisateurServiceTest {
    @Mock
    private UtilisateurRepository utilisateurRepository;

    @InjectMocks
    private UtilisateurService utilisateurService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void utilisateurFindAll() {
        Utilisateur utilisateur1 = new Utilisateur(1L, "Doe", "John", "john_deo", "johndeo@gmail.com", "password", RoleUtilisateurEnum.UTILISATEUR, LocalDate.parse("2002-01-01"), "FR4730003000301731589138L36", false, null, null, null);
        Utilisateur utilisateur2 = new Utilisateur(2L, "Jean", "Paul", "jean_paul", "jeanpaul@gmail.com", "azerty", RoleUtilisateurEnum.UTILISATEUR, LocalDate.parse("2003-01-01"), "FR4730003000301731589138L36", false, null, null, null);

        List<Utilisateur> utilisateurs = Arrays.asList(utilisateur1, utilisateur2);

        when(utilisateurRepository.findAll()).thenReturn(utilisateurs);

        List<Utilisateur> result = utilisateurService.getAllUtilisateurs();

        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(2);
    }

    @Test
    public void utilisateurFindById() {
        Utilisateur utilisateur = new Utilisateur(1L, "Doe", "John", "john_deo", "johndeo@gmail.com", "password", RoleUtilisateurEnum.UTILISATEUR, LocalDate.parse("2002-01-01"), "FR4730003000301731589138L36", false, null, null, null);
        utilisateur.setId(12010L);

        when(utilisateurRepository.findById(12010L)).thenReturn(Optional.of(utilisateur));

        // 2. Action (On teste l'acteur principal)
        Optional<Utilisateur> result = utilisateurService.getUtilisateurById(12010L);

        // 3. Vérification (L'acteur a-t-il bien joué sa scène ?)
        assertThat(result).isPresent();
        verify(utilisateurRepository, times(1)).findById(Long.parseLong("12010"));
    }

    @Test
    public void utilisateurCreated() {
        Utilisateur utilisateur = new Utilisateur(1L, "Doe", "John", "john_deo", "johndeo@gmail.com", "password", RoleUtilisateurEnum.UTILISATEUR, LocalDate.parse("2002-01-01"), "FR4730003000301731589138L36", false, null, null, null);

        utilisateurService.saveUtilisateur(utilisateur);
        verify(utilisateurRepository, times(1)).save(utilisateur);
    }

    @Test
    public void utilisateurUpdate() {
        Utilisateur utilisateur = new Utilisateur(1L, "Doe", "John", "john_deo", "johndeo@gmail.com", "password", RoleUtilisateurEnum.UTILISATEUR, LocalDate.parse("2002-01-01"), "FR4730003000301731589138L36", false, null, null, null);
        utilisateur.setId(12010L);

        when(utilisateurRepository.findById(12010L)).thenReturn(Optional.of(utilisateur));
        when(utilisateurRepository.save(any(Utilisateur.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Utilisateur newUtilisateur = new Utilisateur(2L, "Jean", "Paul", "jean_paul", "jeanpaul@gmail.com", "azerty", RoleUtilisateurEnum.UTILISATEUR, LocalDate.parse("2003-01-01"), "FR4730003000301731589138L36", false, null, null, null);
        utilisateurService.updateUtilisateur(12010L, newUtilisateur);

        verify(utilisateurRepository, times(1)).save(newUtilisateur);
    }


    @Test
    public void utilisateurRemoveById() {
        Utilisateur utilisateur = new Utilisateur(1L, "Doe", "John", "john_deo", "johndeo@gmail.com", "password", RoleUtilisateurEnum.UTILISATEUR, LocalDate.parse("2002-01-01"), "FR4730003000301731589138L36", false, null, null, null);
        utilisateur.setId(12010L);

        when(utilisateurRepository.findById(12010L)).thenReturn(Optional.of(utilisateur));
        utilisateurService.deleteUtilisateurById(12010L);

        verify(utilisateurRepository).deleteById(12010L);
    }


}