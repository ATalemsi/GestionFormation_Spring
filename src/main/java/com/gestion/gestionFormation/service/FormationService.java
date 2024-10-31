package com.gestion.gestionFormation.service;

import com.gestion.gestionFormation.model.Formation;

import java.util.List;
import java.util.Optional;

public interface FormationService {
    Formation addFormation(Formation formation);
    Formation updateFormation(Long id,Formation formation);
    Optional<Formation> getFormation(Long id);
    List<Formation> getAllFormations();
    String deleteFormation(Long id);
}
