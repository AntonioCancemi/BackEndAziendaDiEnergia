package com.buildweek.gestionale_anziendale_energia.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.buildweek.gestionale_anziendale_energia.enumeration.StatoFattura;
import com.buildweek.gestionale_anziendale_energia.models.Fattura;

@Repository
public interface FatturaDAOrepository extends JpaRepository<Fattura, Long>, PagingAndSortingRepository<Fattura, Long> {

	boolean existsByNumeroFattura(Long numeroFattura);

	// filtra per cliente
	// List<Fattura> findByCliente(Cliente cliente);
	// filtra per stato
	List<Fattura> findByStatoFattura(StatoFattura statoFattura);

	// filtra per data
	List<Fattura> findByDataFattura(LocalDate dataFattura);

	// filtra per anno
	List<Fattura> findByAnno(Integer anno);

	// filtra pe range di importi
	@Query("SELECT f FROM Fattura f WHERE f.importo BETWEEN :value1 AND :value2")
	List<Fattura> findByRangeImporto(@Param("value1") double value1, @Param("value2") double value2);

	///////////////////////////////

	 Page<Fattura> findAll(Pageable pageable);
 	 Page<Fattura> findByStatoFattura(StatoFattura statoFattura, Pageable pageable);
     Page<Fattura> findByDataFattura(LocalDate data, Pageable pageable);

	@Query("SELECT f FROM Fattura f WHERE f.dataFattura BETWEEN :inizio AND :fine")
	public Page<Fattura> findByRangeDataPag(LocalDate inizio, LocalDate fine, Pageable pageable);

}
