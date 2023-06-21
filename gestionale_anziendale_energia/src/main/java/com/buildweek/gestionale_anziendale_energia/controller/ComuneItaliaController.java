package com.buildweek.gestionale_anziendale_energia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
	
	// PAGINAZIONE
	
	@GetMapping("/page")
	public ResponseEntity<Page<ComuneItalia>> getPageableAllComune(Pageable pageable) {
		//http://localhost:8080/api/comuni/page?size=2&page=2&sort=name,ASC
		return ResponseEntity.ok(ciService.getPageableAll(pageable));
	}
	
	@GetMapping("/cap/{cap}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getPageableComuneByCap(@PathVariable String cap, Pageable pageable ) {
		return new ResponseEntity<Page<ComuneItalia>>(ciService.getComuneByCap(cap, pageable), HttpStatus.FOUND);
	}
	
	@GetMapping("/prov/{provincia}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getPageableComuneByProvincia(@PathVariable String provincia, Pageable pageable) {
		return new ResponseEntity<Page<ComuneItalia>>(ciService.getComuneByProvincia(provincia, pageable), HttpStatus.FOUND);
	}
}
