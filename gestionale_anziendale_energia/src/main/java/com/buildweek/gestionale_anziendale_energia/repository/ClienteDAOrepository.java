package com.buildweek.gestionale_anziendale_energia.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.buildweek.gestionale_anziendale_energia.models.Cliente;

public interface ClienteDAOrepository extends JpaRepository<Cliente, Long> {

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

//provincia della sede legale
//@Query("SELECT c FROM Cliente c INNER JOIN c.sedeLegale")

}
