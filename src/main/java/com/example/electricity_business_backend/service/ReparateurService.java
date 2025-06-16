package com.example.electricity_business_backend.service;

import com.example.electricity_business_backend.model.Reparateur;
import com.example.electricity_business_backend.repository.ReparateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReparateurService {
    private final ReparateurRepository reparateurRepository;

    /**
     * Récupère tous les reparateurs.
     * @return Une liste de tous les reparateurs
     */
    public List<Reparateur> getAllReparateurs() {
        return reparateurRepository.findAll();
    }

    /**
     * Récupère un reparateur par son ID.
     * @param id L'identifiant du reparateur à récupérer
     * @return Un Optional contenant le reparateur si trouvé, sinon vide
     */
    public Optional<Reparateur> getReparateurById(Long id) {
        return reparateurRepository.findById(id);
    }

    /**
     * Crée un nouveau reparateur.
     * @param reparateur Le reparateur à enregistrer
     * @return Le reparateur enregistré
     */
    @Transactional
    public Reparateur saveReparateur(Reparateur reparateur) {
        return reparateurRepository.save(reparateur);
    }

    /**
     * Met à jour un reparateur existant.
     * @param id L'identifiant du reparateur à mettre à jour
     * @param reparateur Le reparateur avec les nouvelles informations
     * @return Le reparateur mis à jour
     */
    public Reparateur updateReparateur(Long id, Reparateur reparateur) {
        reparateur.setId(id);
        return reparateurRepository.save(reparateur);
    }

    /**
     * Supprime un reparateur.
     * @param id L'identifiant du reparateur à supprimer
     */
    public void deleteReparateurById(Long id) {
        reparateurRepository.deleteById(id);
    }

    /**
     * Vérifie si un reparateur existe.
     * @param id L'identifiant du reparateur à vérifier
     * @return true si le reparateur existe, sinon false
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return reparateurRepository.existsById(id);
    }


}
