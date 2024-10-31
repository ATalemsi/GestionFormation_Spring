package com.gestion.gestionFormation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("APPRENANT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Apprenant extends User {

    @NotBlank(message = "Le niveau est obligatoire")
    private String niveau;

    @ManyToOne
    @JoinColumn(name = "classe_id")
    @JsonBackReference("classe-apprenant")
    private Classe classe;

    @ManyToOne
    @JoinColumn(name = "formation_id")
    @JsonBackReference("formation-apprenant")
    private Formation formation;

    public String getClasseNom() {
        return classe != null ? classe.getNom() : null;
    }

    public String getClasseNumSalle() {
        return classe != null ? classe.getNumSalle() : null;
    }

    public Long getFormationId() {
        return formation != null ? formation.getId() : null;
    }

    public String getFormationNom() {
        return formation != null ? formation.getTitre() : null;
    }
}
