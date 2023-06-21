package com.buildweek.gestionale_anziendale_energia.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.buildweek.gestionale_anziendale_energia.enumeration.StatoFattura;
import com.buildweek.gestionale_anziendale_energia.enumeration.TipoIndirizzo;
import com.buildweek.gestionale_anziendale_energia.models.Cliente;
import com.buildweek.gestionale_anziendale_energia.models.Fattura;
import com.buildweek.gestionale_anziendale_energia.models.Indirizzo;
import com.buildweek.gestionale_anziendale_energia.repository.ClienteDAOrepository;
import com.buildweek.gestionale_anziendale_energia.repository.IndirizziDAOrepository;
import com.buildweek.gestionale_anziendale_energia.security.exception.MyAPIException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClienteService {
	@Autowired
	ClienteDAOrepository repo;
	@Autowired
	IndirizziDAOrepository indirizziRepo;

	public String create(Cliente c) {
		List<Indirizzo> i = c.getIndirizzi();
		// check cliente
		if (!repo.findByEmailAndPec(c.getEmail(), c.getPec()).isEmpty()) {
			throw new MyAPIException(HttpStatus.FOUND, "cliente gia registrato");
		}
		// check indirizzi
		// lunghezza
		if (i.size() == 0) {
			throw new MyAPIException(HttpStatus.NOT_ACCEPTABLE, "devi inserire almeno un indirizzo");
		}
		if (i.size() > 2) {
			throw new MyAPIException(HttpStatus.NOT_ACCEPTABLE, "puoi inserire un massimo di 2 indirizzi");
		}
		// esistenza
		if (indirizziRepo.existsByCapAndAndCivicoAndComuneAndVia(i.get(0).getCap(), i.get(0).getCivico(),
				i.get(0).getComune(), i.get(0).getVia())) {
			throw new MyAPIException(HttpStatus.FOUND, "Indirizzo gia presente nel data-base");
		}
		// check secondo indirizzo
		if (i.size() == 2) {
			if (indirizziRepo.existsByCapAndAndCivicoAndComuneAndVia(i.get(1).getCap(), i.get(1).getCivico(),
					i.get(0).getComune(), i.get(0).getVia())) {
				throw new MyAPIException(HttpStatus.FOUND, "Indirizzo gia presente nel data-base");
			}
			if (i.get(0).getTipoIndirizzo() == i.get(1).getTipoIndirizzo()) {
				throw new MyAPIException(HttpStatus.NOT_ACCEPTABLE,
						"devi inserire un indirizzo per la sede Operativa e uno per quella Legale, o ne insirisci uno con Tipo Sede Unica");
			}
			for (Indirizzo indirizzo : i) {
				Boolean flag = indirizzo.getTipoIndirizzo() == TipoIndirizzo.SEDE_UNICA;
				if (flag && i.size() == 2) {
					throw new MyAPIException(HttpStatus.NOT_ACCEPTABLE,
							"Hai inserito sede unica non puoi avere piu di 2 indirizzi");
				}
			}

		}

		// salvataggui undirizzi
		for (Indirizzo indirizzo : i) {
			indirizziRepo.save(indirizzo);
		}
		c.setDataInserimento(LocalDate.now());
		repo.save(c);
		return "cliete salvato";
	}

	public String update(Cliente c) {
		List<Indirizzo> i = c.getIndirizzi();
		// check cliente
		if (!repo.findByEmailAndPec(c.getEmail(), c.getPec()).isEmpty()) {
			throw new MyAPIException(HttpStatus.FOUND, "cliente gia registrato");
		}
		// check indirizzi
		// lunghezza
		if (i.size() == 0) {
			throw new MyAPIException(HttpStatus.NOT_ACCEPTABLE, "devi inserire almeno un indirizzo");
		}
		if (i.size() > 2) {
			throw new MyAPIException(HttpStatus.NOT_ACCEPTABLE, "puoi inserire un massimo di 2 indirizzi");
		}
		// esistenza
		if (indirizziRepo.existsByCapAndAndCivicoAndComuneAndVia(i.get(0).getCap(), i.get(0).getCivico(),
				i.get(0).getComune(), i.get(0).getVia())) {
			throw new MyAPIException(HttpStatus.FOUND, "Indirizzo gia presente nel data-base");
		}
		// check secondo indirizzo
		if (i.size() == 2) {
			if (indirizziRepo.existsByCapAndAndCivicoAndComuneAndVia(i.get(1).getCap(), i.get(1).getCivico(),
					i.get(0).getComune(), i.get(0).getVia())) {
				throw new MyAPIException(HttpStatus.FOUND, "Indirizzo gia presente nel data-base");
			}
			if (i.get(0).getTipoIndirizzo() == i.get(1).getTipoIndirizzo()) {
				throw new MyAPIException(HttpStatus.NOT_ACCEPTABLE,
						"devi inserire un indirizzo per la sede Operativa e uno per quella Legale, o ne insirisci uno con Tipo Sede Unica");
			}
		}
		// salvataggio undirizzi
		for (Indirizzo indirizzo : i) {
			indirizziRepo.save(indirizzo);
		}
		repo.save(c);
		return "cliente salvato";
	}

	public String removeById(Long id) {
		if (!repo.existsById(id)) {
			throw new EntityNotFoundException("Cliente[id:" + id + "] dosent exits");
		}
		repo.deleteById(id);
		return "Cliente [id:" + id + "] removed!!!";
	}

	// GET HANDLER
	public List<Cliente> get(String type, Long id) {
		String value = null;
		if (StatoFattura.valueOf(value).equals(StatoFattura.NONPAGATO))
			if (repo.findAll().isEmpty()) {
				throw new EntityNotFoundException("No entity Found");
			}
		switch (type) {
		case "nome_contatto":
			return repo.getAllClienteOrderBynomeContatto().get();
		case "provincia":
			return repo.getALLClientiOrderByComune().get();
		case "fatturato_annuale":
			return repo.getAllClienteOrderByFatturatoAnnuo().get();
		case "data_inserimento":
			return repo.getAllClienteOrderByFatturatoAnnuo().get();
		case "data_ultimo_contatto":
			return repo.getAllClienteOrderByDataUltimoContatto().get();
		case "id":
			List<Cliente> l = new ArrayList<>();
			l.add(repo.findById(id).get());
			return l;
		default:
			return repo.findAll();
		}
	}

///////////////////////////////////////

   public Page<Cliente> getAllClientePag(Pageable pag) {
   return  repo.findAll(pag);	
   }
   
   public Page<Cliente> getAllClienteByNomeContatto(String nome, Pageable pag) {
		return  repo.getAllByNomeContatto(nome, pag);
	}
  
   public Page<Cliente> getAllClienteByFatturatoAnnuo(Long amount1, Long amount2, Pageable pag) {
		return  repo.findClientesByFatturatoAnnuo(amount1, amount2, pag);
	}
   
   public Page<Cliente> getAllClienteByDataInserimento(LocalDate data1, LocalDate data2, Pageable pag) {
		return  repo.  findClienteyDataInserimento(data1, data2, pag);
	}

   public Page<Cliente> getAllClienteByDataUltimoContatto(LocalDate data1, LocalDate data2, Pageable pag) {
		return  repo.  findClienteByDataUltimoContatto(data1, data2, pag);
	}
}
