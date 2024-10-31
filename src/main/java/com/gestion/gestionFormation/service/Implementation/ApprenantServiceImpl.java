package com.gestion.gestionFormation.service.Implementation;

import com.gestion.gestionFormation.model.Apprenant;
import com.gestion.gestionFormation.repository.ApprenantRepository;
import com.gestion.gestionFormation.service.ApprenantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ApprenantServiceImpl implements ApprenantService {

    private final ApprenantRepository apprenantRepository;

    @Override
    public Apprenant RegisterApprenant(Apprenant apprenant) {
        return apprenantRepository.save(apprenant);
    }

    @Override
    public Apprenant updateApprenant(Long id, Apprenant apprenant) {
        return apprenantRepository.findById(id)
                .map(existingApprenant -> {
                    existingApprenant.setNiveau(apprenant.getNiveau());
                    existingApprenant.setClasse(apprenant.getClasse());
                    existingApprenant.setFormation(apprenant.getFormation());
                    return apprenantRepository.save(existingApprenant);
                })
                .orElseThrow(() -> new RuntimeException("Apprenant not found with ID: " + id));
    }

    @Override
    public Optional<Apprenant> getApprenant(Long id) {
        return apprenantRepository.findById(id);
    }

    @Override
    public List<Apprenant> getAllApprenants() {
        return apprenantRepository.findAllWithClasseAndFormation();
    }

    @Override
    public String deleteApprenant(Long id) {
        apprenantRepository.deleteById(id);
        return "Apprenant Supprimé";
    }
}
