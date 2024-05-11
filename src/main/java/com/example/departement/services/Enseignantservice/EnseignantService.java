package com.example.departement.services.Enseignantservice;

import com.example.departement.models.Enseignant;

import java.util.List;

public interface EnseignantService {
    List<Enseignant> getAllEnseignants();
    Enseignant getEnseignantById(Long id);
    Enseignant saveEnseignant(Enseignant enseignant);
    void deleteEnseignant(Long id);
}
