package com.buildweek.gestionale_anziendale_energia.csvreader;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class OpenCsv {

    public static void main(String[] args) throws IOException, CsvException {

        String fileName = "C:\\Users\\user\\Desktop\\comuni-italiani.csv";
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            List<String[]> r = reader.readAll();
            r.forEach(x -> System.out.println(Arrays.toString(x)));
        }

    }

}
