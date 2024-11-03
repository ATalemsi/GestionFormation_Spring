package com.gestion.gestionFormation.service.Implementation;

import com.gestion.gestionFormation.model.Classe;
import com.gestion.gestionFormation.repository.ClasseRepository;
import com.gestion.gestionFormation.service.ClasseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClasseServiceImpl implements ClasseService {

    private final ClasseRepository classeRepository;


    @Override
    public Classe addClasse(Classe classe) {
        return classeRepository.save(classe);
    }

    @Override
    public Classe updateClasse(Long id, Classe classe) {
        return classeRepository.findById(id)
                .map(existingClasse -> {
                    existingClasse.setNom(classe.getNom());
                    existingClasse.setNumSalle(classe.getNumSalle());
                    return classeRepository.save(existingClasse);
                })
                .orElseThrow(() -> new RuntimeException("Classe not found with ID: " + id));
    }

    @Override
    public Optional<Classe> getClasse(Long id) {
        return classeRepository.findById(id);
    }

    @Override
    public String deleteClasse(Long id) {
         classeRepository.deleteById(id);
        return "Classe Supprimer";
    }

    @Override
    public Page<Classe> getAllClasses(Pageable pageable) {
        return classeRepository.findAll(pageable);
    }

    @Override
    public List<Classe> findByNom(String nom) {
        return classeRepository.findByNom(nom);
    }

    @Override
    public List<Classe> findByNomAndNumSalle(String nom, int numSalle) {
        return classeRepository.findByNomAndNumSalle(nom, numSalle);
    }

    @Override
    public List<Classe> searchByNomAndNumSalle(String nom, int numSalle) {
        return classeRepository.searchByNomAndNumSalle(nom, numSalle);
    }

    @Override
    public Page<Classe> getClassesByNom(String nom, Pageable pageable) {
        return classeRepository.findByNom(nom, pageable);
    }
}
