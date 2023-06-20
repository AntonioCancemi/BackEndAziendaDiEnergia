package com.buildweek.gestionale_anziendale_energia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buildweek.gestionale_anziendale_energia.models.ComuneItalia;
import com.buildweek.gestionale_anziendale_energia.service.ComuneItaliaService;

@RestController
@RequestMapping("/api/comuni")
public class ComuneItaliaController {

	@Autowired ComuneItaliaService ciService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById (@PathVariable Long id){
		return ResponseEntity.ok(ciService.getById(id));
	}
	
	@PostMapping
	public ResponseEntity<?> create (@RequestBody ComuneItalia c){
		return ResponseEntity.ok(ciService.createComune(c));
	}
}
