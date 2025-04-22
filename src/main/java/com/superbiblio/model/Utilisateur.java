package com.superbiblio.model;

import com.superbiblio.annotations.PasswordValidator;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Utilisateurs")
@Data
public class Utilisateur {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    private int utilisateurId;

    private String nom;

    private String prenom;

    @Column(unique = true)
    private String adresseEmail;

    @PasswordValidator
    private String motDePasseHashed;

    private String role;

    private boolean active;
}
