package com.superbiblio.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Exemplaires")
@Data
public class Exemplaire {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    private int exemplaireId;

    @ManyToOne
    @JoinColumn(name = "livre_id")
    private Livre livre;

    private String statut;
}
