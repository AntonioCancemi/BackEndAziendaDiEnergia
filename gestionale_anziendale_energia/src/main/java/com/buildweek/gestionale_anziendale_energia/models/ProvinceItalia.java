package com.buildweek.gestionale_anziendale_energia.models;

import com.opencsv.bean.CsvBindByPosition;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "province_italiane")
public class ProvinceItalia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	@CsvBindByPosition(position = 0)
	private String sigla;
	@Column(nullable = false)
	@CsvBindByPosition(position = 1)
	private String provincia;
	@Column(nullable = false)
	@CsvBindByPosition(position = 2)
	private String regione;

//sigla;provincia;regione
	public ProvinceItalia(String sigla, String provincia, String regione) {
		super();
		this.sigla = sigla;
		this.provincia = provincia;
		this.regione = regione;
	}
}
