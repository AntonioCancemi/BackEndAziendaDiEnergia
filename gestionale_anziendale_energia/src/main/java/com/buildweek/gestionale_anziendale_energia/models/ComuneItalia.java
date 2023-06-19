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
@Table(name = "comuni_italiani")
public class ComuneItalia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false )
	@CsvBindByPosition(position = 0)
	private String cap;
	@Column(nullable = false )
	@CsvBindByPosition(position = 1)
	private String prefisso;
	@Column(nullable = false )
	@CsvBindByPosition(position = 2)
	private String comune;
	@Column(nullable = false )
	@CsvBindByPosition(position = 3)
	private String provincia;
	
	
	public ComuneItalia(String cap, String prefisso, String comune, String provincia) {
		super();
		this.cap = cap;
		this.prefisso = prefisso;
		this.comune = comune;
		this.provincia = provincia;
	}

}
