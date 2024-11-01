package com.gestion.gestionFormation.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "classes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Classe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom de la classe est obligatoire s'il vous plait")
    private String nom;

    @NotBlank(message = "Le numéro de la salle est obligatoire")
    private String numSalle;

    @OneToMany(mappedBy = "classe")
    @JsonManagedReference("classe-apprenant")
    private List<Apprenant> apprenants;

    @OneToOne(mappedBy = "classe")
    @JsonManagedReference("classe-formateur")
    private Formateur formateur;
}
