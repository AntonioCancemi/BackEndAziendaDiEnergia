package com.buildweek.gestionale_anziendale_energia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buildweek.gestionale_anziendale_energia.models.Indirizzo;
import com.buildweek.gestionale_anziendale_energia.repository.IndirizziDAOrepository;

import jakarta.persistence.EntityExistsException;

@Service
public class IndirizzoService {

	@Autowired
	IndirizziDAOrepository repo;

	public Indirizzo getById(Long id) {
		if (!repo.existsById(id)) {
			throw new EntityExistsException("Indirizzo non esistente!!");
		}
		return repo.findById(id).get();
	}

	public Indirizzo createComune(Indirizzo i) {
		return repo.save(i);
	}
}
