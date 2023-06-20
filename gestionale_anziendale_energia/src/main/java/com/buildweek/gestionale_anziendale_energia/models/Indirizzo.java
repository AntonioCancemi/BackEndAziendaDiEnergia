package com.buildweek.gestionale_anziendale_energia.models;

import com.buildweek.gestionale_anziendale_energia.enumeration.TipoIndirizzo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	@Column(nullable = true)
	private String localita;
	@Column(nullable = false)
	private Integer cap;
	@Column(nullable = false)
	private String comune;
	@Column(nullable = false)
	private TipoIndirizzo tipoIndirizzo;

//	{"via":"corso italia",
//	"civico":123,
//	"localita":"Libbrino",
//	"cap":31453,
//	"comune":"Catania"
//	"tipoIndirizzo":"SEDE_LEGALE"}
//	
//	
//	
}
