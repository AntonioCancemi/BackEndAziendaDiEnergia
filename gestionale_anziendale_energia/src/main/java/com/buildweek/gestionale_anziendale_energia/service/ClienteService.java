package com.buildweek.gestionale_anziendale_energia.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buildweek.gestionale_anziendale_energia.models.Cliente;
import com.buildweek.gestionale_anziendale_energia.repository.ClienteDAOrepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClienteService {
	@Autowired
	ClienteDAOrepository repo;

	public String create(Cliente c) {
		if (!repo.findByEmailAndPec(c.getEmail(), c.getPec()).isEmpty()) {
			throw new EntityNotFoundException(" entity Found");
		}
		c.setDataInserimento(LocalDate.now());
		repo.save(c);
		return "cliete salvato";
	}

	public String update(Cliente c) {
		if (!repo.existsById(c.getIdCliente())) {
			throw new EntityNotFoundException("Cliente[id:" + c.getIdCliente() + "] not Found");
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
