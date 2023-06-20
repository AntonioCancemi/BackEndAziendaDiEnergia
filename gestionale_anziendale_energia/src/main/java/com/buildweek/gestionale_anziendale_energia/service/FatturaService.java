package com.buildweek.gestionale_anziendale_energia.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.buildweek.gestionale_anziendale_energia.enumeration.StatoFattura;
import com.buildweek.gestionale_anziendale_energia.models.Fattura;
import com.buildweek.gestionale_anziendale_energia.models.FatturaDTO;
import com.buildweek.gestionale_anziendale_energia.repository.ClienteDAOrepository;
import com.buildweek.gestionale_anziendale_energia.repository.FatturaDAOrepository;
import com.buildweek.gestionale_anziendale_energia.security.exception.MyAPIException;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class FatturaService {

	@Autowired
	FatturaDAOrepository repo;
	@Autowired
	ClienteDAOrepository repoCliente;
	static Fattura fattura = new Fattura();

	public List<Fattura> getAll() {
		return (List<Fattura>) repo.findAll();
	}

	public Fattura getById(Long id) {
		if (!repo.existsById(id)) {
			throw new EntityNotFoundException("Fattura non esiste!");
		}
		return repo.findById(id).get();
	}

	public Fattura createFattura(FatturaDTO DTO) {
		if (repo.existsByNumeroFattura(DTO.getNumeroFattura())) {
			throw new EntityExistsException("La fattura n. " + DTO.getNumeroFattura() + " è già stata creata");
		}
		if (!repoCliente.existsById(DTO.getIdCliente())) {
			throw new MyAPIException(HttpStatus.NOT_FOUND, "Cliente[" + DTO.getIdCliente() + "] non esiste");
		}
		Fattura fattura = new Fattura();
		fattura.setAnno(DTO.getAnno());
		fattura.setCliente(repoCliente.findById(DTO.getIdCliente()).get());
		fattura.setDataFattura(DTO.getDataFattura());
		fattura.setImporto(DTO.getImporto());
		fattura.setNumeroFattura(DTO.getNumeroFattura());
		fattura.setStatoFattura(DTO.getStatoFattura());
		
		System.out.println(fattura);
		repo.save(fattura);
		return fattura;
	}

	public Fattura updateFattura(Fattura fattura) {
		if (repo.existsById(fattura.getId())) {
			throw new EntityExistsException(" Fattura [id:" + fattura.getId() + "] not Found");
		}
		repo.save(fattura);
		return fattura;
	}

	public String removeFattura(Long id) {
		if (!repo.existsById(id)) {
			throw new EntityExistsException("Fattura not found");
		}
		repo.deleteById(id);
		return "Fattura cancellata!";
	}

	/////////////////////////////////////////
	public Fattura getFatturaByNumero(Long id) {
		if (repo.existsById(id)) {
			throw new EntityNotFoundException("Fattura non esiste!");
		}
		return repo.findById(id).get();
	}

//	public List<Fattura> getByCliente(Cliente c) {
//		return repo.findByCliente(c);
//	}
//	
//	 public List<Fattura> listatoByCliente(Long id) {
//		 Cliente c = servCliente.getById(id);
//		 return repo.findByCliente(c);		 
//	   }

	public List<Fattura> getByStato(StatoFattura s) {
		return repo.findByStatoFattura(s);
	}

	public List<Fattura> getByData(LocalDate dataFattura) {
		return repo.findByDataFattura(dataFattura);
	}

	public List<Fattura> getByAnno(Integer anno) {
		return repo.findByAnno(anno);
	}

	public List<Fattura> getByRangeImporto(double d1, double d2) {
		return repo.findByRangeImporto(d1, d2);
	}

	///////////////////////////////////////

	public Page<Fattura> getAllFatturePag(Pageable pag) {
		return (Page<Fattura>) repo.findAll(pag);
	}

	public Page<Fattura> getByDataPag(LocalDate data, Pageable pageable) {
		return repo.findByDataFattura(data, pageable);
	}

	public Page<Fattura> getByRangeData(LocalDate startDate, LocalDate endDate, Pageable pageable) {
		return repo.findByRangeDataPag(startDate, endDate, pageable);
	}

}
