package com.superbiblio.services;

import com.superbiblio.model.Utilisateur;
import com.superbiblio.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Transactional
    public boolean disableUtilisateur(int utilisateurId) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId).orElse(null);

        if (utilisateur != null) {
            utilisateur.setActive(false);
            utilisateurRepository.save(utilisateur);
            return true;
        } else {
            return false;
        }
    }
}

