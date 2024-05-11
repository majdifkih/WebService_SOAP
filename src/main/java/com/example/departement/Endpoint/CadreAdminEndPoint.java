package com.example.departement.Endpoint;
import com.example.departement.*;
import com.example.departement.models.CadreAdmin;
import com.example.departement.services.CadreAdminservice.CadreAdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Component
@Endpoint
public class CadreAdminEndPoint {

    private static final String NAMESPACE_URI = "http://example.com/departement";

    @Autowired
    private CadreAdminService cadreAdminService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addCadreAdminRequest")
    @ResponsePayload
    public AddCadreAdminResponse addCadreAdmin(@RequestPayload AddCadreAdminRequest request) {
        AddCadreAdminResponse response = new AddCadreAdminResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        CadreAdmin cadreAdmin = new CadreAdmin();
        BeanUtils.copyProperties(request.getCadreAdmin(), cadreAdmin);
        cadreAdminService.saveCadreAdmin(cadreAdmin);

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Cadre administratif ajouté avec succès");
        response.setStatus(String.valueOf(serviceStatus));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllCadreAdminRequest")
    @ResponsePayload
    public GetAllCadreAdminResponse getAllCadreAdmin(@RequestPayload GetAllCadreAdminRequest request) {
        GetAllCadreAdminResponse response = new GetAllCadreAdminResponse();
        ListeCadreAdminsType listeCadreAdminsType = new ListeCadreAdminsType();

        List<CadreAdmin> cadreAdmins = cadreAdminService.getAllCadresAdmin();
        for (CadreAdmin cadreAdmin : cadreAdmins) {
            CadreAdminType cadreAdminType = new CadreAdminType();
            BeanUtils.copyProperties(cadreAdmin, cadreAdminType);
            listeCadreAdminsType.getCadreAdmin().add(cadreAdminType);
        }

        response.setCadreAdmins(listeCadreAdminsType);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateCadreAdminRequest")
    @ResponsePayload
    public UpdateCadreAdminResponse updateCadreAdmin(@RequestPayload UpdateCadreAdminRequest request) {
        UpdateCadreAdminResponse response = new UpdateCadreAdminResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        CadreAdminType updatedCadreAdminType = request.getCadreAdmin();
        CadreAdmin cadreAdmin = new CadreAdmin();
        BeanUtils.copyProperties(updatedCadreAdminType, cadreAdmin);
        cadreAdmin.setId(updatedCadreAdminType.getId()); // Assure que l'identifiant est défini

        cadreAdminService.saveCadreAdmin(cadreAdmin);

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Cadre administratif mis à jour avec succès");
        response.setStatus(String.valueOf(serviceStatus));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteCadreAdminRequest")
    @ResponsePayload
    public DeleteCadreAdminResponse deleteCadreAdmin(@RequestPayload DeleteCadreAdminRequest request) {
        DeleteCadreAdminResponse response = new DeleteCadreAdminResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        Long cadreAdminId = request.getId();
        cadreAdminService.deleteCadreAdmin(cadreAdminId);

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Cadre administratif supprimé avec succès");
        response.setStatus(String.valueOf(serviceStatus));

        return response;
    }
}
