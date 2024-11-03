package com.gestion.gestionFormation.FormationTest;


import com.gestion.gestionFormation.exception.CourseNotFoundException;
import com.gestion.gestionFormation.model.Formation;
import com.gestion.gestionFormation.repository.FormationRepository;
import com.gestion.gestionFormation.service.FormationService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
public class FormationServiceIntegrationTest {

    @Autowired
    private FormationService formationService;

    @Autowired
    private FormationRepository formationRepository;

    @BeforeEach
    void setUp() {
        formationRepository.deleteAll();
    }

    @Test
    void addFormation_shouldSaveAndReturnFormation() {
        Formation formation = new Formation();
        formation.setTitre("Java Advanced");
        formation.setCapaciteMax(50);
        formation.setCapaciteMin(10);
        formation.setNiveau("Beginner");
        formation.setStatut(Formation.StatutFormation.PLANIFIEE);
        formation.setPrerequis("None");
        formation.setDateDebut(LocalDate.ofEpochDay(2024- 1 - 1));
        formation.setDateFin(LocalDate.ofEpochDay(2024 - 2));

        Formation savedFormation = formationService.addFormation(formation);

        assertNotNull(savedFormation.getId());
        assertEquals("Java Advanced", savedFormation.getTitre());
    }

    @Test
    void updateFormation_shouldUpdateAndReturnFormation() {
        Formation formation = new Formation();
        formation.setTitre("Spring Basics");
        formation.setCapaciteMax(50);
        formation.setCapaciteMin(10);
        formation.setNiveau("Beginner");
        formation.setStatut(Formation.StatutFormation.PLANIFIEE);
        formation.setPrerequis("None");
        formation.setDateDebut(LocalDate.ofEpochDay(2024- 1 - 1));
        formation.setDateFin(LocalDate.ofEpochDay(2024 - 1));

        Formation savedFormation = formationService.addFormation(formation);

        Formation updatedFormation = new Formation();
        updatedFormation.setTitre("Spring Advanced");
        updatedFormation.setNiveau("Intermediate");
        updatedFormation.setCapaciteMax(50);
        updatedFormation.setCapaciteMin(10);
        updatedFormation.setStatut(Formation.StatutFormation.PLANIFIEE);
        updatedFormation.setPrerequis("None");
        updatedFormation.setDateDebut(LocalDate.ofEpochDay(2024- 1 - 1));
        updatedFormation.setDateFin(LocalDate.ofEpochDay(2024 - 1));

        Formation result = formationService.updateFormation(savedFormation.getId(), updatedFormation);

        assertEquals("Spring Advanced", result.getTitre());
        assertEquals("Intermediate", result.getNiveau());
    }
    @Test
    void updateFormation_whenFormationNotFound_shouldThrowException() {
        Formation formation = new Formation();
        formation.setTitre("Non-existent Course");
        formation.setNiveau("Intermediate");
        formation.setCapaciteMax(50);
        formation.setCapaciteMin(10);
        formation.setStatut(Formation.StatutFormation.PLANIFIEE);
        formation.setPrerequis("None");
        formation.setDateDebut(LocalDate.ofEpochDay(2024- 1 - 1));
        formation.setDateFin(LocalDate.ofEpochDay(2024 - 1));

        assertThrows(CourseNotFoundException.class, () -> formationService.updateFormation(999L, formation));
    }

    @Test
    void getFormation_shouldReturnFormationById() {
        Formation formation = new Formation();
        formation.setTitre("Python Basics");
        formation.setNiveau("Intermediate");
        formation.setCapaciteMax(50);
        formation.setCapaciteMin(10);
        formation.setStatut(Formation.StatutFormation.PLANIFIEE);
        formation.setPrerequis("None");
        formation.setDateDebut(LocalDate.ofEpochDay(2024- 1 - 1));
        formation.setDateFin(LocalDate.ofEpochDay(2024 - 1));

        Formation savedFormation = formationService.addFormation(formation);

        Optional<Formation> result = formationService.getFormation(savedFormation.getId());

        assertTrue(result.isPresent());
        assertEquals("Python Basics", result.get().getTitre());
    }
    @Test
    void getFormation_whenFormationNotFound_shouldThrowException() {
        assertThrows(CourseNotFoundException.class, () -> formationService.getFormation(999L));
    }
    @Test
    void getAllFormations_shouldReturnAllFormations() {
        Formation formation1 = new Formation();
        formation1.setTitre("Course A");
        formation1.setNiveau("Intermediate");
        formation1.setCapaciteMax(50);
        formation1.setCapaciteMin(10);
        formation1.setStatut(Formation.StatutFormation.PLANIFIEE);
        formation1.setPrerequis("None");
        formation1.setDateDebut(LocalDate.ofEpochDay(2024- 1 - 1));
        formation1.setDateFin(LocalDate.ofEpochDay(2024 - 1));

        Formation formation2 = new Formation();
        formation2.setTitre("Course B");
        formation2.setNiveau("Beginner");
        formation2.setCapaciteMax(20);
        formation2.setCapaciteMin(5);
        formation2.setStatut(Formation.StatutFormation.PLANIFIEE);
        formation2.setPrerequis("None");
        formation2.setDateDebut(LocalDate.ofEpochDay(2024- 1 - 1));
        formation2.setDateFin(LocalDate.ofEpochDay(2024 - 1));


        formationService.addFormation(formation1);
        formationService.addFormation(formation2);

        List<Formation> formations = formationService.getAllFormations();

        assertEquals(2, formations.size());
    }

    @Test
    void deleteFormation_shouldRemoveFormation() {
        Formation formation = new Formation();
        formation.setTitre("Removable Course");
        formation.setTitre("Course B");
        formation.setNiveau("Beginner");
        formation.setCapaciteMax(20);
        formation.setCapaciteMin(5);
        formation.setStatut(Formation.StatutFormation.PLANIFIEE);
        formation.setPrerequis("None");
        formation.setDateDebut(LocalDate.ofEpochDay(2024- 1 - 1));
        formation.setDateFin(LocalDate.ofEpochDay(2024 - 1));
        Formation savedFormation = formationService.addFormation(formation);

        formationService.deleteFormation(savedFormation.getId());

        assertFalse(formationRepository.existsById(savedFormation.getId()));
    }

    @Test
    void deleteFormation_whenFormationNotFound_shouldThrowException() {
        assertThrows(CourseNotFoundException.class, () -> formationService.deleteFormation(999L));
    }
}
