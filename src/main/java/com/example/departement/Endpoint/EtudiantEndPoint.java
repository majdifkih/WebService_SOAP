package com.example.departement.Endpoint;
import com.example.departement.*;
import com.example.departement.models.Etudiant;
import com.example.departement.services.Etudiantsevice.EtudiantService;
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
public class EtudiantEndPoint {

    private static final String NAMESPACE_URI = "http://example.com/departement";

    @Autowired
    private EtudiantService etudiantService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addEtudiantRequest")
    @ResponsePayload
    public AddEtudiantResponse addEtudiant(@RequestPayload AddEtudiantRequest request) {
        AddEtudiantResponse response = new AddEtudiantResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        Etudiant etudiant = new Etudiant();
        BeanUtils.copyProperties(request.getEtudiant(), etudiant);
        etudiantService.saveEtudiant(etudiant);

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Etudiant ajouté avec succès");
        response.setStatus(String.valueOf(serviceStatus));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEtudiantByIdRequest")
    @ResponsePayload
    public GetEtudiantByIdResponse getEtudiantById(@RequestPayload GetEtudiantByIdRequest request) {
        GetEtudiantByIdResponse response = new GetEtudiantByIdResponse();
        EtudiantType etudiantType = new EtudiantType();

        Etudiant etudiant = etudiantService.getEtudiantById(request.getId());
        if (etudiant != null) {
            BeanUtils.copyProperties(etudiant, etudiantType);
            response.setEtudiant(etudiantType);
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateEtudiantRequest")
    @ResponsePayload
    public UpdateEtudiantResponse updateEtudiant(@RequestPayload UpdateEtudiantRequest request) {
        UpdateEtudiantResponse response = new UpdateEtudiantResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        Etudiant existingEtudiant = etudiantService.getEtudiantById(request.getEtudiant().getId());
        if (existingEtudiant != null) {
            BeanUtils.copyProperties(request.getEtudiant(), existingEtudiant);
            etudiantService.saveEtudiant(existingEtudiant);

            serviceStatus.setStatus("SUCCESS");
            serviceStatus.setMessage("Etudiant mis à jour avec succès");
            response.setStatus(String.valueOf(serviceStatus));
        } else {
            serviceStatus.setStatus("ERROR");
            serviceStatus.setMessage("Etudiant non trouvé");
            response.setStatus(String.valueOf(serviceStatus));
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteEtudiantRequest")
    @ResponsePayload
    public DeleteEtudiantResponse deleteEtudiant(@RequestPayload DeleteEtudiantRequest request) {
        DeleteEtudiantResponse response = new DeleteEtudiantResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        boolean isDeleted = etudiantService.deleteEtudiant(request.getId());
        if (isDeleted) {
            serviceStatus.setStatus("SUCCESS");
            serviceStatus.setMessage("Etudiant supprimé avec succès");
        } else {
            serviceStatus.setStatus("ERROR");
            serviceStatus.setMessage("Échec de la suppression de l'étudiant");
        }

        response.setStatus(String.valueOf(serviceStatus));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllEtudiantsRequest")
    @ResponsePayload
    public GetAllEtudiantsResponse getAllEtudiants(@RequestPayload GetAllEtudiantsRequest request) {
        GetAllEtudiantsResponse response = new GetAllEtudiantsResponse();
        ListeEtudiantsType listeEtudiantsType = new ListeEtudiantsType();

        List<Etudiant> etudiants = etudiantService.getAllEtudiants();
        for (Etudiant etudiant : etudiants) {
            EtudiantType etudiantType = new EtudiantType();
            BeanUtils.copyProperties(etudiant, etudiantType);
            listeEtudiantsType.getEtudiant().add(etudiantType);
        }

        response.setEtudiants(listeEtudiantsType);
        return response;
    }
}
