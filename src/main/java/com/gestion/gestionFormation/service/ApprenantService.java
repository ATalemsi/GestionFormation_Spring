package com.gestion.gestionFormation.service;

import com.gestion.gestionFormation.model.Apprenant;

import java.util.List;
import java.util.Optional;

public interface ApprenantService {
    Apprenant RegisterApprenant(Apprenant apprenant);
    Apprenant updateApprenant(Long id,Apprenant apprenant);
    Optional<Apprenant> getApprenant(Long id);
    List<Apprenant> getAllApprenants();
    String deleteApprenant(Long id);
}
