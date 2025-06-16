package com.example.electricity_business_backend.service;

import com.example.electricity_business_backend.model.Vehicule;
import com.example.electricity_business_backend.repository.VehiculeRepository;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@Data
@Service
class VehiculeServiceTest {
    @Mock
    private VehiculeRepository vehiculeRepository;

    @InjectMocks
    private VehiculeService vehiculeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void vehiculeFindAll() {
        // 1. Préparation (On donne ses instructions au cascadeur)Lieu lieu = new Lieu();
        Vehicule vehicule1 = new Vehicule(Long.parseLong("12010"), "AB123CD", "Tesla", "Model S", Year.of(2019), 20, null);
        Vehicule vehicule2 = new Vehicule(Long.parseLong("12020"), "AC123CF", "Tesla", "Model Y", Year.of(2015), 30, null);
        List<Vehicule> vehicules = Arrays.asList(vehicule1, vehicule2);

        when(vehiculeRepository.findAll()).thenReturn(vehicules);

        // 2. Action (On teste l'acteur principal)
        List<Vehicule> result = vehiculeService.getAllVehicules();

        // 3. Vérification (L'acteur a-t-il bien joué sa scène ?)
        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(2);
    }

    @Test
    public void vehiculeFindById() {
        // 1. Préparation (On donne ses instructions au cascadeur)Lieu lieu = new Lieu();
        Vehicule vehicule = new Vehicule(Long.parseLong("12010"), "AB123CD", "Tesla", "Model S", Year.of(2019), 20, null);
        when(vehiculeRepository.findById(12010L)).thenReturn(Optional.of(vehicule));

        // 2. Action (On teste l'acteur principal)
        Optional<Vehicule> result = vehiculeService.getVehiculeById(12010L);

        // 3. Vérification (L'acteur a-t-il bien joué sa scène ?)
        assertThat(result).isPresent();
        verify(vehiculeRepository, times(1)).findById(Long.parseLong("12010"));
    }

    @Test
    public void vehiculeCreated() {
        Vehicule vehicule = new Vehicule(12010L, "AB123CD", "Tesla", "Model S", Year.of(2019), 20, null);

        vehiculeService.saveVehicule(vehicule);
        verify(vehiculeRepository, times(1)).save(vehicule);
    }

    @Test
    public void vehiculeUpdate() {
        Vehicule vehicule = new Vehicule(12010L, "AB123CD", "Tesla", "Model S", Year.of(2019), 20, null);
        when(vehiculeRepository.findById(12010L)).thenReturn(Optional.of(vehicule));
        when(vehiculeRepository.save(any(Vehicule.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Vehicule newVehicule = new Vehicule(12010L, "AB123CD", "Renault", "Zoe", Year.of(2019), 20, null);
        vehiculeService.updateVehicule(12010L, newVehicule);

        verify(vehiculeRepository, times(1)).save(newVehicule);
    }

    @Test
    public void vehiculeRemoveById() {
        Vehicule vehicule = new Vehicule(12010L, "AB123CD", "Tesla", "Model S", Year.of(2019), 20, null);

        when(vehiculeRepository.findById(12010L)).thenReturn(Optional.of(vehicule));
        vehiculeService.deleteVehiculeById(12010L);

        verify(vehiculeRepository).deleteById(12010L);
    }

}