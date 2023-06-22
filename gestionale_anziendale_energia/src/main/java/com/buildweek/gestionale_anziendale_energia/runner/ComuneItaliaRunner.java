package com.buildweek.gestionale_anziendale_energia.runner;

import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.buildweek.gestionale_anziendale_energia.models.ComuneItalia;
import com.buildweek.gestionale_anziendale_energia.repository.ComuneItaliaDaoRepository;
import com.buildweek.gestionale_anziendale_energia.service.ComuneItaliaService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

@Component
public class ComuneItaliaRunner implements ApplicationRunner {

		@Autowired ComuneItaliaDaoRepository repo;

		@Override
		public void run(ApplicationArguments args) throws Exception {

		if(repo.findAll().isEmpty()) {
			setComuni();			
		}

		}

	public void setComuni() throws IOException, CsvValidationException {
		try (CSVReader reader = new CSVReader(new FileReader(
				"C:\\Users\\user\\Desktop\\comuni-italiani.csv"))) {
			String[] lineInArray;
			while ((lineInArray = reader.readNext()) != null) {
				String[] parts = lineInArray[0].split(";");
				String part1 = parts[0];
				String part2 = parts[1];
				String part3 = parts[2];
				String part4 = parts[3];
				ComuneItalia ci = new ComuneItalia(part2, part1, part3, part4);
				repo.save(ci);
			}
		}

	}

}
