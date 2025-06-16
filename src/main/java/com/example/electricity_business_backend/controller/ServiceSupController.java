package com.example.electricity_business_backend.controller;

import com.example.electricity_business_backend.dto.ServiceSupDTO;
import com.example.electricity_business_backend.mapper.EntityMapper;
import com.example.electricity_business_backend.model.ServiceSup;
import com.example.electricity_business_backend.service.ServiceSupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceSupController {

    private final ServiceSupService serviceSupService;
    private final EntityMapper mapper;

    /**
     * Récupère tous les services supplémentaires.
     * GET /api/services
     * @return Une liste de tous les services supplémentaires
     */
    @GetMapping
    public ResponseEntity<List<ServiceSupDTO>> getAllServiceSups() {
        List<ServiceSup> serviceSups = serviceSupService.getAllServiceSups();
        List<ServiceSupDTO> serviceSupsDTO = serviceSups.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(serviceSupsDTO);
    }

    /**
     * Récupère un service supplémentaire par son ID.
     * GET /api/services/{id}
     * @param id L'identifiant du service supplémentaire à récupérer
     * @return Le service supplémentaire correspondant à l'ID, ou un statut HTTP 404 Not Found si non trouvé
     */
    @GetMapping("/{id}")
    public ResponseEntity<ServiceSupDTO> getServiceSupById(@PathVariable Long id) {
        return serviceSupService.getServiceSupById(id)
                .map(serviceSup -> ResponseEntity.ok(mapper.toDTO(serviceSup)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crée un nouveau service supplémentaire.
     * POST /api/services
     * @param serviceSupDTO Le service supplémentaire à créer
     * @return Le service supplémentaire créé avec un statut HTTP 201 Created
     */
    @PostMapping
    public ResponseEntity<ServiceSupDTO> saveServiceSup(@Valid @RequestBody ServiceSupDTO serviceSupDTO) {
        ServiceSup serviceSup = mapper.toEntity(serviceSupDTO);
        ServiceSup savedServiceSup = serviceSupService.saveServiceSup(serviceSup);
        ServiceSupDTO savedDTO = mapper.toDTO(savedServiceSup);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDTO);
    }

    /**
     * Met à jour un service supplémentaire existant.
     * PUT /api/services/{id}
     * @param id L'identifiant du service supplémentaire à mettre à jour
     * @param serviceSupDTO Le service supplémentaire avec les nouvelles informations
     * @return Le service supplémentaire mis à jour, ou un statut HTTP 404 Not Found si non trouvé
     */
    @PutMapping("/{id}")
    public ResponseEntity<ServiceSupDTO> updateServiceSup(@PathVariable Long id, @Valid @RequestBody ServiceSupDTO serviceSupDTO) {
        if (!serviceSupService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ServiceSup serviceSup = mapper.toEntity(serviceSupDTO);
        ServiceSup updatedServiceSup = serviceSupService.updateServiceSup(id, serviceSup);
        ServiceSupDTO updatedDTO = mapper.toDTO(updatedServiceSup);
        return ResponseEntity.ok(updatedDTO);    }

    /**
     * Supprime un service supplémentaire par son ID.
     * DELETE /api/services/{id}
     * @param id L'identifiant du service supplémentaire à supprimer
     * @return Une réponse vide avec le HTTP 204 No Content si la suppression est réussie, ou 404 Not Found si l'ID n'existe pas
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        if (!serviceSupService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        serviceSupService.deleteServiceSupById(id);
        return ResponseEntity.noContent().build();
    }

}
