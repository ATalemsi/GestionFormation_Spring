package com.gestion.gestionFormation.service.Implementation;

import com.gestion.gestionFormation.exception.ClasseNotFoundException;
import com.gestion.gestionFormation.model.Classe;
import com.gestion.gestionFormation.repository.ClasseRepository;
import com.gestion.gestionFormation.service.ClasseService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClasseServiceImpl implements ClasseService {

    private static final Logger logger = LoggerFactory.getLogger(ClasseServiceImpl.class);

    private final ClasseRepository classeRepository;

    @Override
    public Classe addClasse(Classe classe) {
        logger.info("Adding a new classe with name: {}", classe.getNom());
        Classe savedClasse = classeRepository.save(classe);
        logger.info("Classe added successfully with ID: {}", savedClasse.getId());
        return savedClasse;
    }

    @Override
    public Classe updateClasse(Long id, Classe classe) {
        logger.info("Updating classe with ID: {}", id);
        return classeRepository.findById(id)
                .map(existingClasse -> {
                    existingClasse.setNom(classe.getNom());
                    existingClasse.setNumSalle(classe.getNumSalle());
                    Classe updatedClasse = classeRepository.save(existingClasse);
                    logger.info("Classe updated successfully with ID: {}", id);
                    return updatedClasse;
                })
                .orElseThrow(() -> {
                    logger.error("Classe with ID {} not found", id);
                    return new ClasseNotFoundException(id);
                });
    }

    @Override
    public Optional<Classe> getClasse(Long id) {
        logger.info("Fetching classe with ID: {}", id);
        return Optional.ofNullable(classeRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Classe with ID {} not found", id);
                    return new ClasseNotFoundException(id);
                }));
    }

    @Override
    public String deleteClasse(Long id) {
        logger.info("Deleting classe with ID: {}", id);
        if (!classeRepository.existsById(id)) {
            logger.error("Classe with ID {} not found", id);
            throw new ClasseNotFoundException(id);
        }
        classeRepository.deleteById(id);
        logger.info("Classe deleted successfully with ID: {}", id);
        return "Classe Supprimer";
    }

    @Override
    public Page<Classe> getAllClasses(Pageable pageable) {
        logger.info("Fetching all classes with pagination");
        return classeRepository.findAll(pageable);
    }

    @Override
    public List<Classe> findByNom(String nom) {
        logger.info("Finding classes with name: {}", nom);
        return classeRepository.findByNom(nom);
    }

    @Override
    public List<Classe> findByNomAndNumSalle(String nom, int numSalle) {
        logger.info("Finding classes with name: {} and room number: {}", nom, numSalle);
        return classeRepository.findByNomAndNumSalle(nom, numSalle);
    }

    @Override
    public List<Classe> searchByNomAndNumSalle(String nom, int numSalle) {
        logger.info("Searching classes with name: {} and room number: {}", nom, numSalle);
        return classeRepository.searchByNomAndNumSalle(nom, numSalle);
    }

    @Override
    public Page<Classe> getClassesByNom(String nom, Pageable pageable) {
        logger.info("Fetching classes by name: {} with pagination", nom);
        return classeRepository.findByNom(nom, pageable);
    }
}
