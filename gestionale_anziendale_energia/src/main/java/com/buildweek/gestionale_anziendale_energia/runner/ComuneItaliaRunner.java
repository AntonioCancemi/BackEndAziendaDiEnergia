package com.buildweek.gestionale_anziendale_energia.runner;

import java.io.FileReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.buildweek.gestionale_anziendale_energia.models.ComuneItalia;
import com.buildweek.gestionale_anziendale_energia.service.ComuneItaliaService;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
public class ComuneItaliaRunner implements ApplicationRunner {

	@Autowired ComuneItaliaService ciService;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		setComuni();
		
	}
	
	public void setComuni() throws IOException, CsvValidationException {
		String fileName = "C:\\Users\\user\\Desktop\\comuni-italiani.csv";
		try (CSVReader reader = new CSVReader(new FileReader("C:\\Users\\user\\Desktop\\comuni-italiani.csv"))) {
		      String[] lineInArray;
		      while ((lineInArray = reader.readNext()) != null) {
//		          String a = lineInArray[0].substring(0,3);
//		          String b = lineInArray[0].substring(4,7);
//		          String c = lineInArray[0].substring(8);
		          String[] parts = lineInArray[0].split(";");
		          String part1 = parts[0];
		          String part2 = parts[1];
		          String part3 = parts[2];
		          String part4 = parts[3];
		          ComuneItalia ci = new ComuneItalia(part2, part1, part3, null);
		          //ciService.createComune(ci);
		          System.out.println(part4);
		      }
		  }
	}

}
