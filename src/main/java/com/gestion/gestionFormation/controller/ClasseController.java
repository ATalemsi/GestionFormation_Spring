package com.gestion.gestionFormation.controller;


import com.gestion.gestionFormation.model.Classe;
import com.gestion.gestionFormation.service.ClasseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/classe")
@AllArgsConstructor
public class ClasseController {

    private final ClasseService classeService;

    @PostMapping(value = "/add" ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Classe addClasse(@Valid @RequestBody Classe classe) {
        return classeService.addClasse(classe);
    }


    @GetMapping("/all")
    public Page<Classe> getAllClasses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return classeService.getAllClasses(pageable);
    }


    @PutMapping(value = "/update/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Classe updateClasse(@PathVariable Long id,@Valid @RequestBody Classe classe) {
         return classeService.updateClasse(id,classe);
    }


    @DeleteMapping("/delete/{id}")
    public String deleteClasse(@PathVariable Long id) {
      return classeService.deleteClasse(id);
    }

    @GetMapping("/class/{id}")
    public Optional<Classe> getClasse(@PathVariable Long id) {
        return classeService.getClasse(id);
    }

    @GetMapping("/searchByNom")
    public List<Classe> findByNom(@RequestParam String nom) {
        return classeService.findByNom(nom);
    }

    @GetMapping("/searchByNomAndNumSalle")
    public List<Classe> findByNomAndNumSalle(@RequestParam String nom, @RequestParam int numSalle) {
        return classeService.findByNomAndNumSalle(nom, numSalle);
    }

    @GetMapping("/customSearch")
    public List<Classe> searchByNomAndNumSalle(@RequestParam String nom, @RequestParam int numSalle) {
        return classeService.searchByNomAndNumSalle(nom, numSalle);
    }

    @GetMapping("/search")
    public Page<Classe> getClassesByNom(@RequestParam String nom, Pageable pageable) {
        return classeService.getClassesByNom(nom, pageable);
    }

}
