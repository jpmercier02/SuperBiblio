package com.superbiblio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;

@Entity
@Table(name = "Commentaires")
@Data
public class Commentaire {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    private int commentaireId;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "livre_id")
    private Livre livre;

    @Column(columnDefinition = "TEXT")
    private String contenu;

    @Temporal(TemporalType.DATE)
    @NotBlank
    private Date dateCommentaire;

    private Integer note;
}
