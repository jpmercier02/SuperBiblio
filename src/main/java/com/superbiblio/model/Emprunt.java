package com.superbiblio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;

@Entity
@Table(name = "Emprunts")
@Data
public class Emprunt {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    private int empruntId;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "exemplaire_id")
    private Exemplaire exemplaire;

    @Temporal(TemporalType.DATE)
    @NotBlank
    private Date dateEmprunt;

    @Temporal(TemporalType.DATE)
    private Date dateRetourPrevue;

    @Temporal(TemporalType.DATE)
    private Date dateRetourEffective;

    private String statut;
}
