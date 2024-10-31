package com.gestion.gestionFormation.controller;


import com.gestion.gestionFormation.model.Classe;
import com.gestion.gestionFormation.service.ClasseService;
import lombok.AllArgsConstructor;
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
    public Classe addClasse(@RequestBody Classe classe) {
        return classeService.addClasse(classe);
    }


    @GetMapping("/all")
    public List<Classe> getAllClasses() {
        return classeService.getAllClasses();
    }


    @PutMapping(value = "/update/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Classe updateClasse(@PathVariable Long id,@RequestBody Classe classe) {
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

}
