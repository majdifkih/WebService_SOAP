package com.example.departement.services.Etudiantsevice;

import com.example.departement.models.Etudiant;
import com.example.departement.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtudiantServiceImpl implements EtudiantService {
    @Autowired
    private EtudiantRepository etudiantRepository;

    @Override
    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant getEtudiantById(Long id) {
        return etudiantRepository.findById(id).orElse(null);
    }

    @Override
    public Etudiant saveEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    @Override
    public boolean deleteEtudiant(Long etudiantId) {
        try {
            // Logique de suppression de l'étudiant
            etudiantRepository.deleteById(etudiantId);
            return true; // Indique que la suppression a réussi
        } catch (Exception e) {
            // Gérer toute exception lors de la suppression
            return false; // Indique que la suppression a échoué
        }
    }

}
