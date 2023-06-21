package com.buildweek.gestionale_anziendale_energia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.buildweek.gestionale_anziendale_energia.models.Indirizzo;

public interface IndirizziDAOrepository extends JpaRepository<Indirizzo, Long> {
	boolean existsByCapAndAndCivicoAndComuneAndVia(Integer cap, String civico, String comune, String via);

}
