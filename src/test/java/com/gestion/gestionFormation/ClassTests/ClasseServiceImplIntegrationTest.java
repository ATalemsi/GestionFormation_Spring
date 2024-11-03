package com.gestion.gestionFormation.ClassTests;


import com.gestion.gestionFormation.model.Classe;
import com.gestion.gestionFormation.repository.ClasseRepository;
import com.gestion.gestionFormation.service.ClasseService;
import com.gestion.gestionFormation.service.Implementation.ClasseServiceImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ClasseServiceImplIntegrationTest {
    @Autowired
    private ClasseServiceImpl classeService;

    @Autowired
    private ClasseRepository classeRepository;



    @Test
    void addClasse_shouldSaveAndReturnClasse() {
        Classe classe = new Classe();
        classe.setNom("Physics");
        classe.setNumSalle(120);
        Classe savedClasse = classeService.addClasse(classe);
        assertNotNull(savedClasse.getId());
        assertEquals("Physics", savedClasse.getNom());
    }

    @Test
    void updateClasse_shouldUpdateAndReturnClasse() {
        Classe classe = new Classe();
        classe.setNom("Chemistry");
        classe.setNumSalle(104);
        classe = classeService.addClasse(classe);


        Classe updatedClasse = new Classe();
        updatedClasse.setNom("Biology");
        updatedClasse.setNumSalle(105);

        Classe result = classeService.updateClasse(classe.getId(), updatedClasse);

        assertEquals("Biology", result.getNom());
        assertEquals(105, result.getNumSalle());
    }

    @Test
    void getClasse_shouldReturnClasseById() {
        Classe classe = new Classe();
        classe.setNom("History");
        classe.setNumSalle(106);
        classe = classeService.addClasse(classe);

        Classe result = classeService.getClasse(classe.getId()).orElse(null);

        assertNotNull(result);
        assertEquals("History", result.getNom());
    }

    @Test
    void deleteClasse_shouldRemoveClasse() {

        Classe classe = new Classe();
        classe.setNom("Geography");
        classe.setNumSalle(107);
        classe = classeService.addClasse(classe);

        classeService.deleteClasse(classe.getId());

        assertFalse(classeRepository.existsById(classe.getId()));
    }

    @Test
    void getAllClasses_shouldReturnAllClassesPaginated() {
        // Create and set up the 'Classe' instances using setters
        Classe classe1 = new Classe();
        classe1.setNom("Math");
        classe1.setNumSalle(108);
        classeService.addClasse(classe1);

        Classe classe2 = new Classe();
        classe2.setNom("Science");
        classe2.setNumSalle(109);
        classeService.addClasse(classe2);

        Page<Classe> page = classeService.getAllClasses(PageRequest.of(0, 10));

        assertEquals(2, page.getTotalElements());

    }
}
