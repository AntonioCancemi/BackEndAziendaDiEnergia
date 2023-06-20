package com.buildweek.gestionale_anziendale_energia.models;

import java.time.LocalDate;
import java.util.List;

import com.buildweek.gestionale_anziendale_energia.enumeration.TipoCliente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clienti")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCliente;
	@Column(nullable = false, name = "ragione_sociale")
	private String ragioneSociale;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false, unique = true)
	private String pec;
	@Column(name = "data_inserimento")
	private LocalDate dataInserimento;
	@Column(name = "data_ultimo_contatto")
	private LocalDate dataUltimoContatto;
	@Column(nullable = false, name = "fatturato_annuale")
	private int fatturatoAnnuale;
	@Column(nullable = false, name = "telefono")
	private String telefono;
	@Column(nullable = false, name = "email_contatto")
	private String emailContatto;
	@Column(nullable = false, name = "nome_contatto")
	private String nomeContatto;
	@Column(nullable = false, name = "congnome_contatto")
	private String cognomeContatto;
	@Column(nullable = false, name = "telefono_contatto")
	private String telefonoContatto;

	@Column(nullable = false, name = "tipo_cliente")
	@Enumerated(EnumType.STRING)
	private TipoCliente tipoCliente;

	@OneToMany
	private List<Indirizzo> indirizzi;

	@OneToMany
	private List<Fattura> fatture;

	public Cliente(String ragioneSociale, String email, String pec, LocalDate dataInserimento,
			LocalDate dataUltimoContatto, int fatturatoAnnuale, String telefono, String emailContatto,
			String nomeContatto, String cognomeContatto, String telefonoContatto, TipoCliente tipoCliente) {
		this.ragioneSociale = ragioneSociale;
		this.email = email;
		this.pec = pec;
		this.dataInserimento = dataInserimento;
		this.dataUltimoContatto = dataUltimoContatto;
		this.fatturatoAnnuale = fatturatoAnnuale;
		this.telefono = telefono;
		this.emailContatto = emailContatto;
		this.nomeContatto = nomeContatto;
		this.cognomeContatto = cognomeContatto;
		this.telefonoContatto = telefonoContatto;
		this.tipoCliente = tipoCliente;
		// this.sedeLegale = sedeLegale;
		// this.sedeOperativa = sedeLegale;
	}

}
