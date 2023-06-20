package com.buildweek.gestionale_anziendale_energia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.buildweek.gestionale_anziendale_energia.models.Fattura;
import com.buildweek.gestionale_anziendale_energia.service.FatturaService;

//@CrossOrigin(origins =  "*", maxAge = 360000)
@RestController
@RequestMapping("/api/fatture")
public class FatturaController {
	
	@Autowired private FatturaService service;
	
	@GetMapping
	//@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(service.getAll());
	}
	
	@GetMapping("/{numero}")
	//@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getById(@PathVariable Integer numero) {
      return ResponseEntity.ok(service.getFatturaByNumero(numero));
	}
	
	
	@PostMapping
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> save(@RequestBody Fattura f) {
		return ResponseEntity.ok(service.createFattura(f));
	}
	
	@PutMapping
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> update(@RequestBody Fattura f) {
		return ResponseEntity.ok(service.updateFattura(f));
	}
	
	
	@DeleteMapping("/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> removeById(@PathVariable Long id) {
		return ResponseEntity.ok(service.removeFattura(id));
	}
	

}
