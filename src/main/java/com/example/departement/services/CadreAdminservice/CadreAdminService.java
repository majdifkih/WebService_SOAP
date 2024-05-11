package com.example.departement.services.CadreAdminservice;

import com.example.departement.models.CadreAdmin;

import java.util.List;

public interface CadreAdminService {
    List<CadreAdmin> getAllCadresAdmin();
    CadreAdmin getCadreAdminById(Long id);
    CadreAdmin saveCadreAdmin(CadreAdmin cadreAdmin);
    void deleteCadreAdmin(Long id);
}
