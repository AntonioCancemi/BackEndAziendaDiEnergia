package com.buildweek.gestionale_anziendale_energia.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "indirizzi")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Indirizzo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String via;
    @Column(nullable = false)
    private String civico;
    @Column(nullable = false)
    private String localita;
    @Column(nullable = false)
    private Integer cap;
    @Column(nullable = false)
    private String comune;

    @ManyToOne
    @JoinColumn(name = "cliente")
    private Cliente cliente;
}
