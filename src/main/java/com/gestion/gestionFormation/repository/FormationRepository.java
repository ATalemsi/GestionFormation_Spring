package com.gestion.gestionFormation.repository;

import com.gestion.gestionFormation.model.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormationRepository extends JpaRepository<Formation, Long> {
}
