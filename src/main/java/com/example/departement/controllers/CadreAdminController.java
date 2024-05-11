package com.example.departement.controllers;

import com.example.departement.models.CadreAdmin;
import com.example.departement.services.CadreAdminservice.CadreAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cadreadmin")
public class CadreAdminController {
    @Autowired
    private CadreAdminService cadreAdminService;

    @GetMapping
    public List<CadreAdmin> getAllCadreAdmins() {
        return cadreAdminService.getAllCadresAdmin();
    }

    @GetMapping("/{id}")
    public CadreAdmin getCadreAdminById(@PathVariable Long id) {
        return cadreAdminService.getCadreAdminById(id);
    }

    @PostMapping
    public CadreAdmin createCadreAdmin(@RequestBody CadreAdmin cadreAdmin) {
        return cadreAdminService.saveCadreAdmin(cadreAdmin);
    }

    @PutMapping("/{id}")
    public CadreAdmin updateCadreAdmin(@PathVariable Long id, @RequestBody CadreAdmin cadreAdmin) {
        cadreAdmin.setId(id); // Assure que l'id de l'objet correspond à l'id dans l'URL
        return cadreAdminService.saveCadreAdmin(cadreAdmin);
    }

    @DeleteMapping("/{id}")
    public void deleteCadreAdmin(@PathVariable Long id) {
        cadreAdminService.deleteCadreAdmin(id);
    }

}
