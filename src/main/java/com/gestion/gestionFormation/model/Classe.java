package com.gestion.gestionFormation.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "le numero de la salle est obligatoire")
    private Integer numSalle;

    @OneToMany(mappedBy = "classe")
    @JsonManagedReference("classe-apprenant")
    private List<Apprenant> apprenants;

    @OneToOne(mappedBy = "classe")
    @JsonManagedReference("classe-formateur")
    private Formateur formateur;
}
