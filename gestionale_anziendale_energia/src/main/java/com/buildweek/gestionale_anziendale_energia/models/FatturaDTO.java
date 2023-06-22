package com.buildweek.gestionale_anziendale_energia.models;

import java.time.LocalDate;

import com.buildweek.gestionale_anziendale_energia.enumeration.StatoFattura;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FatturaDTO {
	private Long numeroFattura;
	private Integer anno;
	private LocalDate dataFattura;
	private Long importo;
	private String statoFattura;
	private Long idCliente;
}
