package com.gestion.gestionFormation.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le titre de la formation est obligatoire")
    private String titre;

    @NotBlank(message = "Le niveau est obligatoire")
    private String niveau;

    @NotBlank(message = "Les prérequis sont obligatoires")
    private String prerequis;

    @Min(value = 1, message = "La capacité minimale doit être au moins de 1")
    private int capaciteMin;

    @Max(value = 100, message = "La capacité maximale ne doit pas dépasser 100")
    private int capaciteMax;

    @NotNull(message = "La date de début est obligatoire")
    private LocalDate dateDebut;

    @NotNull(message = "La date de fin est obligatoire")
    private LocalDate dateFin;



    @Enumerated(EnumType.STRING)
    @NotNull(message = "Le statut de la formation est obligatoire")
    private StatutFormation statut;


    @ManyToOne
    @JoinColumn(name = "formateur_id")
    @JsonManagedReference("formation-formateur")
    private Formateur formateur;


    @OneToMany(mappedBy = "formation")
    @JsonManagedReference("formation-apprenant")
    private List<Apprenant> apprenants;


    public enum StatutFormation {
        PLANIFIEE,
        EN_COURS,
        TERMINEE,
        ANNULEE
    }
}
