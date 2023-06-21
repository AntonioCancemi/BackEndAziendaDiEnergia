package com.buildweek.gestionale_anziendale_energia.controller;

import java.time.LocalDate;

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

import com.buildweek.gestionale_anziendale_energia.enumeration.StatoFattura;
import com.buildweek.gestionale_anziendale_energia.models.ComuneItalia;
import com.buildweek.gestionale_anziendale_energia.models.Fattura;
import com.buildweek.gestionale_anziendale_energia.models.FatturaDTO;
import com.buildweek.gestionale_anziendale_energia.service.FatturaService;

//@CrossOrigin(origins =  "*", maxAge = 360000)
@RestController
@RequestMapping("/api/fatture")
public class FatturaController {

	@Autowired
	private FatturaService service;

	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(service.getAll());
	}

	@GetMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		return ResponseEntity.ok(service.getFatturaByNumero(id));
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> save(@RequestBody FatturaDTO f) {
		return ResponseEntity.ok(service.createFattura(f));
	}

	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> update(@RequestBody Fattura f) {
		return ResponseEntity.ok(service.updateFattura(f));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> removeById(@PathVariable Long id) {
		return ResponseEntity.ok(service.removeFattura(id));
	}

	//PAGINAZIONE
	
	@GetMapping("/page")
	public ResponseEntity<Page<Fattura>> getPageableAllFattura(Pageable pageable) {
		//http://localhost:8080/api/comuni/page?size=2&page=2&sort=name,ASC
		return ResponseEntity.ok(service.getAllFatturePag(pageable));
	}
	
	@GetMapping("/statofattura/{statoFattura}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getPageableFatturaByStato(@PathVariable StatoFattura statoFattura, Pageable pageable ) {
		return new ResponseEntity<Page<Fattura>>(service.getAllFattureByStatoPag(statoFattura, pageable), HttpStatus.FOUND);
	}
	
	@GetMapping("/datafattura/{dataFattura}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getPageableFatturaByData(@PathVariable LocalDate data, Pageable pageable ) {
		return new ResponseEntity<Page<Fattura>>(service.getByDataPag(data, pageable), HttpStatus.FOUND);
	}
	
	@GetMapping("/rangedatafattura/{data1}&{data2}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getPageableFatturaByRangeData(@PathVariable LocalDate data1, @PathVariable LocalDate data2, Pageable pageable ) {
		return new ResponseEntity<Page<Fattura>>(service.getByRangeData(data1, data2, pageable), HttpStatus.FOUND);
	}
	
	
}
