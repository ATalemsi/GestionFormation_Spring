package com.gestion.gestionFormation.ClassTests;

import com.gestion.gestionFormation.exception.ClasseNotFoundException;
import com.gestion.gestionFormation.model.Classe;
import com.gestion.gestionFormation.repository.ClasseRepository;
import com.gestion.gestionFormation.service.Implementation.ClasseServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ClasseServiceImplUnitTest {

    @Mock
    private ClasseRepository classRepositoryMock;

    @InjectMocks
    private ClasseServiceImpl classeServiceImpl;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void addClasse_shouldSaveClasse() {
        Classe classe = new Classe();
        classe.setNom("Math");
        classe.setNumSalle(120);
        when(classRepositoryMock.save(any(Classe.class))).thenReturn(classe);
        Classe result = classeServiceImpl.addClasse(classe);
        assertNotNull(result);
        assertEquals("Math", result.getNom());
        verify(classRepositoryMock, times(1)).save(classe);
    }

    @Test
    void getClasse_shouldReturnClasse() {
        Classe classe = new Classe();
        classe.setNom("Math");
        classe.setNumSalle(101);
        when(classRepositoryMock.findById(1L)).thenReturn(Optional.of(classe));

        Optional<Classe> result = classeServiceImpl.getClasse(1L);

        assertTrue(result.isPresent());
        assertEquals("Math", result.get().getNom());
    }

    @Test
    void deleteClasse_shouldThrowExceptionIfNotFound() {
        when(classRepositoryMock.existsById(1L)).thenReturn(false);

        assertThrows(ClasseNotFoundException.class, () -> classeServiceImpl.deleteClasse(1L));
    }

    @Test
    void getAllClasses_shouldReturnPaginatedList() {
        Classe classe = new Classe();
        classe.setNom("Math");
        classe.setNumSalle(101);
        PageRequest pageable = PageRequest.of(0, 10);
        Page<Classe> page = new PageImpl<>(List.of(classe));
        when(classRepositoryMock.findAll(pageable)).thenReturn(page);

        Page<Classe> result = classeServiceImpl.getAllClasses(pageable);

        assertEquals(1, result.getTotalElements());
        verify(classRepositoryMock, times(1)).findAll(pageable);
    }
}
