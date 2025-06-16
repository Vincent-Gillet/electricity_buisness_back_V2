package com.example.electricity_business_backend.service;

import com.example.electricity_business_backend.model.Borne;
import com.example.electricity_business_backend.repository.BorneRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BorneService {
    private final BorneRepository borneRepository;

    /**
     * Récupère tous les bornes.
     * @return Une liste de toutes les bornes
     */
    @Transactional(readOnly = true)
    public List<Borne> getAllBornes() {
        return borneRepository.findAll();
    }

    /**
     * Récupère un vehicule par son ID.
     * @param id L'identifiant du vehicule à récupérer
     * @return Un Optional contenant le vehicule si trouvé, sinon vide
     */
    public Optional<Borne> getBorneById(Long id) {
        return borneRepository.findById(id);
    }

    /**
     * Crée un nouveau vehicule.
     * @param borne La borne à enregistrer
     * @return La borne enregistrée
     */
    public Borne saveBorne(Borne borne) {
        return borneRepository.save(borne);
    }

    /**
     * Met à jour un vehicule existant.
     * @param id L'identifiant du vehicule à mettre à jour
     * @param borne La borne avec les nouvelles informations
     * @return La borne mise à jour
     */
    public Borne updateBorne(Long id, Borne borne) {
        borne.setId(id);
        return borneRepository.save(borne);
    }

    /**
     * Supprime un utilisateur.
     * @param id L'identifiant de la borne à supprimer
     */
    public void deleteBorneById(Long id) {
        borneRepository.deleteById(id);
    }

    /**
     * Vérifie si une borne existe.
     * @param id L'identifiant de la borne à vérifier
     * @return true si la borne existe, sinon false
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return borneRepository.existsById(id);
    }


}
