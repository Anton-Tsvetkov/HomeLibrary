package com.epam.laboratory.util.parsers;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CSVParser {

    public void showCSVFileData() {
        String filename = "src/main/resources/book/catalog.csv";

        List<String[]> r = null;
        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            r = reader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        int listIndex = 0;
        for (String[] arrays : r) {
            System.out.println("\nString[" + listIndex++ + "] : " + Arrays.toString(arrays));

            int index = 0;
            for (String array : arrays) {
                System.out.println(index++ + " : " + array);
            }
        }
    }
}
