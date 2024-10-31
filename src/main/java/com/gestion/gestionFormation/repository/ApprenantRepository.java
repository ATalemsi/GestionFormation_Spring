package com.gestion.gestionFormation.repository;

import com.gestion.gestionFormation.model.Apprenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApprenantRepository extends JpaRepository<Apprenant, Long> {
    @Query("SELECT a FROM Apprenant a JOIN FETCH a.classe c JOIN FETCH a.formation f")
    List<Apprenant> findAllWithClasseAndFormation();
}
