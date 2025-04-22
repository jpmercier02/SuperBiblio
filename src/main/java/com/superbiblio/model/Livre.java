package com.superbiblio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Livres")
@Data
public class Livre {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    private int livreId;

    @NotBlank
    @Size(min = 2, message = "Il doit y avoir au moins 2 caractères")
    private String titre;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "auteur_id")
    private Auteur auteur;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    private String edition;

    private Boolean disponibilite;
}
