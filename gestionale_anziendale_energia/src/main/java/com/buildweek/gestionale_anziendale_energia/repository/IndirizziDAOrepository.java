package com.buildweek.gestionale_anziendale_energia.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.buildweek.gestionale_anziendale_energia.enumeration.TipoIndirizzo;
import com.buildweek.gestionale_anziendale_energia.models.Indirizzo;

public interface IndirizziDAOrepository extends JpaRepository<Indirizzo, Long>, PagingAndSortingRepository<Indirizzo, Long> {
	boolean existsByCapAndAndCivicoAndComuneAndVia(Integer cap, String civico, String comune, String via);

	Page<Indirizzo> findAll(Pageable pageable);
	Page<Indirizzo> findByTipoIndirizzo(TipoIndirizzo tipoIndirizzo, Pageable pageable);
}
