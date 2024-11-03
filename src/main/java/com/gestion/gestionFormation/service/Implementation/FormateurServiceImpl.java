package com.gestion.gestionFormation.service.Implementation;

import com.gestion.gestionFormation.exception.ClasseNotFoundException;
import com.gestion.gestionFormation.exception.CourseNotFoundException;
import com.gestion.gestionFormation.exception.FormateurNotFoundException;
import com.gestion.gestionFormation.model.Formateur;
import com.gestion.gestionFormation.model.Formation;
import com.gestion.gestionFormation.repository.FormateurRepository;
import com.gestion.gestionFormation.repository.FormationRepository;
import com.gestion.gestionFormation.service.FormateurService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FormateurServiceImpl implements FormateurService {
    private  final FormateurRepository formateurRepository;
    private final FormationRepository formationRepository;


    @Override
    public Formateur RegisterFormateur(Formateur formateur, Long formationId) {
        Formation formation = formationRepository.findById(formationId)
                .orElseThrow(() -> new CourseNotFoundException(formationId));

        formation.setFormateur(formateur);


        formationRepository.save(formation);

        return formateur;
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
                .orElseThrow(() -> new FormateurNotFoundException(id));
    }

    @Override
    public Optional<Formateur> getFormateur(Long id) {
        return Optional.ofNullable(formateurRepository.findById(id)
                .orElseThrow(() -> new FormateurNotFoundException(id)));
    }

    @Override
    public List<Formateur> getAllFormateurs() {
        return formateurRepository.findAll();
    }

    @Override
    public String deleteFormateur(Long id) {
        if (!formateurRepository.existsById(id)) {
            throw new FormateurNotFoundException(id);
        }
        formateurRepository.deleteById(id);
        return "Formateur Supprimé";
    }
}
