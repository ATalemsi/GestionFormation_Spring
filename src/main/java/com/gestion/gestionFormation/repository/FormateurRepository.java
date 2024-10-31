package com.gestion.gestionFormation.repository;

import com.gestion.gestionFormation.model.Formateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormateurRepository extends JpaRepository<Formateur, Long> {
}
