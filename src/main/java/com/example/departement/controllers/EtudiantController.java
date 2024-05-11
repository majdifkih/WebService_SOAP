package com.example.departement.controllers;

import com.example.departement.models.Etudiant;
import com.example.departement.services.Etudiantsevice.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {
    @Autowired
    private EtudiantService etudiantService;

    @GetMapping("/all")
    public List<Etudiant> getAllEtudiants() {
        return etudiantService.getAllEtudiants();
    }

    @GetMapping("/{id}")
    public Etudiant getEtudiantById(@PathVariable Long id) {
        return etudiantService.getEtudiantById(id);
    }

    @PostMapping("/addetudiant")
    public Etudiant createEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.saveEtudiant(etudiant);
    }

    @PutMapping("/editetudiant/{id}")
    public Etudiant updateEtudiant(@PathVariable Long id, @RequestBody Etudiant etudiant) {
        etudiant.setId(id);
        return etudiantService.saveEtudiant(etudiant);
    }

    @DeleteMapping("/deletudiant/{id}")
    public ResponseEntity<String> deleteEtudiant(@PathVariable Long id) {
        // Supprimer l'étudiant par son identifiant
        etudiantService.deleteEtudiant(id);
        return ResponseEntity.status(HttpStatus.OK).body("Étudiant supprimé avec succès");
    }
}
