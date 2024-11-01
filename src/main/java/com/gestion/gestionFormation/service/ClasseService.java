package com.gestion.gestionFormation.service;

import com.gestion.gestionFormation.model.Classe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ClasseService {
    Classe addClasse(Classe classe);
    Classe updateClasse(Long id, Classe classe);
    Optional<Classe> getClasse(Long id);
    String deleteClasse(Long id);
    Page<Classe> getAllClasses(Pageable pageable);

    List<Classe> findByNom(String nom);
    List<Classe> findByNomAndNumSalle(String nom, int numSalle);
    List<Classe> searchByNomAndNumSalle(String nom, int numSalle);
    Page<Classe> getClassesByNom(String nom, Pageable pageable);
}
