package com.example.departement.services.Etudiantsevice;

import com.example.departement.models.Etudiant;

import java.util.List;

public interface EtudiantService {
    List<Etudiant> getAllEtudiants();
    Etudiant getEtudiantById(Long id);
    Etudiant saveEtudiant(Etudiant etudiant);
    void deleteEtudiant(Long id);
}
