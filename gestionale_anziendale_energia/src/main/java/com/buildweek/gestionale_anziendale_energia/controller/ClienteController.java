package com.buildweek.gestionale_anziendale_energia.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buildweek.gestionale_anziendale_energia.models.Cliente;
import com.buildweek.gestionale_anziendale_energia.service.ClienteService;

@RestController
@RequestMapping("/api/clienti")
public class ClienteController {
	@Autowired
	private ClienteService service;

	@GetMapping("/{type}/{id}")
	public ResponseEntity<?> get(@PathVariable String type, @PathVariable Long id) {

		return ResponseEntity.ok(service.get(type, id));
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody Cliente c) {
		return ResponseEntity.ok(service.create(c));
	}

	@PutMapping()
	public ResponseEntity<?> update(@RequestBody Cliente c) {
		return ResponseEntity.ok(service.update(c));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeById(@PathVariable Long id) {
		return ResponseEntity.ok(service.removeById(id));
	}
	
	//PAGINAZIONE
	
	@GetMapping("/page")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Page<Cliente>> getPageableAllCliente(Pageable pageable) {
		//http://localhost:8080/api/clienti/page?size=2&page=2&sort=name,ASC
		return ResponseEntity.ok(service.getAllClientePag(pageable));
	}
	
	@GetMapping("/page/nome-contatto/{nome}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getPageableAllClienteByNome(@PathVariable String nome, Pageable pageable) {
		return new ResponseEntity<Page<Cliente>>(service.getAllClienteByNomeContatto(nome, pageable), HttpStatus.FOUND);
	}
	
	@GetMapping("/page/cliente-fatturatoannuo/{amount1}&{amount2}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getPageableAllClienteByFatturatoAnnuo(@PathVariable Long amount1, @PathVariable Long amount2,Pageable pageable) {
		return new ResponseEntity<Page<Cliente>>(service.getAllClienteByFatturatoAnnuo(amount1, amount2, pageable), HttpStatus.FOUND);
	}
	
	@GetMapping("/page/cliente-datainserimento/{data1}&{data2}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getPageableAllClienteByDataInserimento(@PathVariable LocalDate data1, @PathVariable LocalDate data2,Pageable pageable) {
		return new ResponseEntity<Page<Cliente>>(service.getAllClienteByDataInserimento(data1, data2, pageable), HttpStatus.FOUND);
	}
	
	@GetMapping("/page/cliente-dataultimocontatto/{data1}&{data2}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getPageableAllClienteByDataUltimoContatto(@PathVariable LocalDate data1, @PathVariable LocalDate data2,Pageable pageable) {
		return new ResponseEntity<Page<Cliente>>(service.getAllClienteByDataUltimoContatto(data1, data2, pageable), HttpStatus.FOUND);
	}

}
