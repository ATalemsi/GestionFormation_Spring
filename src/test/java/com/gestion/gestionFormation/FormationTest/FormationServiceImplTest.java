package com.gestion.gestionFormation.FormationTest;

import com.gestion.gestionFormation.exception.CourseNotFoundException;
import com.gestion.gestionFormation.model.Formation;
import com.gestion.gestionFormation.repository.FormationRepository;
import com.gestion.gestionFormation.service.Implementation.FormationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FormationServiceImplTest  {

    @Mock
    private FormationRepository formationRepository;

    @InjectMocks
    private FormationServiceImpl formationService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void addFormation_shouldSaveAndReturnFormation() {
        Formation formation = new Formation();
        formation.setTitre("Java Basics");
        formation.setCapaciteMax(50);
        formation.setCapaciteMin(10);
        formation.setNiveau("Beginner");
        formation.setStatut(Formation.StatutFormation.PLANIFIEE);
        formation.setPrerequis("None");
        formation.setDateDebut(LocalDate.ofEpochDay(2024- 1 - 1));
        formation.setDateFin(LocalDate.ofEpochDay(2024 - 1));
        when(formationRepository.save(formation)).thenReturn(formation);

        Formation result = formationService.addFormation(formation);

        assertNotNull(result);
        assertEquals("Java Basics", result.getTitre());
        verify(formationRepository, times(1)).save(formation);
    }

    @Test
    void updateFormation_shouldUpdateAndReturnFormation() {
        Formation existingFormation = new Formation();
        existingFormation.setId(1L);
        existingFormation.setTitre("Old Title");
        existingFormation.setCapaciteMax(50);
        existingFormation.setCapaciteMin(10);
        existingFormation.setNiveau("Beginner");
        existingFormation.setStatut(Formation.StatutFormation.PLANIFIEE);
        existingFormation.setPrerequis("None");
        existingFormation.setDateDebut(LocalDate.ofEpochDay(2024- 1 - 1));
        existingFormation.setDateFin(LocalDate.ofEpochDay(2024 - 1));

        Formation updatedFormation = new Formation();
        updatedFormation.setTitre("New Title");
        updatedFormation.setCapaciteMax(40);
        updatedFormation.setCapaciteMin(20);
        updatedFormation.setNiveau("Intermediate");
        updatedFormation.setStatut(Formation.StatutFormation.EN_COURS);
        updatedFormation.setPrerequis("None");
        updatedFormation.setDateDebut(LocalDate.ofEpochDay(2024- 1 - 1));
        updatedFormation.setDateFin(LocalDate.ofEpochDay(2024 - 1));

        when(formationRepository.findById(1L)).thenReturn(Optional.of(existingFormation));
        when(formationRepository.save(existingFormation)).thenReturn(existingFormation);

        Formation result = formationService.updateFormation(1L, updatedFormation);

        assertNotNull(result);
        assertEquals("New Title", result.getTitre());
        verify(formationRepository, times(1)).save(existingFormation);
    }

    @Test
    void updateFormation_whenFormationNotFound_shouldThrowException() {
        Formation formation = new Formation();
        formation.setTitre("Updated Title");
        formation.setCapaciteMax(40);
        formation.setCapaciteMin(20);
        formation.setNiveau("Intermediate");
        formation.setStatut(Formation.StatutFormation.EN_COURS);
        formation.setPrerequis("None");
        formation.setDateDebut(LocalDate.ofEpochDay(2024- 1 - 1));
        formation.setDateFin(LocalDate.ofEpochDay(2024 - 1));

        when(formationRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CourseNotFoundException.class, () -> formationService.updateFormation(1L, formation));
    }

    @Test
    void getFormation_shouldReturnFormationById() {
        Formation formation = new Formation();
        formation.setId(1L);
        formation.setTitre("Python Basics");
        formation.setCapaciteMax(40);
        formation.setCapaciteMin(20);
        formation.setNiveau("Intermediate");
        formation.setStatut(Formation.StatutFormation.EN_COURS);
        formation.setPrerequis("None");
        formation.setDateDebut(LocalDate.now());
        formation.setDateFin(LocalDate.ofEpochDay(2024 - 1));

        when(formationRepository.findById(1L)).thenReturn(Optional.of(formation));

        Optional<Formation> result = formationService.getFormation(1L);

        assertTrue(result.isPresent());
        assertEquals("Python Basics", result.get().getTitre());
        verify(formationRepository, times(1)).findById(1L);
    }

    @Test
    void getFormation_whenFormationNotFound_shouldThrowException() {
        when(formationRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CourseNotFoundException.class, () -> formationService.getFormation(1L));
    }
    @Test
    void getAllFormations_shouldReturnAllFormations() {
        Formation formation1 = new Formation();
        formation1.setTitre("Course 1");
        formation1.setCapaciteMax(40);
        formation1.setCapaciteMin(20);
        formation1.setNiveau("Intermediate");
        formation1.setStatut(Formation.StatutFormation.EN_COURS);
        formation1.setPrerequis("None");
        formation1.setDateDebut(LocalDate.now());
        formation1.setDateFin(LocalDate.ofEpochDay(2024 - 1));

        Formation formation2 = new Formation();
        formation2.setTitre("Course 2");
        formation2.setCapaciteMax(50);
        formation2.setCapaciteMin(10);
        formation2.setNiveau("Beginner");
        formation2.setStatut(Formation.StatutFormation.PLANIFIEE);
        formation2.setPrerequis("None");
        formation2.setDateDebut(LocalDate.ofEpochDay(2024- 1 - 1));
        formation2.setDateFin(LocalDate.ofEpochDay(2024 - 1));

        when(formationRepository.findAll()).thenReturn(List.of(formation1, formation2));

        List<Formation> result = formationService.getAllFormations();

        assertEquals(2, result.size());
        verify(formationRepository, times(1)).findAll();
    }
    @Test
    void deleteFormation_shouldRemoveFormation() {
        when(formationRepository.existsById(1L)).thenReturn(true);
        doNothing().when(formationRepository).deleteById(1L);

        String result = formationService.deleteFormation(1L);

        assertEquals("Formation Supprimée", result);
        verify(formationRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteFormation_whenFormationNotFound_shouldThrowException() {
        when(formationRepository.existsById(1L)).thenReturn(false);

        assertThrows(CourseNotFoundException.class, () -> formationService.deleteFormation(1L));
    }
}
