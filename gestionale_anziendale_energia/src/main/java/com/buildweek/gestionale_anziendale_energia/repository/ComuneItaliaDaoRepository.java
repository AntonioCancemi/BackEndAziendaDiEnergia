package com.buildweek.gestionale_anziendale_energia.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.buildweek.gestionale_anziendale_energia.models.ComuneItalia;

public interface ComuneItaliaDaoRepository extends JpaRepository<ComuneItalia, Long>, PagingAndSortingRepository<ComuneItalia, Long>{

	Page<ComuneItalia> findByCap(Long cap, Pageable pageable);
	Page<ComuneItalia> findByProvincia(String provincia, Pageable pageable);
	
}
