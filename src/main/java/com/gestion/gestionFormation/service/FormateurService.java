package com.gestion.gestionFormation.service;

import com.gestion.gestionFormation.model.Formateur;

import java.util.List;
import java.util.Optional;

public interface FormateurService {
    Formateur RegisterFormateur(Formateur formateur,Long formationId);
    Formateur updateFormateur(Long id,Formateur formateur);
    Optional<Formateur> getFormateur(Long id);
    List<Formateur> getAllFormateurs();
    String deleteFormateur(Long id);
}
