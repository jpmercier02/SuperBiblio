package com.superbiblio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "Auteurs")
public class Auteur {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "auteur_id")
    private int auteurId;

    @NotBlank(message = "Le nom ne peut pas être vide")
    @Size(min = 2, message = "Il doit y avoir au moins 2 caractères")
    private String nom;

    @NotBlank(message = "Le prénom ne peut pas être vide")
    @Size(min = 2, message = "Le prénom doit y avoir au moins 2 caractères")
    private String prenom;

    @Column(columnDefinition = "TEXT")
    private String biographie;
}
