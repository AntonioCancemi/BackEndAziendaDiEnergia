package com.buildweek.gestionale_anziendale_energia.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.buildweek.gestionale_anziendale_energia.models.Cliente;
import com.buildweek.gestionale_anziendale_energia.models.Fattura;

public interface ClienteDAOrepository extends JpaRepository<Cliente, Long>, PagingAndSortingRepository<Cliente, Long> {

	List<Cliente> findByEmailAndPec(String email, String pec);

	public boolean existsByEmail(String email);

	public boolean existsByPec(String pec);

	/*
	 * Deve essere possibile ordinare i clienti per: Nome Fatturato annuale Data di
	 * inserimento Data di ultimo contatto Provincia della sede legale.
	 */
//provincia sede legale cliente->indirizzo(Where i
//	@Query("SELECT c FROM Cliente c JOIN c.indirizzo ind WHERE c.idCliente = :clienteId AND ind.comune=:comune")
//	List<Cliente> findByClienteIdAndComune(@Param("clienteId") Long clienteId, @Param("comune") String comune);
	@Query("SELECT c FROM Cliente c JOIN c.indirizzi i ORDER BY i.comune")
	Optional<List<Cliente>> getALLClientiOrderByComune();

	// ordine alfabetico per nome
	@Query("SELECT c FROM Cliente c ORDER BY c.nomeContatto ASC")
	Optional<List<Cliente>> getAllClienteOrderBynomeContatto();

	// ordine per fatturato
	@Query("SELECT c FROM Cliente c ORDER BY c.fatturatoAnnuale ASC")
	Optional<List<Cliente>> getAllClienteOrderByFatturatoAnnuo();

// ordine per data inserimento 
	@Query("SELECT c FROM Cliente c ORDER BY c.dataInserimento DESC")
	Optional<List<Cliente>> getAllClienteOrderByDataInserimento();

// ordine di data ultimo contatto 
	@Query("SELECT c FROM Cliente c ORDER BY c.dataUltimoContatto DESC")
	Optional<List<Cliente>> getAllClienteOrderByDataUltimoContatto();

////////////////////////////////
	 Page<Cliente> findAll(Pageable pageable);

	@Query("SELECT c FROM Cliente c WHERE c.nomeContatto LIKE LOWER (CONCAT('%', :name, '%'))")
	Page<Cliente> getAllByNomeContatto(String name, Pageable pageable);
		
	@Query("SELECT c FROM Cliente c WHERE c.fatturatoAnnuale BETWEEN :amount1 AND :amount2")
	Page<Cliente> findClientesByFatturatoAnnuo(@Param("amount1") Long amount1, @Param("amount2") Long amount2, Pageable pageable);
		
	@Query("SELECT c FROM Cliente c WHERE c.dataInserimento BETWEEN :data1 AND :data2")
	Page<Cliente> findClienteyDataInserimento(@Param("data1") LocalDate data1, @Param("data2") LocalDate data2, Pageable pageable);
		
	@Query("SELECT c FROM Cliente c WHERE c.dataUltimoContatto BETWEEN :data1 AND :data2")
	Page<Cliente> findCustomerByLastContactData(@Param("data1") LocalDate data1, @Param("data2") LocalDate data2,Pageable pageable);

}
