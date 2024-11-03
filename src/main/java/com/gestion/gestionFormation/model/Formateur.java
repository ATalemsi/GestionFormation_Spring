package com.gestion.gestionFormation.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("FORMATEUR")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Formateur extends User {

    @NotBlank(message = "La spécialité est obligatoire")
    private String specialite;

    @OneToOne
    @JoinColumn(name = "classe_id")
    @JsonBackReference("classe-formateur")
    private Classe classe;


    @OneToOne(mappedBy = "formateur", cascade = CascadeType.PERSIST)
    @JsonBackReference("formation-formateur")
    private Formation formation;

    public String getClasseNom() {
        return classe != null ? classe.getNom() : null;
    }

    public Integer getClasseNumSalle() {
        return classe != null ? classe.getNumSalle() : null;
    }

    public Long getFormationId() {
        return formation != null ? formation.getId() : null;
    }

    public String getFormationNom() {
        return formation != null ? formation.getTitre() : null;
    }
}
