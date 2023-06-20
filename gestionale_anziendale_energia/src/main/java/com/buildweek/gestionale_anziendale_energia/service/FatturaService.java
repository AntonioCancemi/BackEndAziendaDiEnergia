package com.buildweek.gestionale_anziendale_energia.service;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.buildweek.gestionale_anziendale_energia.enumeration.StatoFattura;
import com.buildweek.gestionale_anziendale_energia.models.Cliente;
import com.buildweek.gestionale_anziendale_energia.models.Fattura;
import com.buildweek.gestionale_anziendale_energia.repository.FatturaDAOrepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class FatturaService {
	
	@Autowired FatturaDAOrepository repo;
	@Autowired ClienteService clienteService;
	
	public List<Fattura> getAll() {
		return (List<Fattura>) repo.findAll();
	}
	
	public Fattura getById(Long id) {
		if(!repo.existsById(id)) {
			throw new EntityNotFoundException("Fattura non esiste!");
		}
		return repo.findById(id).get();
	}
	
	public Fattura createFattura(Fattura fattura) {
		if(repo.existsByNumero(fattura.getNumero())) {
			throw new EntityExistsException("La fattura con " + fattura.getNumero() + " è già stata creata");
		}
		repo.save(fattura);
		return fattura;
	}
	
	public Fattura updateFattura(Fattura fattura) {
		if(repo.existsByNumero(fattura.getNumero())){
			throw new EntityExistsException(" Fattura [id:" + fattura.getNumero() + "] not Found");
		}
		repo.save(fattura);
		return fattura;
	}
	
	public String removeFattura(Long id) {
		if(!repo.existsById(id)) {
			throw new EntityExistsException("Fattura not found");
		}
		repo.deleteById(id);
		return "Fattura cancellata!";
	}

	/////////////////////////////////////////

	public List<Fattura> getByCliente(Cliente c) {
		return repo.findByCliente(c);
	}
	
    public List<Fattura>getByStato(StatoFattura s){
    return repo.findByStato(s);
    }
    
    public List<Fattura> getByData(LocalDate dataFattura){
    	return repo.findByData(dataFattura);
    }
    
    public List<Fattura> getByAnno(Integer anno){
    	return repo.findByAnno(anno);
    }
    
	public List<Fattura> getByRangeImporto(double d1, double d2) {
		return repo.findByRangeImporto(d1, d2);
    }

    ///////////////////////////////////////
	public Page<Fattura> getAllFatturePag(Pageable pag) {
		return (Page<Fattura>) repo.findAll(pag);
	}
	
	public Page<Fattura> getByDataPag(LocalDate data, Pageable pageable){
      return repo.findByDataPag(data, pageable);
	   }
	
	public Page<Fattura> getByRangeData(LocalDate startDate, LocalDate endDate, Pageable pageable){
		  return repo.findByRangeDataPag(startDate, endDate, pageable);
	   }
	
    
    
}

