package com.example.departement.Endpoint;
import com.example.departement.*;
import com.example.departement.models.Enseignant;
import com.example.departement.services.Enseignantservice.EnseignantService;
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
public class EnseignantEndPoint {

    private static final String NAMESPACE_URI = "http://example.com/departement";

    @Autowired
    private EnseignantService enseignantService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addEnseignantRequest")
    @ResponsePayload
    public AddEnseignantResponse addEnseignant(@RequestPayload AddEnseignantRequest request) {
        AddEnseignantResponse response = new AddEnseignantResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        Enseignant enseignant = new Enseignant();
        BeanUtils.copyProperties(request.getEnseignant(), enseignant);
        enseignantService.saveEnseignant(enseignant);

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Enseignant ajouté avec succès");
        response.setStatus(String.valueOf(serviceStatus));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllEnseignantsRequest")
    @ResponsePayload
    public GetAllEnseignantsResponse getAllEnseignants(@RequestPayload GetAllEnseignantsRequest request) {
        GetAllEnseignantsResponse response = new GetAllEnseignantsResponse();
        ListeEnseignantsType listeEnseignantsType = new ListeEnseignantsType();

        List<Enseignant> enseignants = enseignantService.getAllEnseignants();
        for (Enseignant enseignant : enseignants) {
            EnseignantType enseignantType = new EnseignantType();
            BeanUtils.copyProperties(enseignant, enseignantType);
            listeEnseignantsType.getEnseignant().add(enseignantType);
        }

        response.setEnseignants(listeEnseignantsType);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateEnseignantRequest")
    @ResponsePayload
    public UpdateEnseignantResponse updateEnseignant(@RequestPayload UpdateEnseignantRequest request) {
        UpdateEnseignantResponse response = new UpdateEnseignantResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        EnseignantType updatedEnseignantType = request.getEnseignant();
        Enseignant enseignant = new Enseignant();
        BeanUtils.copyProperties(updatedEnseignantType, enseignant);
        enseignant.setId(updatedEnseignantType.getId()); // Assure que l'identifiant est défini

        enseignantService.saveEnseignant(enseignant);

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Enseignant mis à jour avec succès");
        response.setStatus(String.valueOf(serviceStatus));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteEnseignantRequest")
    @ResponsePayload
    public DeleteEnseignantResponse deleteEnseignant(@RequestPayload DeleteEnseignantRequest request) {
        DeleteEnseignantResponse response = new DeleteEnseignantResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        Long enseignantId = request.getId();
        enseignantService.deleteEnseignant(enseignantId);

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Enseignant supprimé avec succès");
        response.setStatus(String.valueOf(serviceStatus));

        return response;
    }
}
