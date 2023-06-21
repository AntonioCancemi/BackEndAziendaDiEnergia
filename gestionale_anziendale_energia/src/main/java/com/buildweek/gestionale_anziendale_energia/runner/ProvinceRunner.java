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
//		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//			String line;
//			while ((line = reader.readLine()) != null) {
////				String[] arr = line.split(";");
////				System.out.println(arr[0].toString() + "|" + arr[1].toString() + "|" + arr[2].toString());
////
//				String[] x = line.split(";");
//				String part1 = x[0].toString();
//				String part2 = x[1].toString();
//				String part3 = x[2].toString();
//				ProvinceItalia p = new ProvinceItalia(part1, part2, part3);
//				repo.save(p);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
//			String[] lineInArray;
//			while ((lineInArray = reader.readNext()) != null) {
////				String[] parts = lineInArray[].split(";");
////				String part1 = parts[0];
////				String part2 = parts[1];
////				String part3 = parts[2];
//				System.out.println(lineInArray);
////				ProvinceItalia p = new ProvinceItalia(part1, part2, part3);
////				repo.save(p);
//			}
//		}
	}
}
