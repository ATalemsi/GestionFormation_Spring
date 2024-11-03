package com.gestion.gestionFormation.exception;

public class ApprenantNotFoundException extends RuntimeException {

    public ApprenantNotFoundException(Long id) {
        super("Apprenant not found with id: " + id);
    }
}
