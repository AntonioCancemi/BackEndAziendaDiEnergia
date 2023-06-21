package com.buildweek.gestionale_anziendale_energia.runner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.buildweek.gestionale_anziendale_energia.enumeration.TipoCliente;
import com.buildweek.gestionale_anziendale_energia.models.Cliente;
import com.buildweek.gestionale_anziendale_energia.models.Indirizzo;
import com.buildweek.gestionale_anziendale_energia.repository.ClienteDAOrepository;
import com.buildweek.gestionale_anziendale_energia.service.ClienteService;
import com.github.javafaker.Faker;

@Component
public class ClienteRunner implements ApplicationRunner {
	@Autowired
	ClienteDAOrepository repo;

	@Autowired
	ClienteService clienteService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("clienti runner...");
		if (repo.findAll().isEmpty()) {
			Faker fake = new Faker();

			Random rand = new Random();
			Indirizzo ind = new Indirizzo();
			Cliente c = new Cliente();
			List<TipoCliente> ltp = new ArrayList<TipoCliente>();
			ltp.add(TipoCliente.PA);
			ltp.add(TipoCliente.SAS);
			ltp.add(TipoCliente.SPA);
			ltp.add(TipoCliente.SRL);
			for (int i = 0; i < 10; i++) {

				c.setCognomeContatto(fake.name().lastName());
				c.setNomeContatto(fake.name().firstName());
				c.setEmail(fake.internet().emailAddress());
				c.setEmailContatto(fake.internet().emailAddress());
				c.setFatturatoAnnuale(rand.nextInt(100000000));
				c.setPec(fake.internet().safeEmailAddress());
				c.setRagioneSociale(fake.commerce().department());
				c.setTelefono(fake.phoneNumber().phoneNumber());
				c.setTelefonoContatto(fake.phoneNumber().cellPhone());
				c.setTipoCliente(ltp.get(rand.nextInt(4)));

				System.out.println(c);
				clienteService.create(c);
			}

		}
	}

}
