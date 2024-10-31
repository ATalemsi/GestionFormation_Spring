package com.gestion.gestionFormation.controller;


import com.gestion.gestionFormation.model.Apprenant;
import com.gestion.gestionFormation.service.ApprenantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apprenant")
@AllArgsConstructor
public class ApprenantController {

    private final ApprenantService apprenantService;

    @PostMapping("/register")
    public Apprenant registerApprenant(@RequestBody Apprenant apprenant) {
        return apprenantService.RegisterApprenant(apprenant);
    }

    @GetMapping("/all")
    public List<Apprenant> getAllApprenants() {
        return apprenantService.getAllApprenants();
    }

    @PutMapping("/update/{id}")
    public Apprenant updateApprenant(@PathVariable Long id, @RequestBody Apprenant apprenant) {
        return apprenantService.updateApprenant(id, apprenant);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteApprenant(@PathVariable Long id) {
        return apprenantService.deleteApprenant(id);
    }

    @GetMapping("/{id}")
    public Optional<Apprenant> getApprenant(@PathVariable Long id) {
        return apprenantService.getApprenant(id);
    }


}
