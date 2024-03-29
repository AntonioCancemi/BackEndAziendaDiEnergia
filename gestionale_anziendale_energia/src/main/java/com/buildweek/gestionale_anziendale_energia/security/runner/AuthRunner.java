package com.buildweek.gestionale_anziendale_energia.security.runner;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.buildweek.gestionale_anziendale_energia.security.controller.AuthController;
import com.buildweek.gestionale_anziendale_energia.security.entity.ERole;
import com.buildweek.gestionale_anziendale_energia.security.entity.Role;
import com.buildweek.gestionale_anziendale_energia.security.payload.LoginDto;
import com.buildweek.gestionale_anziendale_energia.security.payload.RegisterDto;
import com.buildweek.gestionale_anziendale_energia.security.repository.RoleRepository;
import com.buildweek.gestionale_anziendale_energia.security.repository.UserRepository;
import com.buildweek.gestionale_anziendale_energia.security.service.AuthService;

@Component
public class AuthRunner implements ApplicationRunner {

	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AuthService authService;

	private Set<Role> adminRole;
	private Set<Role> moderatorRole;
	private Set<Role> userRole;
	@Autowired
	private AuthController auth;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Run...");
		// Metodo da lanciare solo la prima volta
		// Serve per salvare i ruoli nel DB
		if (roleRepository.findAll().isEmpty()) {
			setRoleDefault();
		}
		if (userRepository.findAll().isEmpty()) {

			Set<String> roles = new HashSet<>();
			roles.add("ADMIN");
			roles.add("USER");
			RegisterDto registerDto = new RegisterDto().builder().name("Antonio").lastname("Cancemi")
					.username("salcan96").email("salcan@example.com").password("sadaufawf").roles(roles).build();
			auth.register(registerDto);

			LoginDto loginDto = new LoginDto().builder().username("salcan96").password("sadaufawf").build();
			auth.login(loginDto);
		} else {
			LoginDto loginDto = new LoginDto().builder().username("salcan96").password("sadaufawf").build();
			auth.login(loginDto);
		}

	}

	private void setRoleDefault() {
		Role admin = new Role();
		admin.setRoleName(ERole.ROLE_ADMIN);
		roleRepository.save(admin);

		Role user = new Role();
		user.setRoleName(ERole.ROLE_USER);
		roleRepository.save(user);

		Role moderator = new Role();
		moderator.setRoleName(ERole.ROLE_MODERATOR);
		roleRepository.save(moderator);

//		adminRole = new HashSet<Role>();
//		adminRole.add(admin);
//		adminRole.add(moderator);
//		adminRole.add(user);
//		
//		moderatorRole = new HashSet<Role>();
//		moderatorRole.add(moderator);
//		moderatorRole.add(user);
//		
//		userRole = new HashSet<Role>();
//		userRole.add(user);
	}

}
