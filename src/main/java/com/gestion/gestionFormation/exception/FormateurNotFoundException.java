package com.gestion.gestionFormation.exception;

public class FormateurNotFoundException extends RuntimeException {

    public FormateurNotFoundException(Long id) {
        super("Formateur not found with id: " + id);
    }
}
