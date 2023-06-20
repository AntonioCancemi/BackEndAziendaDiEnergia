package com.buildweek.gestionale_anziendale_energia.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.buildweek.gestionale_anziendale_energia.enumeration.TipoIndirizzo;
import com.buildweek.gestionale_anziendale_energia.models.Cliente;
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

		if (!repo.findByEmailAndPec(c.getEmail(), c.getPec()).isEmpty()) {
			throw new EntityNotFoundException("entity Found");
		}
		if (c.getIndirizzi().size() == 0) {
			throw new MyAPIException(HttpStatus.NOT_ACCEPTABLE, "devi inserire almeno un indirizzo");
		}
		if (c.getIndirizzi().size() > 2) {
			throw new MyAPIException(HttpStatus.NOT_ACCEPTABLE, "puoi inserire un massimo di 2 indirizzi");
		}
		if (c.getIndirizzi().get(0).getTipoIndirizzo() == TipoIndirizzo.SEDE_UNICA && c.getIndirizzi().size() > 1) {
			throw new MyAPIException(HttpStatus.NOT_ACCEPTABLE,
					"hai inserito sede unica non puoi inserire altri indirizzi");
		} else if (c.getIndirizzi().size() == 2) {
			List<TipoIndirizzo> tipi = new ArrayList<>();
			tipi.add(c.getIndirizzi().get(0).getTipoIndirizzo());
			tipi.add(c.getIndirizzi().get(1).getTipoIndirizzo());
			if (!tipi.contains(TipoIndirizzo.SEDE_LEGALE) && tipi.contains(TipoIndirizzo.SEDE_OPERATIVA)) {
				throw new MyAPIException(HttpStatus.NOT_ACCEPTABLE,
						"devi inserire un indirizzo per la sede Operativa e uno per quella Legale, o ne insirisci uno con Tipo Sede Unica");
			}
		}
		if (c.getIndirizzi().size() == 1) {
			Indirizzo i1 = c.getIndirizzi().get(0);
//			i1.setCliente(c);
			indirizziRepo.save(i1);
		} else {
			Indirizzo i1 = c.getIndirizzi().get(0);
			Indirizzo i2 = c.getIndirizzi().get(1);
//			i1.setCliente(c);
//			i2.setCliente(c);
			indirizziRepo.save(i1);
			indirizziRepo.save(i2);
		}
		repo.save(c);
		c.setDataInserimento(LocalDate.now());
		System.out.println(c.getIndirizzi().get(0));
		return "cliete salvato";
	}

	public String update(Cliente c) {
		if (!repo.existsById(c.getIdCliente())) {
			throw new EntityNotFoundException("Cliente[id:" + c.getIdCliente() + "] not Found");
		}
		if (c.getIndirizzi().size() == 0) {
			throw new MyAPIException(HttpStatus.NOT_ACCEPTABLE, "devi inserire almeno un indirizzo");
		}
		if (c.getIndirizzi().size() > 2) {
			throw new MyAPIException(HttpStatus.NOT_ACCEPTABLE, "puoi inserire un massimo di 2 indirizzi");
		}
		repo.save(c);
		return "cliente salvato";
	}

	public String remove(Long id) {
		if (repo.existsById(id)) {
			throw new EntityNotFoundException("Cliente[id:" + id + "] not Found");
		}
		repo.deleteById(id);
		return "cliente  eliminato!!!";
	}

	public String removeById(Long id) {
		if (!repo.existsById(id)) {
			throw new EntityNotFoundException("Cliente[id:" + id + "] dosent exits");
		}
		repo.deleteById(id);
		return "Cliente [id:" + id + "] removed!!!";
	}

	public List<Cliente> getAll() {
		if (repo.findAll().isEmpty()) {
			throw new EntityNotFoundException("No entity Found");
		}
		return repo.findAll();
	}

	public Cliente getById(Long id) {
		if (!repo.existsById(id)) {
			throw new EntityNotFoundException("No entity Found");
		}
		return repo.findById(id).get();
	}
}
