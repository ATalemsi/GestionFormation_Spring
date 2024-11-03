package com.gestion.gestionFormation.controller;


import com.gestion.gestionFormation.model.Formateur;
import com.gestion.gestionFormation.service.FormateurService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/formateur")
@AllArgsConstructor
public class FormateurController {
    private final FormateurService formateurService;


    @PostMapping("/register")
    public Formateur registerFormateur(@Valid @RequestBody Formateur formateur) {
        return formateurService.RegisterFormateur(formateur);
    }

    @GetMapping("/all")
    public List<Formateur> getAllFormateurs() {
        return formateurService.getAllFormateurs();
    }

    @PutMapping("/update/{id}")
    public Formateur updateFormateur(@PathVariable Long id,@Valid @RequestBody Formateur formateur) {
        return formateurService.updateFormateur(id, formateur);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteFormateur(@PathVariable Long id) {
        return formateurService.deleteFormateur(id);
    }

    @GetMapping("/{id}")
    public Optional<Formateur> getFormateur(@PathVariable Long id) {
        return formateurService.getFormateur(id);
    }

}
