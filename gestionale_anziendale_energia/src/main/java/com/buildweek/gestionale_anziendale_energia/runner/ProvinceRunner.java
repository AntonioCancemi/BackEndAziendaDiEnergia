package com.buildweek.gestionale_anziendale_energia.runner;

import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.buildweek.gestionale_anziendale_energia.models.ProvinceItalia;
import com.buildweek.gestionale_anziendale_energia.repository.ProvinciaItaliaDaoRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

@Component
public class ProvinceRunner implements ApplicationRunner {

	@Autowired
	ProvinciaItaliaDaoRepository repo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (repo.findAll().isEmpty()) {

			setProvince();
		}

	}

	public void setProvince() throws IOException, CsvValidationException {

		try (CSVReader reader = new CSVReader(new FileReader(
				"C:\\Users\\antos\\Desktop\\BackEndAziendaDiEnergia\\gestionale_anziendale_energia\\src\\main\\resources\\province-italiane.csv"))) {
			String[] lineInArray;
			while ((lineInArray = reader.readNext()) != null) {
				String[] parts = lineInArray[0].split(";");
				String part1 = parts[0];
				String part2 = parts[1];
				String part3 = parts[2];

				ProvinceItalia p = new ProvinceItalia(part1, part2, part3);
				repo.save(p);
			}
		}
	}
}
