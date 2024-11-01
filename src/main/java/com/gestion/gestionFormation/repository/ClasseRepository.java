package com.gestion.gestionFormation.repository;

import com.gestion.gestionFormation.model.Classe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClasseRepository extends JpaRepository<Classe, Long> {

    List<Classe> findByNom(String nom);

    List<Classe> findByNomAndNumSalle(String nom, Integer  numSalle);

    @Query("SELECT c FROM Classe c WHERE c.nom LIKE %:nom% AND c.numSalle = :numSalle")
    List<Classe> searchByNomAndNumSalle(@Param("nom") String nom, @Param("numSalle") Integer  numSalle);

    Page<Classe> findByNom(String nom, Pageable pageable);

}
