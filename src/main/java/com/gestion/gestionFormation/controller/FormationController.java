package com.gestion.gestionFormation.controller;


import com.gestion.gestionFormation.model.Formation;
import com.gestion.gestionFormation.service.FormationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/formation")
@AllArgsConstructor
public class FormationController {
    private FormationService formationService;


    @PostMapping("/add")
    public Formation add(@Valid @RequestBody Formation formation) {
        return formationService.addFormation(formation);
    }

    @GetMapping("/all")
    public List<Formation> getAllFormation() {
        return formationService.getAllFormations();
    }

    @PutMapping("/update/{id}")
    public Formation update(@PathVariable Long id,@Valid @RequestBody Formation formation) {
        return formationService.updateFormation(id,formation);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        formationService.deleteFormation(id);
    }

    @GetMapping("/{id}")
    public Optional<Formation> getFormation(@PathVariable Long id) {
        return formationService.getFormation(id);
    }
}
