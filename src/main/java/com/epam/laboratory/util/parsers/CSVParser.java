package com.epam.laboratory.util.parsers;

import com.epam.laboratory.entities.global.Library;
import com.epam.laboratory.entities.library.Author;
import com.epam.laboratory.entities.library.Book;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {

    public Library parseDataFromFile(String pathToCSVFile) {
        List<String[]> CSVFileData = null;
        try (CSVReader reader = new CSVReader(new FileReader(pathToCSVFile))) {
            CSVFileData = reader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        List<Book> bookList = new ArrayList<>();

        for (String[] stringCSVFileData : CSVFileData.subList(1, CSVFileData.size())) {

            bookList.add(new Book(stringCSVFileData[0], //bookName
                    Integer.parseInt(stringCSVFileData[1]), //year
                    Integer.parseInt(stringCSVFileData[2]), //pages
                    stringCSVFileData[3],   // isbn
                    stringCSVFileData[4], // publisher
                    new Author(stringCSVFileData[6],    // name
                            stringCSVFileData[5],   // secondName
                            stringCSVFileData[7],   // lastName
                            stringCSVFileData[8])));    //dob

        }
        Library library = new Library();
        library.setList(bookList);
        return library;
    }
}
