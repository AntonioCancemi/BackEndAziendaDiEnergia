package com.buildweek.gestionale_anziendale_energia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buildweek.gestionale_anziendale_energia.models.ComuneItalia;
import com.buildweek.gestionale_anziendale_energia.repository.ComuneItaliaDaoRepository;

import jakarta.persistence.EntityExistsException;

@Service
public class ComuneItaliaService {

		@Autowired ComuneItaliaDaoRepository repo;
		
		public ComuneItalia getById(Long id) {
			if(!repo.existsById(id)) {
				throw new EntityExistsException("Comune non esistente!!");
			}
			return repo.findById(id).get();
		}
		
		public ComuneItalia createComune (ComuneItalia c) {
			return repo.save(c);
		}
}
