package com.example.departement.services.CadreAdminservice;

import com.example.departement.models.CadreAdmin;
import com.example.departement.repository.CadreAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadreAdminServiceImpl implements CadreAdminService{
    @Autowired
    private CadreAdminRepository cadreAdminRepository;

    @Override
    public List<CadreAdmin> getAllCadresAdmin() {
        return cadreAdminRepository.findAll();
    }

    @Override
    public CadreAdmin getCadreAdminById(Long id) {
        return cadreAdminRepository.findById(id).orElse(null);
    }

    @Override
    public CadreAdmin saveCadreAdmin(CadreAdmin cadreAdmin) {
        return cadreAdminRepository.save(cadreAdmin);
    }

    @Override
    public void deleteCadreAdmin(Long id) {
        cadreAdminRepository.deleteById(id);
    }
}
