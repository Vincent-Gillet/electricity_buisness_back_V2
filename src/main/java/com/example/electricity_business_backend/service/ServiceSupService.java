package com.example.electricity_business_backend.service;

import com.example.electricity_business_backend.model.ServiceSup;
import com.example.electricity_business_backend.repository.ServiceSupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceSupService {
    private final ServiceSupRepository serviceSupRepository;

    /**
     * Récupère tous les services supplémentaires.
     * @return Une liste de tous les services supplémentaires
     */
    public List<ServiceSup> getAllServiceSups() {
        return serviceSupRepository.findAll();
    }

    /**
     * Récupère un service supplémentaire par son ID.
     * @param id L'identifiant du service supplémentaire à récupérer
     * @return Un Optional contenant le service supplémentaire si trouvé, sinon vide
     */
    public Optional<ServiceSup> getServiceSupById(Long id) {
        return serviceSupRepository.findById(id);
    }

    /**
     * Crée un nouveau service supplémentaire.
     * @param serviceSup Le service supplémentaire à enregistrer
     * @return Le service supplémentaire enregistré
     */
    @Transactional
    public ServiceSup saveServiceSup(ServiceSup serviceSup) {
        return serviceSupRepository.save(serviceSup);
    }

    /**
     * Met à jour un service supplémentaire existant.
     * @param id L'identifiant du service supplémentaire à mettre à jour
     * @param serviceSup Le service supplémentaire avec les nouvelles informations
     * @return Le service supplémentaire mis à jour
     */
    public ServiceSup updateServiceSup(Long id, ServiceSup serviceSup) {
        serviceSup.setId(id);
        return serviceSupRepository.save(serviceSup);
    }

    /**
     * Supprime un service supplémentaire par son ID.
     * @param id L'identifiant du service supplémentaire à supprimer
     */
    public void deleteServiceSupById(Long id) {
        serviceSupRepository.deleteById(id);
    }


    /**
     * Vérifie si un service supplémentaire existe par son ID.
     * @param id L'identifiant du service supplémentaire à vérifier
     * @return true si le service supplémentaire existe, sinon false
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return serviceSupRepository.existsById(id);
    }




}
