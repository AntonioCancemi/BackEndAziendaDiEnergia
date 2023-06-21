package com.buildweek.gestionale_anziendale_energia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buildweek.gestionale_anziendale_energia.models.ProvinceItalia;
import com.buildweek.gestionale_anziendale_energia.repository.ProvinciaItaliaDaoRepository;

import jakarta.persistence.EntityExistsException;

@Service
public class ProvinciaItaliaService {

	@Autowired
	ProvinciaItaliaDaoRepository repo;

	public ProvinceItalia getById(Long id) {
		if (!repo.existsById(id)) {
			throw new EntityExistsException("Comune non esistente!!");
		}
		return repo.findById(id).get();
	}

	public ProvinceItalia createComune(ProvinceItalia c) {
		return repo.save(c);
	}
}
