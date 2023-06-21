package com.buildweek.gestionale_anziendale_energia.models;

import java.time.LocalDate;

import com.buildweek.gestionale_anziendale_energia.enumeration.StatoFattura;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "fatture")
public class Fattura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_fattura")
	private Long id;
	@Column(unique = true, nullable = false)
	private Long numeroFattura;
	@Column(nullable = false)
	private Integer anno;
	@Column(nullable = false)
	private LocalDate dataFattura;
	@Column(nullable = false)
	private Long importo;
	@Enumerated(EnumType.STRING)
	private StatoFattura statoFattura;

	@ManyToOne
	private Cliente cliente;
}
