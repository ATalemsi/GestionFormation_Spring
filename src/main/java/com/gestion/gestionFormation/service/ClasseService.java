package com.gestion.gestionFormation.service;

import com.gestion.gestionFormation.model.Classe;

import java.util.List;
import java.util.Optional;

public interface ClasseService {
    Classe addClasse(Classe classe);
    Classe updateClasse(Long id, Classe classe);
    Optional<Classe> getClasse(Long id);
    String deleteClasse(Long id);
    List<Classe> getAllClasses();
}
