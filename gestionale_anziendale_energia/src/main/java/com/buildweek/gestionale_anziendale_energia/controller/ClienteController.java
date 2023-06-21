package com.buildweek.gestionale_anziendale_energia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

}
