package com.buildweek.gestionale_anziendale_energia.runner;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.buildweek.gestionale_anziendale_energia.models.ComuneItalia;
import com.buildweek.gestionale_anziendale_energia.models.ProvinceItalia;
import com.buildweek.gestionale_anziendale_energia.service.ComuneItaliaService;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvValidationException;

@Component
public class ComuneItaliaRunner implements ApplicationRunner {

	@Autowired
	ComuneItaliaService ciService;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		setComuni();

	}

	public void setComuni() throws IOException, CsvValidationException {
		try (CSVReader reader = new CSVReader(new FileReader(
				"C:\\Users\\antos\\Desktop\\BackEndAziendaDiEnergia\\gestionale_anziendale_energia\\src\\main\\resources\\comuni-italiani.csv"))) {
			String[] lineInArray;
			while ((lineInArray = reader.readNext()) != null) {
				String[] parts = lineInArray[0].split(";");
				String part1 = parts[0];
				String part2 = parts[1];
				String part3 = parts[2];
				String part4 = parts[3];
				ComuneItalia ci = new ComuneItalia(part2, part1, part3, part4);
				ciService.createComune(ci);
			}
		}

	}

	public void setProvince() throws IOException {

		String fileName = "c:\\test\\csv\\country.csv";

		List<ProvinceItalia> beans = new CsvToBeanBuilder<ProvinceItalia>(new FileReader(fileName))
				.withType(ProvinceItalia.class).build().parse();

		beans.forEach(System.out::println);

	}

}
