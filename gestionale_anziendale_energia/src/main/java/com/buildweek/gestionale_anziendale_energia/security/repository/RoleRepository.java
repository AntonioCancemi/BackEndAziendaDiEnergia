package com.buildweek.gestionale_anziendale_energia.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.buildweek.gestionale_anziendale_energia.security.entity.ERole;
import com.buildweek.gestionale_anziendale_energia.security.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByRoleName(ERole roleName);

}
