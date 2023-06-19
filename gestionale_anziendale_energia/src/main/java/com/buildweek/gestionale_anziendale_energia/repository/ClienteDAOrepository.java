package com.buildweek.gestionale_anziendale_energia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.buildweek.gestionale_anziendale_energia.models.Cliente;

public interface ClienteDAOrepository extends JpaRepository<Cliente, Long> {
	List<Cliente> findByEmailAndPec(String email, String pec);
}
