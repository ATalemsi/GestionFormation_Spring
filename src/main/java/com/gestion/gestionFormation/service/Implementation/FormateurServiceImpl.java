package com.gestion.gestionFormation.service.Implementation;

import com.gestion.gestionFormation.model.Formateur;
import com.gestion.gestionFormation.repository.FormateurRepository;
import com.gestion.gestionFormation.service.FormateurService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FormateurServiceImpl implements FormateurService {
    private  final FormateurRepository formateurRepository;


    @Override
    public Formateur RegisterFormateur(Formateur formateur) {
        return formateurRepository.save(formateur);
    }

    @Override
    public Formateur updateFormateur(Long id, Formateur formateur) {
        return formateurRepository.findById(id)
                .map(existingFormateur -> {
                    existingFormateur.setSpecialite(formateur.getSpecialite());
                    existingFormateur.setClasse(formateur.getClasse());
                    existingFormateur.setFormation(formateur.getFormation());
                    return formateurRepository.save(existingFormateur);
                })
                .orElseThrow(() -> new RuntimeException("Formateur not found with Id : " + id));
    }

    @Override
    public Optional<Formateur> getFormateur(Long id) {
        return formateurRepository.findById(id);
    }

    @Override
    public List<Formateur> getAllFormateurs() {
        return formateurRepository.findAll();
    }

    @Override
    public String deleteFormateur(Long id) {
        formateurRepository.deleteById(id);
        return "Formateur Supprimé";
    }
}
